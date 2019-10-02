package io.sithroo.aoc.accounts.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sithroo.aoc.commons.accounts.dto.AccountDTO;
import io.sithroo.aoc.commons.client.HttpClient;

import java.io.IOException;

public class AccountClient extends HttpClient {
    private static final String APPLICATION_BASE_URL = "http://localhost:9090";
    private static final String RESOURCE_ACCOUNT = "/v1/accounts";

    public AccountClient() {
        super(APPLICATION_BASE_URL);
    }

    public AccountDTO createAccount(final String customerId, final Double initialAmount) {
        String accountRequest = String.format("{\"customerId\": \"%s\",  \"initialAmount\": %s, \"accountType\": \"CURRENT\"}", customerId, initialAmount);
        String response = post(RESOURCE_ACCOUNT, accountRequest);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, AccountDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
