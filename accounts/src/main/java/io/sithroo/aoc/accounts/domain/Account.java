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
    private Double balance;
    private Long createdDate;

    public Account(String customerId, Double balance) {
        this.id = null;
        this.customerId = customerId;
        this.balance = balance;
        this.createdDate = System.currentTimeMillis();
    }

    public Account(String id, String customerId, Double balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(customerId, account.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
                '}';
    }
}
