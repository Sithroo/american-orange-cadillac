package io.sithroo.aoc.transactions.service;

import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        logger.info("Create transaction: " + transaction);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        logger.info("Get transactions for account: " + accountId);
        return transactionRepository.findByAccountId(accountId);
    }
}
