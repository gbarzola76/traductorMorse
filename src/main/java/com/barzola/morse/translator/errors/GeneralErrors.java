package com.barzola.morse.translator.errors;

/**
 * Este enum describe los errores para las excepciones de la aplicación.
 * 
 * @author gabriel.barzola
 *
 */
public enum GeneralErrors implements ApiError {

	    // Described a general errors
	    UNSPECTED_ERROR(ErrorTypes.GENERAL_ERROR, "system.error.unspected"),
	    ARGUMENTOS_INVALIDOS(ErrorTypes.GENERAL_ERROR, "user.error.arguments"),
	    // Described a system errors
	    SYSTEM_ERROR(ErrorTypes.SYSTEM_ERROR, "system.error.generic"),
	    SYSTEM_NOSTATUS(ErrorTypes.SYSTEM_ERROR, "system.error.noStatus"),
	    SYSTEM_INVALID_MESSAGE(ErrorTypes.SYSTEM_ERROR, "system.error.invalid.message"),
	    SYSTEM_MEDIA_TYPE(ErrorTypes.SYSTEM_ERROR, "system.error.media.type"),
	    SYSTEM_UNSUPPORTED(ErrorTypes.SYSTEM_ERROR, "system.error.unsupported.method"),
	    SYSTEM_REST(ErrorTypes.SYSTEM_ERROR, "system.error.rest"),
	    // Described a business errors
	    USER_INVALID_MESSAGE(ErrorTypes.BUSINESS_ERROR, "user.error.invalid.message"),
	    USER_UNSUPPORTED(ErrorTypes.BUSINESS_ERROR, "user.error.unsupported.method"),
	    USER_BINDING(ErrorTypes.BUSINESS_ERROR, "user.error.binding"),
	    USER_MEDIA_TYPE(ErrorTypes.BUSINESS_ERROR, "user.error.media.type"),
	    MISSING_GALAXY(ErrorTypes.BUSINESS_ERROR, "user.error.inexistent.galaxy"),
	    FULL_GALAXY(ErrorTypes.BUSINESS_ERROR, "user.error.max.planets"),
	    MISSING_PLANETS(ErrorTypes.BUSINESS_ERROR, "user.error.min.planets"),
	    NOTFOUND_DAY(ErrorTypes.BUSINESS_ERROR, "user.error.notfound.day");

	    private final ErrorTypes tipoError;

	    private final String codeMessage;

	    private GeneralErrors(ErrorTypes tipoError, String codeMessage) {
	        this.tipoError = tipoError;
	        this.codeMessage = codeMessage;
	    }

	    @Override
	    public String getCodeMessage() {
	        return codeMessage;
	    }

	    @Override
	    public ErrorTypes getErrorType() {
	        return tipoError;
	    }

	}
