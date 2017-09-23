package com.barzola.morse.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barzola.morse.translator.service.TranslatorService;

@RestController
@RequestMapping(value = "/api/translateMorse")
public class TranslatorController {
	
	@Autowired
	TranslatorService translatorService;
	
	@RequestMapping(value = "/decodeBits2Morse", method = RequestMethod.GET)
	public String decodeBits2Morse(@RequestParam String code){		
		return translatorService.decode(code);
	}
	
	@RequestMapping(value = "/translate2Human", method = RequestMethod.GET)
	public String translate2Human(@RequestParam String morseCode){
		return translatorService.translate(morseCode);
	}
	
	@RequestMapping(value = "/translate2Morse", method = RequestMethod.GET)
	public String translate2Morse(@RequestParam String text){
		return translatorService.translateToMorse(text);
	}
}
