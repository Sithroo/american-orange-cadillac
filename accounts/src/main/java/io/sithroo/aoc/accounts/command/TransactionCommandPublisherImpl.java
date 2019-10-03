package io.sithroo.aoc.accounts.command;

import io.sithroo.aoc.commons.transactions.command.TransactionRequested;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TransactionCommandPublisherImpl implements TransactionCommandPublisher {
    private RabbitTemplate rabbitTemplate;
    private String transactionsExchange;
    private String transactionsCommandKey;

    @Autowired
    TransactionCommandPublisherImpl(final RabbitTemplate rabbitTemplate,
                                    @Value("${transactions.exchange}") final String transactionsExchange,
                                    @Value("${transactions.command.key}") final String transactionsCommandKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.transactionsExchange = transactionsExchange;
        this.transactionsCommandKey = transactionsCommandKey;
    }

    @Async
    public void sendAsync(final TransactionRequested transactionRequested) {
        rabbitTemplate.convertAndSend(transactionsExchange, transactionsCommandKey, transactionRequested);
    }
}
