package com.junjie.commons.utils;

import java.util.Locale;

public interface JunjieMessageSource {
	String getMessage(String name);
	String getMessage(String code, Object[] args, Locale locale) ;
	

}
