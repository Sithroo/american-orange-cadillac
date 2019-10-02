package io.sithroo.aoc.transactions.event;

import io.sithroo.aoc.commons.transactions.event.DepositTransaction;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventPublisherImpl implements TransactionEventPublisher {
    private RabbitTemplate rabbitTemplate;
    private String transactionsCommandTopic;
    private String transactionsCommandKey;

    @Autowired
    TransactionEventPublisherImpl(final RabbitTemplate rabbitTemplate,
                                  @Value("${transactions.event}") final String transactionsEventTopic,
                                  @Value("${transactions.event.key}") final String transactionsEventKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.transactionsCommandTopic = transactionsEventTopic;
        this.transactionsCommandKey = transactionsEventKey;
    }

    @Async
    public void sendAsync(final DepositTransaction depositTransaction) {
        rabbitTemplate.convertAndSend(transactionsCommandTopic, transactionsCommandKey, depositTransaction);
    }
}
