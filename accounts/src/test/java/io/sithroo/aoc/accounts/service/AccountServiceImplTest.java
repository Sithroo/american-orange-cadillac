package io.sithroo.aoc.accounts.service;

import io.sithroo.aoc.accounts.client.CustomerServiceClient;
import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerServiceClient customerServiceClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountRepository, customerServiceClient);
    }

    @Test
    public void createAccountTest() {
        String accountId = "a1";
        String customerId = "c1";

        Account requestAccount = new Account(customerId);
        Account expectedAccount = new Account(accountId, customerId);
        given(accountRepository.save(requestAccount)).willReturn(expectedAccount);
        given(customerServiceClient.validCustomer(customerId)).willReturn(true);

        Account actualAccount = accountService.createAccount(requestAccount);
        verify(accountRepository).save(requestAccount);
        assertThat(actualAccount).isEqualTo(expectedAccount);
    }

    @Test(expected = AccountServiceException.class)
    public void failAccountForInvalidCustomer() {
        String accountId = "a1";
        String customerId = "INVALID_CUSTOMER";

        Account requestAccount = new Account(customerId);
        given(customerServiceClient.validCustomer(customerId)).willReturn(false);

        try {
            accountService.createAccount(requestAccount);
        } catch (RuntimeException exp) {
            verify(accountRepository, never()).save(requestAccount);
            throw exp;
        }
    }

    @Test
    public void getAccountTest() {
        String accountId = "a1";
        String customerId = "c1";

        Optional<Account> expectedAccount = Optional.of(new Account(accountId, customerId));
        given(accountRepository.findById(accountId)).willReturn(expectedAccount);

        Optional<Account> actualAccount = accountService.getAccount(accountId);
        verify(accountRepository).findById(accountId);
        assertThat(actualAccount).isEqualTo(expectedAccount);
    }
}
