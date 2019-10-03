package io.sithroo.aoc.accounts.event;

import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.service.AccountService;
import io.sithroo.aoc.accounts.service.NoSuchAccountException;
import io.sithroo.aoc.commons.transactions.event.TransactionCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Transaction Command Handler
 */
@Component
public class TransactionEventHandler {
    private final Logger logger = LoggerFactory.getLogger(TransactionEventHandler.class);

    private final AccountService accountService;

    public TransactionEventHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = "${transactions.event.queue}")
    void handleCommand(final TransactionCommit event) {
        logger.info("Receive TransactionCommit event: " + event);
        try {
            Account updatedAccount = accountService.getAccount(event.getAccountId())
                    .map(account -> parseAccount(account, event))
                    .orElseThrow(() -> new NoSuchAccountException(String.format("The Account with given id: %s is not found", event.getAccountId())));

            accountService.updateAccount(updatedAccount);
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private Account parseAccount(Account existingAccount, TransactionCommit event) {
        Double amountDelta = 0d;
        switch (event.getType()) {
            case DEPOSIT:
                amountDelta = event.getAmount();
                break;
            case WITHDRAWAL:
                amountDelta = -event.getAmount();
            default:
                logger.warn("Unknown TransactionCommit type: " + event);
        }

        Account updatedAccount =  new Account(
                existingAccount.getId(),
                existingAccount.getCustomerId(),
                existingAccount.getType(),
                existingAccount.getBalance() + amountDelta);

        return updatedAccount;
    }
}
