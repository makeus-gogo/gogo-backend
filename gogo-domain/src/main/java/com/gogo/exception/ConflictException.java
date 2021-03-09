package com.gogo.exception;

public class ConflictException extends CustomBaseException {

    public ConflictException(String message, String info) {
        super(message, info);
    }

    public ConflictException(String message) {
        super(message);
    }

}
