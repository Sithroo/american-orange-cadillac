package io.sithroo.aoc.accounts.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO extends ResourceSupport {
    private String accountId;
    private String customerId;
    private AccountType accountType;
    private Double initialAmount;
    private Double balance;
    private Long createdDate;

    public AccountDTO() {
    }

    public AccountDTO(String customerId, Double initialAmount) {
        this.customerId = customerId;
        this.initialAmount = initialAmount;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(customerId, that.customerId) &&
                accountType == that.accountType &&
                Objects.equals(initialAmount, that.initialAmount) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountId, customerId, accountType, initialAmount, balance, createdDate);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId='" + accountId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountType=" + accountType +
                ", initialAmount=" + initialAmount +
                ", balance=" + balance +
                ", createdDate=" + createdDate +
                '}';
    }
}
