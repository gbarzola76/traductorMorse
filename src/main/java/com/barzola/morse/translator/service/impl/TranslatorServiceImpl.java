package com.barzola.morse.translator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.barzola.morse.translator.enums.RequestType;
import com.barzola.morse.translator.exceptions.ApiException;
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
	private static final String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " " };
	private static final String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
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
	private List<Integer> amountsZerosList;
	private List<Integer> amountsList;

	// Inicializo los promedios en 0
	private int averageZeros = 0;
	private int average = 0;

	/*
	 * Primero busca los promedios de los 0s y 1s y luego lo decodifica a codigo
	 * morse
	 */
	@Override
	public String decodeBits2Morse(String code) throws ApiException {
		String character = "";
		String letter = "";
		
		//Busqueda de promedios de 0s y 1s
		getAverage(code);
		
		/*
		 * Recorre los arrays de los 1s y 0s para decodificarlo a codigo morse.
		 * 
		 * Recorre el array de 1s. Si la cantidad es menor o igual al promedio de
		 * los 1 es un "." Si es mayor al promedio entonces es una "-" y los junta.
		 * Luego chequea si la cantidad de 0s siguiente es mayor al promedio de 0s entonces
		 *  es un cambio de letra y agrega un espacio.
		 *
		 */
		for (int i = 0; i < amountsList.size(); i++) {
			character = "";
			if (amountsList.get(i) <= average) {
				character = ".";
			} else {
				character = "-";
			}
			letter += character;
			if (amountsZerosList.get(i + 1) > averageZeros) {
				letter += " ";
			}

		}

		return letter;
	}

	/**
	 * Recorre la secuencia de bits, suma los 0s y los 1s que hay en secuencia y
	 * se guardan esas cantidades en dos arrays (amounts y amountsZeros). Además suma sus cantidades y
	 * guarda el promedio de cada uno.
	 **/
	private void getAverage(String code) {
		amountsList = new ArrayList<Integer>();
		amountsZerosList = new ArrayList<Integer>();

		int counter = 0;
		int sum = 0;
		Boolean booleanN = false;

		int counterZeros = 0;
		int sumZeros = 0;
		Boolean booleanZero = false;

		String[] parts = code.split("");

		for (int i = 0; i < parts.length; i++) {
			/** Si el bit es 0, suma 1 a la cantidad de 0s de la secuencia (counterZeros++)
			* pone en true el booleanZero para "avisar" que había 0s.
			* Luego chequea si hubo 1s y los agrega al array "amountsList".
			**/
			if (parts[i].equals("0")) {
				counterZeros++;
				booleanZero = true;
				
				/**
				 * Si hubo 1s anteriormente, se suman y se agregan al arrays correspondiente.
				 */
				if (booleanN) {
					sum += counter;
					amountsList.add(counter);
					booleanN = false;
					counter = 0;
				}
			} else {
				/** Si el bit es 1, suma 1 a la cantidad de 1s de la secuencia (counter++)
				* pone en true el booleanN para "avisar" que había 1s.
				* Luego chequea si hubo 0s y los agrega al array "amountsZerosList"
				**/
				counter++;
				booleanN = true;
				
				if (booleanZero) {
					sumZeros += counterZeros;
					amountsZerosList.add(counterZeros);
					booleanZero = false;
					counterZeros = 0;
				}				
			}
		}
		
		// Cuando se termina la secuencia de bits por encontrarse un espacio
		// prolongado se agrega la ultima cantidad de 0s al array correspondiente.
		if (booleanZero) {
			sumZeros += counterZeros;
			amountsZerosList.add(counterZeros);
			booleanZero = false;
		}
		
		averageZeros = sumZeros / amountsZerosList.size();
		average = sum / amountsList.size();
	}
	
	/**
	 * Según el tipo de request que se recibe, se selecciona el Mapa del cual se van a buscar
	 * los datos.
	 */
	public String chooseTranslation(String text, RequestType requestType) throws ApiException{
		if(requestType.equals(RequestType.MORSETOTEXT)){
			return translate(text, morseToLetters);
		} else {
			return translate(text, lettersToMorse);
		}
		
	}

	/**
	 * Se hace un split "" si es un texto y con un espacio " " si es un código morse.
	 * luego hace una busqueda en el Mapa correspondiente.
	 * @param text
	 * @param map
	 * @return
	 */
	private String translate(String text, Map<String, String> map) {
		Boolean toMorse = false;
		StringBuilder morseBuilder = new StringBuilder();
		String[] words = null;
		if(map.get("A") != null){
			words = text.split("");
			toMorse = true;
		}
		else
		{
			words = text.split(" ");
		}

		for (String word : words) {
			String letter = map.get(word);
			morseBuilder.append(letter);
			if(toMorse) morseBuilder.append(" ");
		}		

		return morseBuilder.toString();
	}
}
