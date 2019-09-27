package io.sithroo.aoc.transactions.service;

import io.sithroo.aoc.transactions.domain.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getTransactions(String accountId);
}

