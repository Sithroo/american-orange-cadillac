package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.client.CustomerServiceClient;
import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.event.TransactionEvent;
import io.sithroo.aoc.accounts.event.TransactionPublisher;
import io.sithroo.aoc.accounts.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private AccountServiceImp accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionPublisher transactionPublisher;

    @Mock
    private CustomerServiceClient customerServiceClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImp(accountRepository, transactionPublisher, customerServiceClient);
    }

    @Test
    public void createAccountTest() {
        String accountId = "a1";
        String customerId = "c1";
        Double initialAmount = 5000.65;

        Account requestAccount = new Account(customerId, initialAmount);
        Account expectedAccount = new Account(accountId,customerId, initialAmount);
        TransactionEvent event = new TransactionEvent(accountId, initialAmount);
        given(accountRepository.save(requestAccount)).willReturn(expectedAccount);
        given(customerServiceClient.validCustomer(customerId)).willReturn(true);

        Account actualAccount = accountService.createAccount(requestAccount);
        verify(accountRepository).save(requestAccount);
        verify(transactionPublisher).send(event);
        assertThat(actualAccount).isEqualTo(expectedAccount);
    }

    @Test(expected = AccountServiceException.class)
    public void failAccountForInvalidCustomer() {
        String accountId = "a1";
        String customerId = "INVALID_CUSTOMER";
        Double initialAmount = 5000.65;

        Account requestAccount = new Account(customerId, initialAmount);
        given(customerServiceClient.validCustomer(customerId)).willReturn(false);

        verify(accountRepository, never()).save(requestAccount);
        verify(transactionPublisher, never()).send(any(TransactionEvent.class));
        accountService.createAccount(requestAccount);
    }
}
