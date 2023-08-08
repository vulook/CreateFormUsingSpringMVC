package edu.cbsystematics.com.exception;

public class CreateFormUsingSpringMVCRuntimeException extends RuntimeException {

    public CreateFormUsingSpringMVCRuntimeException(String message) {
        super(message);
    }

    public CreateFormUsingSpringMVCRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}