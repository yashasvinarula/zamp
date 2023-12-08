package org.example.exceptions;

public class InvalidInputException extends Exception {

    private static final String MESSAGE = "Invalid input format";

    public InvalidInputException() {
        super(MESSAGE);
    }
    public InvalidInputException(String msg) {
        super(MESSAGE + " - " + msg);
    }

}
