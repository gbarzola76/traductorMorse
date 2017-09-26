package com.barzola.morse.translator.errors;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.barzola.morse.translator.exceptions.ApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Cualquier {@link ApiException} es trasladada a esta clase para ser serializada y reportada al usuario.
 * 
 * @author gabriel.barzola
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class RestError {

    /**
     * The HTTP status code reported to the client as a result of the operation
     */
    @JsonSerialize(using = HttpStatusCodeSerializer.class)
    public HttpStatus status;

    /**
     * Why the API call failed
     */
    private ErrorTypes errorTypes;

    private String path;

    private Date time;

    /**
     * A verbose description of the problem
     */
    private String description;

    public RestError(HttpStatus status, ErrorTypes errorTypes, String exceptionDescription, String apiErrorDescription,
            Date time, String path) {
        this.status = status;
        this.errorTypes = errorTypes;
        this.description = StringUtils.isNotEmpty(exceptionDescription) ?
                exceptionDescription + " - " + apiErrorDescription : apiErrorDescription;
        this.time = time;
        this.path = path;
    }

}