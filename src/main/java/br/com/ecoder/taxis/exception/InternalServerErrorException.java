package br.com.ecoder.taxis.exception;

public class InternalServerErrorException extends CustomRuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException() {}

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

}
