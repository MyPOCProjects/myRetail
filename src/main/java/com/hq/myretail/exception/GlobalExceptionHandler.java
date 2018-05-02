
package com.hq.myretail.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERRORMESSAGE = "Internal application error";
    private static final String NOTFOUNDMESSAGE = "No product found with given Id";

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Throwable ex, HttpServletRequest request,
            HttpServletResponse response) {
        return createErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, ERRORMESSAGE);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleProductNotFoundException(final Throwable ex,
            HttpServletRequest request, HttpServletResponse response) {
        return createErrorResponse(ex, request, HttpStatus.NOT_FOUND, NOTFOUNDMESSAGE);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidRequestException(final Throwable ex,
            HttpServletRequest request, HttpServletResponse response) {
        return createErrorResponse(ex, request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<ExceptionResponse> createErrorResponse(Throwable ex, HttpServletRequest request,
            HttpStatus httpStatus, String errorMessage) {
        logger.error("Error in calling the resource : {}", request.getRequestURI());
        logger.error("Exception caught in GlobalExceptionHandler : {}", ex);

        ExceptionResponse exceptionResponse = null;

        try {
            exceptionResponse = new ExceptionResponse(
                    new Date(),
                    httpStatus.value(),
                    errorMessage,
                    request.getRequestURI(),
                    null);
        } catch (final Exception e) {
            logger.error("Error in GlobalResponseEntityExceptionHandler:handleAllExceptions method {}", e);
        }

        return new ResponseEntity<>(exceptionResponse, httpStatus);
    }

}
