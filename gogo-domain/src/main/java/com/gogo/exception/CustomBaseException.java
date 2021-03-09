package com.gogo.exception;

import lombok.Getter;

@Getter
public class CustomBaseException extends RuntimeException {

    private String info;

    public CustomBaseException(String message, String info) {
        super(message);
        this.info = info;
    }

    public CustomBaseException(String message) {
        super(message);
    }

}
