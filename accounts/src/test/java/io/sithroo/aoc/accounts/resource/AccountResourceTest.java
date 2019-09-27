package io.sithroo.aoc.accounts.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountResource.class)
public class AccountResourceTest {
    @MockBean
    private AccountService accountService;

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

        Account createdAccount = new Account(accountId, customerId, initialAmount);
        given(accountService.createAccount(new Account(customerId, initialAmount)))
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
    }

//    @Test
    public void createAccountTestError() throws Exception {
        String customerId = "INVALID_CUSTOMER";
        Double initialAmount = 5000.65;

        given(accountService.createAccount(new Account(customerId, initialAmount)))
                .willThrow(new AccountServiceException("Invalid customer"));

        AccountDTO accountRequest = new AccountDTO(customerId, initialAmount);
        MockHttpServletResponse response = mvc.perform(
                post("/v1/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(json.write(accountRequest).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
