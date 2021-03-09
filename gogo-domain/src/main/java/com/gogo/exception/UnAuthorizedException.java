package com.gogo.exception;

public class UnAuthorizedException extends CustomBaseException {

    public UnAuthorizedException(String message, String info) {
        super(message, info);
    }

    public UnAuthorizedException(String message) {
        super(message);
    }

}
