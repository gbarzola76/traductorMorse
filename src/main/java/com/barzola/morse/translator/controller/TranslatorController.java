package com.barzola.morse.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.barzola.morse.translator.entity.RequestMorse;
import com.barzola.morse.translator.entity.TextEntity;
import com.barzola.morse.translator.service.TranslatorService;

@RestController
@RequestMapping(value = "/translate")
public class TranslatorController {
	
	@Autowired
	TranslatorService translatorService;
	
	@ResponseStatus( HttpStatus.OK )
	@RequestMapping(value = "/decodeBits2Morse", method = RequestMethod.POST)
	public String decodeBits2Morse(@RequestParam String code){		
		return translatorService.decode(code);
	}
	
	@RequestMapping(value = "/translate2Human", method = RequestMethod.POST)
	public String translate2Human(@RequestParam String morseCode){
		return translatorService.translate(morseCode);
	}
	
	@RequestMapping(value = "/translate2Morse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TextEntity translate2Morse(@RequestBody TextEntity text) {
		return translatorService.translateToMorse(text);
	}
	
	@RequestMapping(value = "/2text", method = RequestMethod.POST)
	public ResponseEntity<String> translate2Morse (@RequestBody RequestMorse morse) {
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>("my response body", HttpStatus.OK);
		return responseEntity;
//		return translatorService.translateToMorse(morse);
		
	}
}
