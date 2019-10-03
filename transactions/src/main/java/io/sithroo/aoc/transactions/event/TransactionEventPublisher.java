package io.sithroo.aoc.transactions.event;

import io.sithroo.aoc.commons.transactions.event.TransactionCommit;

/**
 *  Publisher for events related the transaction entity
 */
public interface TransactionEventPublisher {
    /**
     * Publish Transaction event on Message Broker
     * @param transactionCommit
     */
    public void sendAsync(final TransactionCommit transactionCommit);
}
