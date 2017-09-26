package com.barzola.morse.translator.request;

/**
 * Este objeto modela las llamadas al controlador para convertir un codigo morse a texto.
 * @author gabriel.barzola
 *
 */
public class RequestMorse {

	private String text;
	
	/**
	 * Obtiene el valor de la propiedad text.
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Define el valor de la propiedad text.
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
