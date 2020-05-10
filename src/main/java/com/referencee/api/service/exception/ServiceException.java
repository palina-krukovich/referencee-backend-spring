package com.referencee.api.service.exception;

public class ServiceException extends Exception {
    static final long serialVersionUID = -4511243262408506671L;

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }
}
