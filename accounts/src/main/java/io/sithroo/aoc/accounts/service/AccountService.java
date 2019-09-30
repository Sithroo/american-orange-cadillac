package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.domain.Account;

import java.util.Optional;

/**
 * Application Service Layer for Accounts
 */
public interface AccountService {
    /**
     * Create New Account
     * @param account Account to be created
     * @return Created Account
     */
    Account createAccount(Account account);

    /**
     * Get Account by accountId
     * @param accountId Account Id
     * @return Account for given id
     */
    Optional<Account> getAccount(String accountId);
}
