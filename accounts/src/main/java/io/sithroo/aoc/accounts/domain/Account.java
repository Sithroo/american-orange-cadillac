package io.sithroo.aoc.accounts.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Represents a business entity of Account
 */
@Entity
public class Account {
    @Id
    @GenericGenerator(name = "account_uuid", strategy = "io.sithroo.aoc.accounts.domain.AccountIdGenerator")
    @GeneratedValue(generator = "account_uuid")
    @Column(name = "account_id")
    private String id;
    private String customerId;
    private String type;
    private Double balance;
    private Long createdDate;

    public Account() {
    }

    public Account(String customerId, String type) {
        this.customerId = customerId;
        this.type = type;
        this.createdDate = System.currentTimeMillis();
    }

    public Account(String id, String customerId,  String type) {
        this.id = id;
        this.customerId = customerId;
        this.type = type;
        this.createdDate = System.currentTimeMillis();
    }

    public Account(String id, String customerId, String type, Double balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
        this.type = type;
        this.createdDate = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(customerId, account.customerId) &&
                Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
                '}';
    }
}
