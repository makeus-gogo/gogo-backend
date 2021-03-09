package com.gogo.exception;

public class NotFoundException extends CustomBaseException {

    public NotFoundException(String message, String info) {
        super(message, info);
    }

    public NotFoundException(String message) {
        super(message);
    }

}
