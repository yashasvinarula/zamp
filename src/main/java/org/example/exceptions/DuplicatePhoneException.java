package org.example.exceptions;

public class DuplicatePhoneException extends Exception {

    private static final String MESSAGE = "Phone number already exists in address book";

    public DuplicatePhoneException() {
        super(MESSAGE);
    }
    public DuplicatePhoneException(String msg) {
        super(msg);
    }


}
