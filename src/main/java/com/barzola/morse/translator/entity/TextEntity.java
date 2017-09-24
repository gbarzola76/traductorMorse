package com.barzola.morse.translator.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9183464022529501729L;
	
	@JsonProperty("text")
	private String text;

	@Override
	public String toString() {
		return super.toString();
	}

	public String getTexto() {
		return text;
	}

	public void setTexto(String texto) {
		this.text = texto;
	}	

}
