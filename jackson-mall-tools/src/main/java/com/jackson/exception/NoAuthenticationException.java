package com.jackson.exception;

public class NoAuthenticationException extends RuntimeException {
    public NoAuthenticationException() {}
    public NoAuthenticationException(String message) {
        super(message);
    }
}