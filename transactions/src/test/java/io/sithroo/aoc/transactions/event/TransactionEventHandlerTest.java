package io.sithroo.aoc.transactions.event;

import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.domain.TransactionType;
import io.sithroo.aoc.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TransactionEventHandlerTest {
    private TransactionEventHandler transactionEventHandler;

    @Mock
    private TransactionService transactionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionEventHandler = new TransactionEventHandler(transactionService);
    }

    @Test
    public void receivedTransactionEventTest() {
        String accountId = "a1";
        String transactionId = "t1";
        Double amount = 5000.65;

        TransactionEvent event = new TransactionEvent(accountId, amount, System.currentTimeMillis());

        Transaction requestTransaction = new Transaction(accountId, TransactionType.DEPOSIT, amount);
        Transaction registeredTransaction = new Transaction(transactionId, accountId, TransactionType.DEPOSIT, amount);
        given(transactionService.createTransaction(requestTransaction)).willReturn(registeredTransaction);

        transactionEventHandler.handleTransactionEvent(event);
        verify(transactionService).createTransaction(requestTransaction);
    }
}
