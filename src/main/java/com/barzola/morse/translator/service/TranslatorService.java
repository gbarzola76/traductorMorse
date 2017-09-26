package com.barzola.morse.translator.service;

import com.barzola.morse.translator.enums.RequestType;
import com.barzola.morse.translator.exceptions.ApiException;

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
	public String decodeBits2Morse(String code) throws ApiException;

	/**
	 * 
	 * @param text
	 * @param requestType
	 * @return
	 */
	public String chooseTranslation(String text, RequestType requestType) throws ApiException;

}
