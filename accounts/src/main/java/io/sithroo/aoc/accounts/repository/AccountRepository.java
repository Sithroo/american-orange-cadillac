package io.sithroo.aoc.accounts.repository;

import io.sithroo.aoc.accounts.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
