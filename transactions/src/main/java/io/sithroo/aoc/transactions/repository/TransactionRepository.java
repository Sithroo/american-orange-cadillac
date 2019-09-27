package io.sithroo.aoc.transactions.repository;

import io.sithroo.aoc.transactions.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Find all transactions for a given accountId
     * @param accountId the id of the account
     * @return the list of Transactions
     */
    List<Transaction> findByAccountId(final String accountId);
}
