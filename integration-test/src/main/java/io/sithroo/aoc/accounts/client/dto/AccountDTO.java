package io.sithroo.aoc.accounts.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO extends ResourceSupport {
    private final String accountId;
    private final String customerId;
    private final Double initialAmount;
    private final Long createdDate;

    public AccountDTO() {
        this.accountId = null;
        this.customerId = null;
        this.initialAmount = null;
        this.createdDate  = null;
    }

    public AccountDTO(String customerId, Double initialAmount) {
        this.accountId = null;
        this.customerId = customerId;
        this.initialAmount = initialAmount;
        this.createdDate  = null;
    }

    public AccountDTO(String accountId, String customerId, Double initialAmount, Long createdDate) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.initialAmount = initialAmount;
        this.createdDate  = createdDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(initialAmount, that.initialAmount) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, customerId, initialAmount, createdDate);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId='" + accountId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", initialAmount=" + initialAmount +
                ", createdDate=" + createdDate +
                '}';
    }
}
