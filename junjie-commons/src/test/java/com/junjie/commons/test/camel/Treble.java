package com.junjie.commons.test.camel;

import org.springframework.stereotype.Service;

@Service(value = "multiplier")
public class Treble implements Multiplier {

	public int multiply(final int originalNumber) {
		return originalNumber * 3;
	}

}