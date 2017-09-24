package com.barzola.morse.translator.service;

import org.springframework.http.ResponseEntity;

import com.barzola.morse.translator.entity.RequestMorse;
import com.barzola.morse.translator.entity.TextEntity;
/**
 * Esta interface decodifica una secuencia de bits a codigo morse, tambien decodifica un codigo morse a texto
 * y codifica un texto a codigo morse.
 *
 * @author Gabriel Barzola <gbarzola@gmail.com>
 *
 */

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

	/**
     * Codifica un texto a codigo morse.
     *
     * @param TextEntity  Entidad de Texto.
     *
     * @return codigo morse.
     */
	public TextEntity translateToMorse(TextEntity text);

	public ResponseEntity<String> translateToMorse(RequestMorse morse);

}
