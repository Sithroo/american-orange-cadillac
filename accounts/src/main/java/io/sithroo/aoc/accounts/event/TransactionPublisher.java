package io.sithroo.aoc.accounts.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TransactionPublisher {
    private RabbitTemplate rabbitTemplate;
    private String transactionsExchange;
    private String transactionsRoutingKey;

    @Autowired
    TransactionPublisher(final RabbitTemplate rabbitTemplate,
                    @Value("${transactions.exchange}") final String transactionsExchange,
                    @Value("${transactions.solved.key}") final String transactionsRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.transactionsExchange = transactionsExchange;
        this.transactionsRoutingKey = transactionsRoutingKey;
    }

    public void send(final TransactionEvent transactionEvent) {
        rabbitTemplate.convertAndSend(transactionsExchange, transactionsRoutingKey,transactionEvent);
    }
}
