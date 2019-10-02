package io.sithroo.aoc.commons.accounts.dto;

import java.util.Objects;

/**
 * Data Transfer protocol representing an account request
 */
public class AccountRequestDTO {
    private final String customerId;
    private final AccountType accountType;
    private final Double initialAmount;

    public AccountRequestDTO(String customerId, AccountType accountType, Double initialAmount) {
        this.customerId = customerId;
        this.accountType = accountType;
        this.initialAmount = initialAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRequestDTO that = (AccountRequestDTO) o;
        return Objects.equals(customerId, that.customerId) &&
                accountType == that.accountType &&
                Objects.equals(initialAmount, that.initialAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, accountType, initialAmount);
    }

    @Override
    public String toString() {
        return "AccountRequestDTO{" +
                "customerId='" + customerId + '\'' +
                ", accountType=" + accountType +
                ", initialAmount=" + initialAmount +
                '}';
    }
}
