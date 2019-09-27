package io.sithroo.aoc.transactions.event;

import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.domain.TransactionType;
import io.sithroo.aoc.transactions.service.TransactionService;
import io.sithroo.aoc.transactions.util.TransactionUtil;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventHandler {
    private final TransactionService transactionService;

    public TransactionEventHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RabbitListener(queues = "${transactions.queue}")
    void handleTransactionEvent(final TransactionEvent event) {
        try {
            transactionService.createTransaction(new Transaction(event.getAccountId(), TransactionType.DEPOSIT, event.getAmount()));
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
