package io.sithroo.aoc.transactions.event;

import io.sithroo.aoc.commons.transactions.event.DepositTransaction;

/**
 *  Publisher for events related the transaction entity
 */
public interface TransactionEventPublisher {
    /**
     * Publish Transaction event on Message Broker
     * @param depositTransaction
     */
    public void sendAsync(final DepositTransaction depositTransaction);
}
