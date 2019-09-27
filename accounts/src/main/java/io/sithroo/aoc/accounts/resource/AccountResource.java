package io.sithroo.aoc.accounts.resource;

import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.resource.dto.AccountDTO;
import io.sithroo.aoc.accounts.service.AccountServiceException;
import io.sithroo.aoc.accounts.service.AccountService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/v1")
@Api(tags = "Account Resource")
public class AccountResource {
    private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
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
        return new ResponseEntity(parseAccount(createdAccount), HttpStatus.CREATED);
    }

    private Account parseAccountDTO(AccountDTO accountDto) {
        return new Account(accountDto.getCustomerId(), accountDto.getInitialAmount());
    }

    private AccountDTO parseAccount(Account account) {
        AccountDTO dto = new AccountDTO(account.getId(), account.getCustomerId(), account.getBalance(), account.getCreatedDate());

        dto.add(new Link("/v1/accounts/" + account.getId()));
        dto.add(new Link("/v1/transactions?accountId=" + account.getId(), "transactions"));
        return dto;
    }
}
