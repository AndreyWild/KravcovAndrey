package com.senla.util.exceptions;

public class ServiceEntityNotFoundException extends RuntimeException {
    public ServiceEntityNotFoundException() {
    }

    public ServiceEntityNotFoundException(String message) {
        super(message);
    }

    public ServiceEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
