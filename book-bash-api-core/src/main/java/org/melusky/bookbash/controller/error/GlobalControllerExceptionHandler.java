package org.melusky.bookbash.controller.error;

import ch.qos.logback.classic.Logger;
import org.melusky.bookbash.model.response.error.ApiErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Created by mikem on 7/10/2017.
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    // Logger
    private final static Logger logger = (Logger) LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
        logger.debug("constraintViolationException: ", ex);
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse illegalArgumentException(IllegalArgumentException ex) {
        logger.debug("illegalArgumentException: ", ex);
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse illegalStateException(IllegalStateException ex) {
        logger.debug("illegalStateException: ", ex);
        return new ApiErrorResponse(400, 4001, ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(NoHandlerFoundException ex) {
        logger.debug("noHandlerFoundException: ", ex);
        return new ApiErrorResponse(404, 4041, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse unknownException(Exception ex) {
        logger.debug("unknownException: ", ex);
        return new ApiErrorResponse(500, 5001, ex.getMessage());
    }

}
