package com.senla.util.exceptions;

public class EntityParsingException extends RuntimeException {
    public EntityParsingException() {
    }

    public EntityParsingException(String message) {
        super(message);
    }

    public EntityParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityParsingException(Throwable cause) {
        super(cause);
    }
}
