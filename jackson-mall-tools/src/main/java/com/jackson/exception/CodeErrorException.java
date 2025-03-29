package com.jackson.exception;

public class CodeErrorException extends NoAuthenticationException{
    public CodeErrorException() {
    }

    public CodeErrorException(String message) {
        super(message);
    }
}
