package com.barzola.morse.translator.errors;

/**
 * Interface para el manejo de los mensajes de error y mensajes al cliente.
 * @author gabriel.barzola
 *
 */
public interface ApiError {

    /**
     * Define un tipo de error.
     *
     * @return type error.
     */
    ErrorTypes getErrorType();

    /**
     * Describe el error para el cliente.
     * 
     * @return el codigo de error para los mensajes.
     */
    String getCodeMessage();
}