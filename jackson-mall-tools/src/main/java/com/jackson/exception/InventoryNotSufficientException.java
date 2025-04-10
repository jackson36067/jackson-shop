package com.jackson.exception;

public class InventoryNotSufficientException extends BaseException {

    public InventoryNotSufficientException() {
    }

    public InventoryNotSufficientException(String message) {
        super(message);
    }
}
