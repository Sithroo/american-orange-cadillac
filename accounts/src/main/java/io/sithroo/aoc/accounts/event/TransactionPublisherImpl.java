package io.sithroo.aoc.accounts.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TransactionPublisherImpl implements TransactionPublisher {
    private RabbitTemplate rabbitTemplate;
    private String transactionsExchange;
    private String transactionsRoutingKey;

    @Autowired
    TransactionPublisherImpl(final RabbitTemplate rabbitTemplate,
                             @Value("${transactions.exchange}") final String transactionsExchange,
                             @Value("${transactions.routing.key}") final String transactionsRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.transactionsExchange = transactionsExchange;
        this.transactionsRoutingKey = transactionsRoutingKey;
    }

    @Async
    public void sendAsync(final TransactionEvent transactionEvent) {
        rabbitTemplate.convertAndSend(transactionsExchange, transactionsRoutingKey,transactionEvent);
    }
}
