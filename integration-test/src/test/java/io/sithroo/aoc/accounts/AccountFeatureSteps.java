package io.sithroo.aoc.accounts;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.sithroo.aoc.accounts.client.AccountClient;
import io.sithroo.aoc.accounts.client.dto.AccountDTO;
import io.sithroo.aoc.transactions.client.TransactionClient;
import io.sithroo.aoc.transactions.client.dto.TransactionDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountFeatureSteps {
    private AccountClient accountClient;
    private TransactionClient transactionClient;

    private AccountDTO createdAccount;

    public AccountFeatureSteps() {
        this.accountClient = new AccountClient();
        this.transactionClient = new TransactionClient();
    }

    @Given("^the cusotmer ([^\\s]+) creates an account with initial amount ([0-9.]+)")
    public void the_customer_create_an_account(final String customerid, final Double initialAmount) throws Throwable {
        createdAccount = accountClient.createAccount(customerid,  initialAmount);
    }

    @Then("^([^\\s]+) gets created account with one transaction amount ([0-9.]+)")
    public void the_customer_gets_created_account_and_transaction(final String customerid, final Double transactionAmount) throws Throwable {
        assertThat(createdAccount.getCustomerId()).isEqualTo(customerid);
        assertThat(createdAccount.getAccountId()).isNotBlank();

        String accountId = createdAccount.getAccountId();
        List<TransactionDTO> transactions = transactionClient.getTransactions(accountId);
        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0).getAmount()).isEqualTo(transactionAmount);
    }

    @Given("^the cusotmer ([^\\s]+) creates an account with no initial amount")
    public void the_customer_create_an_account_no_amount(final String customerid) throws Throwable {
        createdAccount = accountClient.createAccount(customerid,  0d);
    }

    @Then("^([^\\s]+) gets created account with no transactions")
    public void the_customer_gets_created_account_and_no_transaction(final String customerid) throws Throwable {
        assertThat(createdAccount.getCustomerId()).isEqualTo(customerid);
        assertThat(createdAccount.getAccountId()).isNotBlank();

        String accountId = createdAccount.getAccountId();
        List<TransactionDTO> transactions = transactionClient.getTransactions(accountId);
        assertThat(transactions.size()).isEqualTo(0);
    }
}
