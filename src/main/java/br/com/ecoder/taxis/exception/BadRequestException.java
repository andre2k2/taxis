package br.com.ecoder.taxis.exception;


public class BadRequestException extends CustomRuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException() {}

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

}
