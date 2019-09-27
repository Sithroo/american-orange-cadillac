package io.sithroo.aoc.transactions.event;

public class PaymentTransactionEvent extends TransactionEvent {
    private final String merchantId;

    public PaymentTransactionEvent(String merchantId) {
        this.merchantId = merchantId;
    }

    public PaymentTransactionEvent(String accountId, Double amount, Long transactionTime, String merchantId) {
        super(accountId, amount, transactionTime);
        this.merchantId = merchantId;
    }
}
