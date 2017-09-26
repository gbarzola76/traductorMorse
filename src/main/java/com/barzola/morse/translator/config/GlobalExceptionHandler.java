package com.barzola.morse.translator.config;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.barzola.morse.translator.errors.ErrorTypes;
import com.barzola.morse.translator.errors.GeneralErrors;
import com.barzola.morse.translator.errors.RestError;
import com.barzola.morse.translator.exceptions.ApiException;

/**
 * Provee el manejo general de las excepciones del controlador. 
 * @author gabriel.barzola
 *
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private Messages messages;

    @ExceptionHandler(value = ApiException.class)
    protected ResponseEntity<RestError> handleApiError(ApiException ex, HttpServletRequest request) {

        LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);

        HttpStatus status;
        if (responseStatus != null) {
            status = responseStatus.value();
        } else {
            LOGGER.error(messages.get(GeneralErrors.SYSTEM_NOSTATUS.getCodeMessage()));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        RestError error = new RestError(status, ex.getErrorType().getErrorType(), messages.get(ex.getDescriptionError()),
                ex.getErrorType().getCodeMessage(), Calendar.getInstance().getTime(), request.getPathInfo());

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<RestError> handleInvalidMessage(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_INVALID_MESSAGE.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.BAD_REQUEST, ErrorTypes.GENERAL_ERROR, ex.getMessage(),
                messages.get(GeneralErrors.USER_INVALID_MESSAGE.getCodeMessage()), Calendar.getInstance().getTime(),
                request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<RestError> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_MEDIA_TYPE.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.BAD_REQUEST, ErrorTypes.GENERAL_ERROR, ex.getMessage(),
                messages.get(GeneralErrors.USER_MEDIA_TYPE.getCodeMessage()), Calendar.getInstance().getTime(), request.
                getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<RestError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_UNSUPPORTED.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.BAD_REQUEST, ErrorTypes.GENERAL_ERROR, ex.getMessage(),
                messages.get(GeneralErrors.USER_UNSUPPORTED.getCodeMessage()), Calendar.getInstance().getTime(),
                request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BindException.class)
    protected ResponseEntity<RestError> handleBindingException(BindException ex, HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.BAD_REQUEST, ErrorTypes.GENERAL_ERROR, ex.getMessage(),
                messages.get(GeneralErrors.USER_BINDING.getCodeMessage()),
                Calendar.getInstance().getTime(), request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<RestError> handleArgumentNotValidException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_REST.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.BAD_REQUEST, ErrorTypes.GENERAL_ERROR, ex.getMessage(),
                messages.get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
                request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestError> handle(Exception ex, HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);
        RestError error = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorTypes.GENERAL_ERROR,
                ex.getMessage() != null ? ex.getMessage() : messages.
                get(GeneralErrors.UNSPECTED_ERROR.getCodeMessage()),
                messages.
                        get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
                request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<RestError> handleGeneralError(Exception ex, HttpServletRequest request) {
        LOGGER.error(messages.get(GeneralErrors.SYSTEM_ERROR.getCodeMessage()), request.getRequestURI(), ex);

        RestError error = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorTypes.GENERAL_ERROR,
                ex.getMessage() != null ? ex.getMessage() : "An unexpected error has ocurred",
                messages.get(GeneralErrors.ARGUMENTOS_INVALIDOS.getCodeMessage()), Calendar.getInstance().getTime(),
                request.getPathInfo());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
