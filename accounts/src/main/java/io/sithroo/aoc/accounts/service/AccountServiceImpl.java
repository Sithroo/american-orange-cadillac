package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.client.CustomerServiceClient;
import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.repository.AccountRepository;
import io.sithroo.aoc.accounts.resource.AccountResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);
    private final AccountRepository accountRepository;
    private final CustomerServiceClient customerServiceClient;

    @Autowired
    public AccountServiceImpl(final AccountRepository accountRepository,
                              final CustomerServiceClient customerServiceClient) {
        this.accountRepository = accountRepository;
        this.customerServiceClient = customerServiceClient;
    }

    @Transactional
    @Override
    public Account createAccount(Account account) {
        logger.info("Create Account: " + account);
        validate(account);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        logger.info("Update Account: " + account);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(String accountId) {
        logger.info("Get Account: " + accountId);
        return accountRepository.findById(accountId);
    }

    private void validate(Account account) {
        boolean validAccount = customerServiceClient.validCustomer(account.getCustomerId());
//      FIXME: Other validations on account
//      validAccount &= creaditCheckService.validCreadits(account.getCustomerId())
        if(!validAccount)
            throw new AccountServiceException("Account validation failed, invalid customer: " + account.getCustomerId());
    }
}
