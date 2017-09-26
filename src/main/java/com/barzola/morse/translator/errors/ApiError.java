package com.barzola.morse.translator.errors;

/**
 * Interface para el manejo de los mensajes de error y mensajes al cliente.
 * @author gabriel.barzola
 *
 */
public interface ApiError {

    /**
     * Define a error type.
     *
     * @return the type error.
     */
    ErrorTypes getErrorType();

    /**
     * Describes the error for the client side
     *
     * @return the code error for messages.
     */
    String getCodeMessage();
}