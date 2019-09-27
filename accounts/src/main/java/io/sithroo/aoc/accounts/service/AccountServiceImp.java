package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.client.CustomerServiceClient;
import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.event.TransactionEvent;
import io.sithroo.aoc.accounts.event.TransactionPublisher;
import io.sithroo.aoc.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionPublisher transactionPublisher;
    private final CustomerServiceClient customerServiceClient;

    @Autowired
    public AccountServiceImp(final AccountRepository accountRepository,
                             final TransactionPublisher transactionPublisher,
                             final CustomerServiceClient customerServiceClient) {
        this.accountRepository = accountRepository;
        this.transactionPublisher = transactionPublisher;
        this.customerServiceClient = customerServiceClient;
    }

    @Transactional
    @Override
    public Account createAccount(Account account) throws AccountServiceException{
        validate(account);
        Account a = accountRepository.save(account);
        transactionPublisher.send(new TransactionEvent(a.getId(), a.getBalance()));
        return a;
    }

    private void validate(Account account) throws AccountServiceException {
        boolean validAccount = true;
        validAccount &= customerServiceClient.validCustomer(account.getCustomerId());
//      FIXME: Other validations on account
//      validAccount &= creaditCheckService.validCreadits(account.getCustomerId())
        if(!validAccount)
            throw new AccountServiceException("Account validation failed");
    }
}
