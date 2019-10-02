package io.sithroo.aoc.commons.accounts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * Data Transfer protocol representing an account
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO extends ResourceSupport {
    private String accountId;
    private String customerId;
    private AccountType accountType;
    private Double balance;
    private Long createdDate;

    public AccountDTO() {
    }

    public AccountDTO(String accountId, String customerId, AccountType accountType, Double balance, Long createdDate) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountType = accountType;
        this.balance = balance;
        this.createdDate = createdDate;
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

    public Double getBalance() {
        return balance;
    }

    public Long getCreatedDate() {
        return createdDate;
    }
}
