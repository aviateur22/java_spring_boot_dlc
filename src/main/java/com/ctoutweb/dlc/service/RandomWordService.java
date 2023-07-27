package com.ctoutweb.dlc.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomWordService {
		
	public String generateRandom(int wordLength) {
		Random rand = new Random();
		String str = rand.ints(48, 123)
				.filter(num->(num < 58 || num > 64) && (num < 91 || num > 96))
				.limit(5)
				.mapToObj(c->(char) c).collect(StringBuffer::new , StringBuffer::append, StringBuffer::append)
				.toString();
		
		return str.toLowerCase();
	}
	

	
	public String encodeBase64(String text) {
		return encodeBase64(text);
	}
}
