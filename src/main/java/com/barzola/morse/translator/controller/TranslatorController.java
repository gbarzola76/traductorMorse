package com.barzola.morse.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barzola.morse.translator.enums.RequestType;
import com.barzola.morse.translator.exceptions.ApiException;
import com.barzola.morse.translator.request.RequestCode;
import com.barzola.morse.translator.response.ResponseMorse;
import com.barzola.morse.translator.service.TranslatorService;

/**
 * Este controlador representa los endpoints para resolver la decodificación de una
 * secuencia de bits a morse, la decodificación de un código morse a texto, y la
 * codificación de un texto a código morse.
 * @author gabriel.barzola
 *
 */
@RestController
@RequestMapping(value = "/translate")
public class TranslatorController {

	@Autowired
	TranslatorService translatorService;

	/**
	 * Este endpoint recibe un string con la secuencia de bits.
	 * @param code
	 * @return String 
	 */
	@PostMapping(value = "/decodeBits2Morse")
	public String decodeBits2Morse(@RequestParam String code) throws ApiException {
		
		String codeMorse = translatorService.decodeBits2Morse(code);
		
		return codeMorse;
	}

	/**
	 * Este endpoint recibe un string con un código morse.
	 * @param morseCode
	 * @return String.
	 */
	@PostMapping(value = "/translate2Human")
	public String translate2Human(@RequestParam String morseCode) throws ApiException {
		return translatorService.chooseTranslation(morseCode, RequestType.MORSETOTEXT);
	}

	/**
	 * Este endpoint recibe un objeto json ejemplo: {"text" : "HOLA MELI"}
	 * @param text
	 * @return objeto json {"response":".... --- .-.. .-   -- . .-.. .. ","code":200}
	 */
	@PostMapping(value = "/2morse")
	public @ResponseBody ResponseMorse toMorse(@RequestBody RequestCode text) throws ApiException {
		ResponseMorse response = new ResponseMorse();

		response.setCode(HttpStatus.OK.value());
		response.setResponse(translatorService.chooseTranslation(text.getText(), RequestType.TEXTTOMORSE));
		return response;
	}

	/**
	 * Este endpoint recibe un objeto json ejemplo: {"text": ".... --- .-.. .- -- . .-.. .."}
	 * @param text
	 * @return objeto json {"response":"HOLAMELI","code":200}
	 */
	@PostMapping(value = "/2text")
	public @ResponseBody ResponseMorse toText(@RequestBody RequestCode morse) throws ApiException {
		ResponseMorse response = new ResponseMorse();

		response.setCode(HttpStatus.OK.value());
		response.setResponse(translatorService.chooseTranslation(morse.getText(), RequestType.MORSETOTEXT));
		return response;
	}

}
