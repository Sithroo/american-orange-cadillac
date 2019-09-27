package io.sithroo.aoc.accounts.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

@ApiModel(description = "All attributes about of Account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO extends ResourceSupport {
    @ApiModelProperty(notes = "Account ID, system generated", readOnly = true)
    private final String accountId;

    @ApiModelProperty(notes = "Customer ID, system generated")
    private final String customerId;

    @ApiModelProperty(notes = "Initial amount")
    private final Double initialAmount;

    @ApiModelProperty(notes = "Account created Date", readOnly = true)
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
