package io.sithroo.aoc.accounts.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sithroo.aoc.accounts.event.TransactionEvent;
import io.sithroo.aoc.accounts.event.TransactionPublisherImpl;
import io.sithroo.aoc.accounts.service.AccountServiceException;
import io.sithroo.aoc.accounts.domain.Account;
import io.sithroo.aoc.accounts.resource.dto.AccountDTO;
import io.sithroo.aoc.accounts.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@RunWith(SpringRunner.class)
@WebMvcTest(AccountResource.class)
public class AccountResourceTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionPublisherImpl transactionPublisher;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<AccountDTO> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void createAccountTest() throws Exception {
        String accountId = "a1";
        String customerId = "c1";
        Double initialAmount = 5000.65;

        Account createdAccount = new Account(accountId, customerId);
        TransactionEvent event = new TransactionEvent(accountId, initialAmount);
        given(accountService.createAccount(new Account(customerId)))
                .willReturn(createdAccount);

        AccountDTO accountRequest = new AccountDTO(customerId, initialAmount);
        MockHttpServletResponse response = mvc.perform(
                post("/v1/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(accountRequest).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentType()).isEqualTo("application/hal+json;charset=UTF-8");

        ObjectContent<AccountDTO> accountResponse = json.parse(response.getContentAsString());
        assertThat(accountResponse.getObject()).isNotNull();
        assertThat(accountResponse.getObject().getAccountId()).isNotBlank();
        assertThat(accountResponse.getObject().getCustomerId()).isEqualTo(customerId);
        verify(transactionPublisher).sendAsync(event);
    }

    @Test
    public void createAccountWithInvalidCustomerTest() throws Exception {
        String customerId = "INVALID_CUSTOMER";
        Double initialAmount = 5000.65;

        given(accountService.createAccount(new Account(customerId)))
                .willThrow(new AccountServiceException("Account validation failed, invalid customer: " + customerId));

        AccountDTO accountRequest = new AccountDTO(customerId, initialAmount);
        MockHttpServletResponse response = mvc.perform(
                post("/v1/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(accountRequest).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        verify(transactionPublisher, never()).sendAsync(any(TransactionEvent.class));
    }

    @Test
    public void getAccountTest() throws Exception {
        String accountId = "a1";
        String customerId = "c1";
        Double balance = 5000.65;

        Optional<Account> account = Optional.of(new Account(accountId, customerId, balance));
        given(accountService.getAccount(accountId))
                .willReturn(account);

        MockHttpServletResponse response = mvc.perform(
                get(String.format("/v1/accounts/%s", accountId)).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectContent<AccountDTO> accountResponse = json.parse(response.getContentAsString());
        assertThat(accountResponse.getObject()).isNotNull();
        assertThat(accountResponse.getObject().getAccountId()).isEqualTo(accountId);
        assertThat(accountResponse.getObject().getCustomerId()).isEqualTo(customerId);
    }

    @Test
    public void getInvalidAccountTest() throws Exception {
        String accountId = "INVALID_ID";

        given(accountService.getAccount(accountId))
                .willReturn(Optional.empty());

        MockHttpServletResponse response = mvc.perform(
                get(String.format("/v1/accounts/%s", accountId)).accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
