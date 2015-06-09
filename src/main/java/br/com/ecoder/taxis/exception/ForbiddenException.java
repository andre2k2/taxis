package br.com.ecoder.taxis.exception;


public class ForbiddenException extends CustomRuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {}

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message) {
        super(message);
    }

}
