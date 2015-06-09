package br.com.ecoder.taxis.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ecoder.taxis.exception.BadRequestException;
import br.com.ecoder.taxis.exception.CustomRuntimeException;
import br.com.ecoder.taxis.exception.ForbiddenException;
import br.com.ecoder.taxis.exception.InternalServerErrorException;
import br.com.ecoder.taxis.exception.NotFoundException;
import br.com.ecoder.taxis.exception.UnauthorizedException;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, ex.getBindingResult().getAllErrors(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, ex.getAllErrors(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOG.error(ex.getMessage(), ex);

        return handleSpringRequestException(ex);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        LOG.error(ex.getMessage(), ex);

        return handleSpringRequestException(ex);
    }

    private ResponseEntity<Object> handleSpringRequestException(Exception ex) {

        InternalServerErrorException error = new InternalServerErrorException();

        return new ResponseEntity<Object>(handleException(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
    public Object handleUnauthorizedException(UnauthorizedException ex) {

        LOG.warn(ex.getMessage(), ex);

        return handleException(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public Object handleBadRequestError(BadRequestException ex) {

        LOG.warn(ex.getMessage(), ex);

        return handleException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Object handleForbiddenException(ForbiddenException ex) {

        LOG.warn(ex.getMessage(), ex);

        return handleException(ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public Object handleNotFoundException(NotFoundException ex) {

        LOG.warn(ex.getMessage(), ex);

        return handleException(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    public Object handleInternalServerErrorException(InternalServerErrorException ex) {

        LOG.error(ex.getMessage(), ex);

        return handleException(ex);
    }

    private Object handleException(CustomRuntimeException ex) {
        return ex.getMessage();
    }

}
