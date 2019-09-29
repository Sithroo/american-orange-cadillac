package io.sithroo.aoc.accounts.resource;

import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.event.TransactionEvent;
import io.sithroo.aoc.accounts.event.TransactionPublisher;
import io.sithroo.aoc.accounts.event.TransactionPublisherImpl;
import io.sithroo.aoc.accounts.resource.dto.AccountDTO;
import io.sithroo.aoc.accounts.service.AccountService;
import io.sithroo.aoc.accounts.service.AccountServiceException;
import io.sithroo.aoc.accounts.service.NoSuchAccountException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Api(tags = "Account Resource")
public class AccountResource {
    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;
    private final TransactionPublisher transactionPublisher;

    @Autowired
    public AccountResource(AccountService accountService, TransactionPublisherImpl transactionPublisher) {
        this.accountService = accountService;
        this.transactionPublisher = transactionPublisher;
    }

    @ApiOperation(value = "Create new account for existing customers")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account created"),
            @ApiResponse(code = 400, message = "Account failed, bad account request"),
            @ApiResponse(code = 500, message = "Account failed, internal system error when processing the request")
    })
    @PostMapping("/accounts")
    ResponseEntity<AccountDTO> createAccount(@ApiParam(value = "The account to be registered in the system", required = true)
                                          @RequestBody AccountDTO accountDto) throws AccountServiceException {
        logger.info("Create Account " + accountDto);
        Account createdAccount = accountService.createAccount(parseAccountDTO(accountDto));
        publishTransactionOnlyIfValidAmount(createdAccount.getId(), accountDto.getInitialAmount());

        return new ResponseEntity(parseAccount(createdAccount), HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountId}")
    AccountDTO getAccount(@PathVariable("accountId") final String accountId) {
        return accountService.getAccount(accountId)
                .map(a -> parseAccount(a))
                .orElseThrow(() -> new NoSuchAccountException(String.format("The Account with given id: %s is not found", accountId)));
    }

    private Account parseAccountDTO(AccountDTO accountDto) {
//      FIXME: We don't set the initial amount as the account balance. It is seprate event from TransactionService
        return new Account(accountDto.getCustomerId());
    }

    private AccountDTO parseAccount(Account account) {
        AccountDTO dto = new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance(), account.getCreatedDate());

        dto.add(new Link("/v1/accounts/" + account.getId()));
        dto.add(new Link("/v1/transactions?accountId=" + account.getId(), "transactions"));
        return dto;
    }

    private void publishTransactionOnlyIfValidAmount(String accountId, Double initialAmount) {
        if(initialAmount > 0)
            transactionPublisher.sendAsync(new TransactionEvent(accountId, initialAmount));
    }
}
