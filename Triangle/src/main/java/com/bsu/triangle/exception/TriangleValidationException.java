package com.bsu.triangle.exception;

public class TriangleValidationException extends Exception {
    public TriangleValidationException() {
        super();
    }

    public TriangleValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriangleValidationException(String message) {
        super(message);
    }

    public TriangleValidationException(Throwable cause) {
        super(cause);
    }

    protected TriangleValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
