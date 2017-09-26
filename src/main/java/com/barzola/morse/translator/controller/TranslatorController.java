package com.barzola.morse.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barzola.morse.translator.request.RequestMorse;
import com.barzola.morse.translator.request.RequestText;
import com.barzola.morse.translator.response.ResponseMorse;
import com.barzola.morse.translator.service.TranslatorService;

@RestController
@RequestMapping(value = "/translate")
public class TranslatorController {

	@Autowired
	TranslatorService translatorService;

	@RequestMapping(value = "/decodeBits2Morse", method = RequestMethod.POST)
	public String decodeBits2Morse(@RequestParam String code) {
		return translatorService.decodeBits2Morse(code);
	}

	@RequestMapping(value = "/translate2Human", method = RequestMethod.POST)
	public String translate2Human(@RequestParam String morseCode) {
		return translatorService.translate2Human(morseCode);
	}

	@RequestMapping(value = "/2morse", method = RequestMethod.POST)
	public @ResponseBody ResponseMorse toMorse(@RequestBody RequestText text) {
		ResponseMorse response = new ResponseMorse();

		response.setCode(HttpStatus.OK.value());
		response.setResponse(translatorService.translateToMorse(text));
		return response;
	}

	@RequestMapping(value = "/2text", method = RequestMethod.POST)
	public @ResponseBody ResponseMorse toText(@RequestBody RequestMorse morse) {
		ResponseMorse response = new ResponseMorse();

		response.setCode(HttpStatus.OK.value());
		response.setResponse(translatorService.translateToText(morse));
		return response;
	}

}
