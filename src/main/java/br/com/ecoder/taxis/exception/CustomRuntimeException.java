package br.com.ecoder.taxis.exception;

public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomRuntimeException() {}

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
