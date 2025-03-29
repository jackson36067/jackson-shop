package com.jackson.exception;

public class MemberNotFoundException extends NoAuthenticationException{
    public MemberNotFoundException() {
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
