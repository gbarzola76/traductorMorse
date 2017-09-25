package com.barzola.morse.translator.service;

import com.barzola.morse.translator.request.RequestMorse;
import com.barzola.morse.translator.request.RequestText;

/**
 * Esta interface decodifica una secuencia de bits a codigo morse, tambien
 * decodifica un codigo morse a texto y codifica un texto a codigo morse.
 *
 * @author Gabriel Barzola <gbarzola@gmail.com>
 *
 */
public interface TranslatorService {

	/**
	 * Decodifica una secuencia de bits a codigo morse.
	 *
	 * @param code
	 *            secuencia de bits.
	 *
	 * @return codigo morse.
	 */
	public String decodeBits2Morse(String code);

	/**
	 * Decodifica un codigo morse a texto.
	 *
	 * @param morseCode
	 *            codigo morse.
	 *
	 * @return texto traducido.
	 */
	public String translate2Human(String morseCode);

	/**
	 * Codifica un texto a codigo morse.
	 *
	 * @param RequestText
	 *            Entidad de Texto.
	 *
	 * @return codigo morse.
	 */
	public String translateToMorse(RequestText text);

	/**
	 * Codifica un codigo morse a texto.
	 *
	 * @param RequestMorse
	 *            codigo morse.
	 *
	 * @return text.
	 */
	public String translateToText(RequestMorse morse);

}
