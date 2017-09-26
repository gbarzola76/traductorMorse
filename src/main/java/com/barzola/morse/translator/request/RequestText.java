package com.barzola.morse.translator.request;

/**
 * Este objeto modela las llamadas al controlador para convertir un texto a morse.
 * @author gabriel.barzola
 *
 */
public class RequestText {

	private String text;

	/**
	 * Obtiene el valor de la propiedad text.
	 * @return 
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
