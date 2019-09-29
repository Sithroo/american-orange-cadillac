package io.sithroo.aoc.accounts.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException(String message) {
        super(message);
    }
}
