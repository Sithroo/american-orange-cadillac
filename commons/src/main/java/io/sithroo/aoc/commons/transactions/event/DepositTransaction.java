package io.sithroo.aoc.commons.transactions.event;

public class DepositTransaction {
    private final String accountId;
    private final Double amount;

    public DepositTransaction(String accountId, Double amount) {
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
    public String toString() {
        return "DepositTransaction{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
