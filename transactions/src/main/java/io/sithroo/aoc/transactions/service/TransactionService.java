package io.sithroo.aoc.transactions.service;

import io.sithroo.aoc.transactions.domain.Transaction;

import java.util.List;

/**
 * Application Service Layer for Transactions
 */
public interface TransactionService {

    /**
     * Create new Transaction
     * @param transaction Transaction to be created
     * @return created Transaction
     */
    Transaction createTransaction(Transaction transaction);

    /**
     * Find transactions by AccountId
     * @param accountId Account Id for transactions
     * @return List of Transactions for given account id
     */
    List<Transaction> getTransactions(String accountId);
}

