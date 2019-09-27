package io.sithroo.aoc.transactions.event;

import java.io.Serializable;
import java.util.Objects;

public class TransactionEvent implements Serializable {
    private final String accountId;
    private final Double amount;
    private final Long transactionTime;

    public TransactionEvent() {
        this.accountId = null;
        this.amount = null;
        this.transactionTime = null;
    }

    public TransactionEvent(String accountId, Double amount, Long transactionTime) {
        this.accountId = accountId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEvent that = (TransactionEvent) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transactionTime, that.transactionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount, transactionTime);
    }
}
