package com.example.rest.exception;

public class LegalCaseNotFoundException extends RuntimeException {

    public LegalCaseNotFoundException() {
    }

    public LegalCaseNotFoundException(String message) {
        super(message);
    }

    public LegalCaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LegalCaseNotFoundException(Throwable cause) {
        super(cause);
    }

    public LegalCaseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
