package io.sithroo.aoc.transactions.command;

import io.sithroo.aoc.commons.transactions.command.TransactionRequested;
import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.service.TransactionService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Transaction Command Handler
 */
@Component
public class TransactionCommandHandler {
    private final TransactionService transactionService;

    public TransactionCommandHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RabbitListener(queues = "${transactions.command.queue}")
    void handleCommand(final TransactionRequested command) {
        try {
            transactionService.createTransaction(new Transaction(command.getAccountId(), command.getType().name(), command.getAmount()));
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
