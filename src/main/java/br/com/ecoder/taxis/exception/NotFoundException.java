package br.com.ecoder.taxis.exception;


public class NotFoundException extends CustomRuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {}

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message) {
        super(message);
    }

}
