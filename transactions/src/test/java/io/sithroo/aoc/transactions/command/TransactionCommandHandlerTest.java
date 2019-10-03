package io.sithroo.aoc.transactions.command;

import io.sithroo.aoc.commons.transactions.TransactionType;
import io.sithroo.aoc.commons.transactions.command.TransactionRequested;
import io.sithroo.aoc.commons.transactions.event.TransactionCommit;
import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.event.TransactionEventPublisher;
import io.sithroo.aoc.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TransactionCommandHandlerTest {
    private TransactionCommandHandler transactionCommandHandler;

    @Mock
    private TransactionService transactionService;

    @Mock
    private TransactionEventPublisher transactionEventPublisher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionCommandHandler = new TransactionCommandHandler(transactionService, transactionEventPublisher);
    }

    @Test
    public void receivedTransactionRequestedTest() {
        String accountId = "a1";
        String transactionId = "t1";
        Double amount = 5000.65;

        TransactionRequested command = new TransactionRequested(accountId, amount, TransactionType.DEPOSIT);
        TransactionCommit event = new TransactionCommit(accountId, TransactionType.DEPOSIT, amount);

        Transaction requestTransaction = new Transaction(accountId, TransactionType.DEPOSIT.name(), amount);
        Transaction registeredTransaction = new Transaction(transactionId, accountId, TransactionType.DEPOSIT.name(), amount);
        given(transactionService.createTransaction(requestTransaction)).willReturn(registeredTransaction);

        transactionCommandHandler.handleCommand(command);
        verify(transactionService).createTransaction(requestTransaction);
        verify(transactionEventPublisher).sendAsync(event);
    }
}
