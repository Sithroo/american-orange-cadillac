package io.sithroo.aoc.transactions.client.dto;

public class TransactionDTO {
    private final String transactionId;
    private final String accountId;
    private final TransactionType type;
    private final Double amount;

    public TransactionDTO() {
        this.transactionId = null;
        this.accountId = null;
        this.type = null;
        this.amount = null;
    }

    public TransactionDTO(String transactionId, String accountId, TransactionType type, Double amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
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
    public String toString() {
        return "TransactionDTO{" +
                "transactionId='" + transactionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
