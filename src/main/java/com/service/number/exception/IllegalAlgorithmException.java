package com.service.number.exception;

public class IllegalAlgorithmException extends RuntimeException {

    public IllegalAlgorithmException() {
        super();
    }

    public IllegalAlgorithmException(String s) {
        super(s);
    }

    public IllegalAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAlgorithmException(Throwable cause) {
        super(cause);
    }

}
