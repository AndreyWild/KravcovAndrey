package com.senla.util.exceptions;

public class DaoEntityNotFoundException extends RuntimeException {
    public DaoEntityNotFoundException() {
    }

    public DaoEntityNotFoundException(String message) {
        super(message);
    }

    public DaoEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
