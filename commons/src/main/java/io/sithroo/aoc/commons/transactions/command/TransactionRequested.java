package io.sithroo.aoc.commons.transactions.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.sithroo.aoc.commons.transactions.TransactionType;

import java.io.Serializable;
import java.util.Objects;

/**
 * Command event protocol for requesting transaction
 */
public class TransactionRequested implements Serializable {
    private String accountId;
    private Double amount;
    private TransactionType type;

    public TransactionRequested() {
    }

    public TransactionRequested(String accountId, Double amount, TransactionType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequested that = (TransactionRequested) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(amount, that.amount) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount, type);
    }
}
