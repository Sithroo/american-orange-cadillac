package io.sithroo.aoc.transactions.resource;

import io.sithroo.aoc.transactions.domain.Transaction;
import io.sithroo.aoc.transactions.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Api(tags = "Transaction Resource")
public class TransactionResource {
    private TransactionService transactionService;

    @Autowired
    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    @ApiOperation(value = "Get transactions for given account")
    public List<Transaction> getTransactionsByAccount(
            @ApiParam(value = "AccountId of the transactions", required = true)
            @RequestParam("accountId") final String accountId) {
        return transactionService.getTransactions(accountId);
    }
}
