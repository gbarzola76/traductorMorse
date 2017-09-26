package com.barzola.morse.translator.response;

/**
 * DTO del response.
 * @author gabriel.barzola
 *
 */
public class ResponseMorse {

	private String response;
	private int code;

	/**
	 * Obtiene el valor de la propiedad response.
	 * @return response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * Define el valor de la propiedad response.
	 * @param response
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * Obtiene el valor de la propiedad code.
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Define el valor de la propiedad code.
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
