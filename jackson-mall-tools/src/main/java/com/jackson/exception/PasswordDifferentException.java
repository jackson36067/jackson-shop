package com.jackson.exception;
public class PasswordDifferentException extends BaseException{
    public PasswordDifferentException() {}
    public PasswordDifferentException(String message) {
        super(message);
    }
}