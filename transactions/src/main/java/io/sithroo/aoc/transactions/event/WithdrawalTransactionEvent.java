package io.sithroo.aoc.transactions.event;

public class WithdrawalTransactionEvent extends TransactionEvent {
    private final String tellerId;

    public WithdrawalTransactionEvent(String tellerId) {
        this.tellerId = tellerId;
    }

    public WithdrawalTransactionEvent(String accountId, Double amount, Long transactionTime, String tellerId) {
        super(accountId, amount, transactionTime);
        this.tellerId = tellerId;
    }
}
