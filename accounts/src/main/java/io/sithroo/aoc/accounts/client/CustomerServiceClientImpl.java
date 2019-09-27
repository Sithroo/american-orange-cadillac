package io.sithroo.aoc.accounts.client;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceClientImpl implements CustomerServiceClient {
    @Override
    public Boolean validCustomer(String customerId) {
//      FIXME: Interim assumption that given customers are always exists and valid
        return true;
    }
}
