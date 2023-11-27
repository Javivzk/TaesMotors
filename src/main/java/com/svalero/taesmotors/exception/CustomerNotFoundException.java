package com.svalero.taesmotors.exception;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException() {
        super("Client not found");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}

