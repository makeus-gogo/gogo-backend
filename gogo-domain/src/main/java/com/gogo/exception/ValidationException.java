package com.gogo.exception;

public class ValidationException extends CustomBaseException {

    public ValidationException(String message, String info) {
        super(message, info);
    }

    public ValidationException(String message) {
        super(message);
    }

}
