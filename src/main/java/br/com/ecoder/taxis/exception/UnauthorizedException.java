package br.com.ecoder.taxis.exception;


public class UnauthorizedException extends CustomRuntimeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {}

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

}
