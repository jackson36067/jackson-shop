package com.jackson.exception;

public class MemberNotFoundException extends BaseException{
    public MemberNotFoundException() {
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
