package io.sithroo.aoc.commons.transactions.event;

import io.sithroo.aoc.commons.transactions.TransactionType;

import java.util.Objects;

public class TransactionCommit {
    private String accountId;
    private TransactionType type;
    private Double amount;

    public TransactionCommit() {
    }

    public TransactionCommit(String accountId, TransactionType type, Double amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionCommit that = (TransactionCommit) o;
        return Objects.equals(accountId, that.accountId) &&
                type == that.type &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, type, amount);
    }

    @Override
    public String toString() {
        return "TransactionCommit{" +
                "accountId='" + accountId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
