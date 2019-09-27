package io.sithroo.aoc.accounts.event;

import java.io.Serializable;
import java.util.Objects;

public class TransactionEvent implements Serializable {
    private final String accountId;
    private final Double amount;

    public TransactionEvent(String accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEvent that = (TransactionEvent) o;
        return accountId.equals(that.accountId) &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount);
    }
}
