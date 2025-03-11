package com.jackson.exception;

public class AccountLockException extends BaseException {
    public AccountLockException() {
    }

    public AccountLockException(String message) {
        super(message);
    }
}
