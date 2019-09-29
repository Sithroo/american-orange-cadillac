package io.sithroo.aoc.transactions.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sithroo.aoc.commons.client.HttpClient;
import io.sithroo.aoc.transactions.client.dto.TransactionDTO;

import java.io.IOException;
import java.util.List;

public class TransactionClient extends HttpClient {
    private static final String APPLICATION_BASE_URL = "http://localhost:9091";
    private static final String RESOURCE_ACCOUNT = "/v1/transactions";

    public TransactionClient() {
        super(APPLICATION_BASE_URL);
    }

    public List<TransactionDTO> getTransactions(String accountId) {
        String response = get("/v1/transactions?accountId=" + accountId);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<TransactionDTO>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
