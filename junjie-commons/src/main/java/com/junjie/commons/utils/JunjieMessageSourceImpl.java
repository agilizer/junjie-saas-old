package com.junjie.commons.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class JunjieMessageSourceImpl  implements   JunjieMessageSource{

	@Autowired
	ResourceBundleMessageSource messageSource;
	@Override
	public String getMessage(String name) {
		return messageSource.getMessage(name, null, null);
	}
	@Override
	public String getMessage(String code, java.lang.Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, locale);
	}
	public ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
