package com.barzola.morse.translator.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Maneja los mensajes para la aplicación
 * 
 * @author gabriel.barzola
 *
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.US);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

}