package com.test.config.message.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessagesUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code) {
		String message = code;
		try {
			message = messageSource.getMessage(code, new Object[] {},
					Locale.getDefault());
		} catch (NoSuchMessageException e) {
			message = code;
		}
		return message;
	}

	public String getMessage(String code, Object[] args, Locale locale) {
		String message = code;
		try {
			message = messageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {
			message = code;
		}
		return message;
	}

	public Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

}
