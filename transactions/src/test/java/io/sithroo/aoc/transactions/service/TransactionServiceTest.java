package io.sithroo.aoc.transactions.service;

import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class TransactionServiceTest {
    private TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void createTransactionTest() {
        String accountId = "a1";
        String transactionId = "t1";
        String type = "DEPOSIT";
        Double amount = 5000.65;

        Transaction requestTransaction = new Transaction(accountId, type, amount);
        Transaction expectedTransaction = new Transaction(transactionId, accountId, type, amount);
        given(transactionRepository.save(requestTransaction)).willReturn(expectedTransaction);

        Transaction actualTransaction = transactionService.createTransaction(requestTransaction);
        verify(transactionRepository).save(requestTransaction);
        assertThat(actualTransaction).isEqualTo(expectedTransaction);
    }
}
