package com.barzola.morse.translator.service;

public interface TranslatorService {
	
	/**
     * Decodifica una secuencia de bits a codigo morse.
     *
     * @param code    secuencia de bits.
     *
     * @return codigo morse.
     */
	public String decode(String code);

	/**
     * Decodifica un codigo morse a texto.
     *
     * @param morseCode    codigo morse.
     *
     * @return texto traducido.
     */
	public String translate(String morseCode);

	public String translateToMorse(String text);

}
