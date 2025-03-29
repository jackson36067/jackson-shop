package com.jackson.exception;

public class AccountLockException extends NoAuthenticationException {
    public AccountLockException() {
    }

    public AccountLockException(String message) {
        super(message);
    }
}
