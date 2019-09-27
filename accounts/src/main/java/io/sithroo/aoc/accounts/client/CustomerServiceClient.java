package io.sithroo.aoc.accounts.client;

/**
 * CustomerServiceClient provides a proxy to allow access to Customer Service.
 */
public interface CustomerServiceClient {
    Boolean validCustomer(final String customerId);
}
