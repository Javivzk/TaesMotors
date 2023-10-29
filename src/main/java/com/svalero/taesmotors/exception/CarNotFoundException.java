package com.svalero.taesmotors.exception;

public class CarNotFoundException extends Exception{

    public CarNotFoundException() {
        super("Car not found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}

