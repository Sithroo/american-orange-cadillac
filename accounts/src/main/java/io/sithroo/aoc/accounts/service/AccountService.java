package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.domain.Account;

public interface AccountService {
    Account createAccount(Account account) throws AccountServiceException;
}
