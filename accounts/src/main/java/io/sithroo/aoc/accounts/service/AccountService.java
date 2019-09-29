package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.domain.Account;

import java.util.Optional;

/**
 * Application Service Layer for Accounts
 */
public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> getAccount(String accountId);
}
