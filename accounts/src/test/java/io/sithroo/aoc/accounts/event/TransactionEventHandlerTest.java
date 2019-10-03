package io.sithroo.aoc.accounts.event;

import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.service.AccountService;
import io.sithroo.aoc.commons.transactions.TransactionType;
import io.sithroo.aoc.commons.transactions.event.TransactionCommit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TransactionEventHandlerTest {
    private TransactionEventHandler transactionEventHandler;

    @Mock
    private AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionEventHandler = new TransactionEventHandler(accountService);
    }

    @Test
    public void receivedTransactionCommitTest() {
        String accountId = "a1";
        String customerId = "c1";
        TransactionType type = TransactionType.DEPOSIT;
        Double amount = 5000.65;

        TransactionCommit event = new TransactionCommit(accountId, type, amount);

        Optional<Account> existingAccount = Optional.of(new Account(accountId, customerId, type.name(), 2000d));
        Account updatedAccount = new Account(accountId, customerId, type.name(), 2000d + amount);
        given(accountService.getAccount(accountId)).willReturn(existingAccount);

        transactionEventHandler.handleCommand(event);
        verify(accountService).updateAccount(updatedAccount);
    }
}
