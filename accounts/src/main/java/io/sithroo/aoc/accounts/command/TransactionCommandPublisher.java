package io.sithroo.aoc.accounts.command;

import io.sithroo.aoc.commons.transactions.command.TransactionRequested;

/**
 * Publisher for command events related to account transactions
 */
public interface TransactionCommandPublisher {

    /**
     * Publish TransactionRequested command event on Message Broker
     * @param transactionRequested transaction requested command event
     */
    public void sendAsync(final TransactionRequested transactionRequested);
}
