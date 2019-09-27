package io.sithroo.aoc.transactions.event;

public class DepositTransactionEvent extends TransactionEvent {
    private final String tellerId;

    public DepositTransactionEvent(String tellerId) {
        this.tellerId = tellerId;
    }

    public DepositTransactionEvent(String accountId, Double amount, Long transactionTime, String tellerId) {
        super(accountId, amount, transactionTime);
        this.tellerId = tellerId;
    }
}
