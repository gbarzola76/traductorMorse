package com.barzola.morse.translator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TranslatorApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}
}
