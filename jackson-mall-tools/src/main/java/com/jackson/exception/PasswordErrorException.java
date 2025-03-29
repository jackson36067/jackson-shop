package com.jackson.exception;

public class PasswordErrorException extends NoAuthenticationException{
    public PasswordErrorException() {
    }

    public PasswordErrorException(String message) {
        super(message);
    }
}
