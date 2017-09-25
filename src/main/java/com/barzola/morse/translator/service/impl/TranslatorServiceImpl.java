package com.barzola.morse.translator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.barzola.morse.translator.request.RequestMorse;
import com.barzola.morse.translator.request.RequestText;
import com.barzola.morse.translator.service.TranslatorService;

/**
 * Capa de negocio del traductor.
 * 
 * @author gabriel.barzola
 *
 */
@Service
public class TranslatorServiceImpl implements TranslatorService {

	// Defino los arrays de las letras y los caracteres del codigo morse.
	private static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " " };
	private static String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
			".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
			"-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", " " };

	/*
	 * Mapeo de los arrays de letras y caracteres morse para la codificación y
	 * decodificación del código morse a letras y viceversa.
	 */
	private final Map<String, String> morseToLetters = IntStream.range(0, morse.length).boxed()
			.collect(Collectors.toMap(i -> morse[i], i -> letters[i]));
	private final Map<String, String> lettersToMorse = IntStream.range(0, letters.length).boxed()
			.collect(Collectors.toMap(i -> letters[i], i -> morse[i]));

	// Arrays donde guardo la cantidad de 1s y 0s despues de recorrer la
	// secuencia de bits
	private List<Integer> amountsZeros;
	private List<Integer> amounts;

	// Inicializo las variables de los maximos y los minimos de los 0s y los 1s
	// Los minimos los inicializo en 10 para poder ir bajandolos de acuerdo al
	// minimo encontrado.
	private int minZeros = 10;
	private int maxZeros = 0;
	private int minNumbers = 10;
	private int maxNumbers = 0;

	// Inicializo los promedios en 0
	private int averageZeros = 0;
	private int average = 0;

	/*
	 * Primero busca los promedios de los 0s y 1s y luego lo decodica a codigo
	 * morse
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.barzola.morse.translator.service.TranslatorService#decodeBits2Morse(
	 * java.lang. String)
	 */
	@Override
	public String decodeBits2Morse(String code) {
		getAverage(code);

		String morseCode = decodeToMorse();

		return morseCode;
	}

	/*
	 * Recorre los arrays de los 1s y 0s para decodificarlo a codigo morse.
	 * 
	 * Recorre el array de 1s. Si la cantidad es menor o igual al promedio de
	 * los 1 es un "." Si es mayor al promedio entonces es una "-" y los junta.
	 * Luego chequea si la cantidad de 0s siguiente es mayor al promedio de 0s entonces
	 *  es un cambio de letra y agrega un espacio.
	 *
	 */
	private String decodeToMorse() {
		String character = "";
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

	/*
	 * Recorre la secuencia de bits, suma los 0s y los 1s que hay en secuencia y
	 * se guardan esas cantidades en dos arrays. Además suma sus cantidades y
	 * guarda el promedio de cada uno.
	 */
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
			/*Si el bit es 0, suma 1 a la cantidad de 0s de la secuencia (amount++)
			* Suma 
			**/
			if (parts[i].equals("0")) {
				counterZeros++;
				booleanZero = true;
				if (counterZeros <= minZeros)
					minZeros = counterZeros;
				if (counterZeros > maxZeros)
					maxZeros = counterZeros;
				
				if (booleanN) {
					amount++;
					sum += counter;
					amounts.add(counter);
					booleanN = false;
				}
				counter = 0;
			} else {
				counter++;
				booleanN = true;
				if (counter <= minNumbers)
					minNumbers = counter;
				if (counter > maxNumbers)
					maxNumbers = counter;
				
				if (booleanZero) {
					amountZeros++;
					sumZeros += counterZeros;
					amountsZeros.add(counterZeros);
					booleanZero = false;
				}
				counterZeros = 0;
			}
		}
		// Cuando se termina la secuencia de bits por encontrarse un espacio
		// prolongado
		// se agrega la ultima cantidad de 0s al array correspondiente.
		if (booleanZero) {
			amountZeros++;
			sumZeros += counterZeros;
			amountsZeros.add(counterZeros);
			booleanZero = false;
		}
		averageZeros = sumZeros / amountZeros;
		average = sum / amount;
	}

	/*
	 * Recorro el String que recibo y los separo, y luego busco en el array
	 * morseToLetters la letra correspondiente al caracter encontrado.
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.barzola.morse.translator.service.TranslatorService#translate2Human(
	 * java. lang.String)
	 */
	@Override
	public String translate2Human(String morseCode) {

		StringBuilder wordBuilder = new StringBuilder();
		String[] codes = morseCode.trim().split(" ");

		for (String code : codes) {
			String letter = morseToLetters.get(code);
			wordBuilder.append(letter);
		}

		return wordBuilder.toString();
	}

	/**
	 * Recorro el String que recibo y los separo, y luego busco en el array
	 * morseToLetters la letra correspondiente al caracter encontrado.
	 */
	@Override
	public String translateToMorse(RequestText text) {
		RequestText requestText = new RequestText();

		StringBuilder morseBuilder = new StringBuilder();
		String[] words = text.getText().toString().trim().split("");

		for (String word : words) {
			String letter = lettersToMorse.get(word);
			morseBuilder.append(letter).append(" ");
		}

		requestText.setText(morseBuilder.toString());

		return requestText.getText();
	}

	/**
	 * Recorro el codigo morse recibido y busco en el array morseToLetters la
	 * letra que corresponde al código.
	 */
	@Override
	public String translateToText(RequestMorse morse) {
		RequestMorse requestMorse = new RequestMorse();

		StringBuilder wordBuilder = new StringBuilder();
		String[] codes = morse.getText().trim().split(" ");

		for (String code : codes) {
			String letter = morseToLetters.get(code);
			wordBuilder.append(letter);
		}

		requestMorse.setText(wordBuilder.toString());

		return requestMorse.getText();
	}

}
