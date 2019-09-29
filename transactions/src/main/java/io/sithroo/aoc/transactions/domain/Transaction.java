package io.sithroo.aoc.transactions.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * The transactions of an Account are represented in Transaction entity, It is an immutable entity, which its state cannot
 * be changed. That is, once a transaction is registered in the system, it cannot be modified.
 */
@Entity
public class Transaction {
    @Id
    @GenericGenerator(name = "transaction_uuid", strategy = "io.sithroo.aoc.transactions.domain.TransactionIdGenerator")
    @GeneratedValue(generator = "transaction_uuid")
    @Column(name = "transaction_id")
    private String transactionId;
    private String accountId;
    private TransactionType type;
    private Double amount;

    public Transaction() {
    }

    public Transaction(String accountId, TransactionType type, Double amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public Transaction(String transactionId, String accountId, TransactionType type, Double amount) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(accountId, that.accountId) &&
                type == that.type &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountId, type, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }


}
