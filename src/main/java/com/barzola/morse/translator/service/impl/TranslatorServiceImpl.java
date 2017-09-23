package com.barzola.morse.translator.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barzola.morse.translator.service.TranslatorService;

@Service
public class TranslatorServiceImpl implements TranslatorService {

	private static final Logger LOGGER 	= LoggerFactory.getLogger(TranslatorServiceImpl.class);

	private static String[] letters		= { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " " };
	private static String[] morse		= { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", " " };
	
	private final Map<String, String> morseToLetters = new HashMap<String, String>();
	private final Map<String, String> lettersToMorse = new HashMap<String, String>();
	private List<Integer> amountsZeros;
	private List<Integer> amounts;
	private int minZeros = 10;
	private int maxZeros = 0;
	private int averageZeros = 0;

	private int minNumbers = 10;
	private int maxNumbers = 0;
	private int average = 0;
	private String morseCode = "";

	@Override
	public String decode(String code) {
		getAverage(code);

		morseCode = decodeToMorse(code);

		return morseCode;
	}

	private void createDictionary() {
		for (int i = 0; i < letters.length  &&  i < morse.length; i++) {
			lettersToMorse.put(letters[i], morse[i]);
			morseToLetters.put(morse[i], letters[i]);
        }		
	}

	private String decodeToMorse(String code) {
		String character;
		String letter = "";

		for (int i = 0; i < amounts.size(); i++) {
			character = "";
			if (amounts.get(i) <= average) {
				character = ".";
			} else {
				character = "-";
			}
			letter += character;
			if (amountsZeros.get(i + 1) > averageZeros) {
				letter += " ";
			}

		}

		return letter;
	}

	private void getAverage(String code) {
		amounts = new ArrayList<Integer>();
		amountsZeros = new ArrayList<Integer>();
		int counter = 0;
		int sum = 0;
		int amount = 0;
		Boolean booleanN = false;

		int counterZeros = 0;
		int sumZeros = 0;
		int amountZeros = 0;
		Boolean booleanZero = false;

		String[] parts = code.split("");

		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("0") || parts[i].length() == i) {
				if (booleanN) {
					amount++;
					sum += counter;
					amounts.add(counter);
					booleanN = false;
				}
				counter = 0;

				counterZeros++;
				booleanZero = true;
				if (counterZeros <= minZeros) minZeros = counterZeros;
				if (counterZeros > maxZeros) maxZeros = counterZeros;
			} else {
				if (booleanZero) {
					amountZeros++;
					sumZeros += counterZeros;
					amountsZeros.add(counterZeros);
					booleanZero = false;
				}
				counterZeros = 0;

				counter++;
				booleanN = true;
				if (counter <= minNumbers) minNumbers = counter;
				if (counter > maxNumbers) maxNumbers = counter;
			}
		}
		if (booleanZero) {
			amountZeros++;
			sumZeros += counterZeros;
			amountsZeros.add(counterZeros);
			booleanZero = false;
		}
		averageZeros = sumZeros / amountZeros;
		average = sum / amount;
		LOGGER.info("Min: " + minZeros);
		LOGGER.info("Max: " + maxZeros);
		LOGGER.info("AVG0: " + averageZeros);
		LOGGER.info("AVG1: " + average);
		LOGGER.info("size 1: " + amounts.size());
		LOGGER.info("size 2: " + amountsZeros.size());
	}

	@Override
	public String translate(String morseCode) {
		createDictionary();
		
		StringBuilder wordBuilder = new StringBuilder();
		String[] codes = morseCode.trim().split(" ");
		
		 for (String code : codes) {
			 String letter = morseToLetters.get(code);
			 wordBuilder.append(letter);
		 }
		
		return wordBuilder.toString();
	}

	@Override
	public String translateToMorse(String text) {
		createDictionary();
		
		StringBuilder morseBuilder = new StringBuilder();
		String[] words = text.trim().split("");
		
		for (String word : words) {
			String letter = lettersToMorse.get(word);
			morseBuilder.append(letter).append(" ");
		}
		
		return morseBuilder.toString();
	}

}
