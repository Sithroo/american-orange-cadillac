package io.sithroo.aoc.transactions.util;

import java.util.UUID;

public class TransactionUtil {
    public static String getNextTransactionid() {
        return UUID.randomUUID().toString();
    }
}
