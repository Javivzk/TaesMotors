package com.svalero.taesmotors.exception;

public class ExtraNotFoundException extends Exception{

    public ExtraNotFoundException() {
        super("Extra not found");
    }

    public ExtraNotFoundException(String message) {
        super(message);
    }
}

