package com.barzola.morse.translator.exceptions;

import com.barzola.morse.translator.errors.ApiError;

public class ApiException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4685673211710425074L;
	
	private final ApiError errorType;
    private final String descriptionError;
    
    public ApiException(ApiError apiError, Throwable cause) {
        super(cause);
        this.errorType = apiError;
        this.descriptionError = apiError.getCodeMessage();
    }

	/**
     * @return the errorType
     */
    public ApiError getErrorType() {
        return errorType;
    }

    /**
     * @return the descriptionError
     */
	public String getDescriptionError() {
		return descriptionError;
	}
}
