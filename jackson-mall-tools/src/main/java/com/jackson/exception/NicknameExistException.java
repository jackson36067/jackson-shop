package com.jackson.exception;

public class NicknameExistException extends BaseException {
    public NicknameExistException(String message) {
        super(message);
    }

    public NicknameExistException() {
    }
}
