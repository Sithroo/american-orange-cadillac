package io.sithroo.aoc.transactions.command;

import io.sithroo.aoc.commons.transactions.TransactionType;
import io.sithroo.aoc.commons.transactions.command.TransactionRequested;
import io.sithroo.aoc.commons.transactions.event.TransactionCommit;
import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.event.TransactionEventPublisher;
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
    private final TransactionEventPublisher eventPublisher;

    public TransactionCommandHandler(TransactionService transactionService, TransactionEventPublisher eventPublisher) {
        this.transactionService = transactionService;
        this.eventPublisher = eventPublisher;
    }

    @RabbitListener(queues = "${transactions.command.queue}")
    void handleCommand(final TransactionRequested command) {
        try {
            Transaction transaction = transactionService.createTransaction(
                    new Transaction(command.getAccountId(), command.getType().name(), command.getAmount()));
            eventPublisher.sendAsync(
                    new TransactionCommit(transaction.getAccountId(), TransactionType.valueOf(transaction.getType()), transaction.getAmount()));
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
