package com.barzola.morse.translator.errors;


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