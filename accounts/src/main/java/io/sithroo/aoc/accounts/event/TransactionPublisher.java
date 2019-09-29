package io.sithroo.aoc.accounts.event;

public interface TransactionPublisher {
    public void sendAsync(final TransactionEvent transactionEvent);
}
