package com.apolinario.elasticsearch;

import java.util.Date;
import java.util.Random;

import com.github.javafaker.Faker;

public class DocumentFactor {

	private final Random rand = new Random();
	private final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890  ";

	public String createFakeName() {
		return (new Faker()).name().fullName();
	}

	public String createFakeText() {
		return createFakeText(254);
	}

	public String createFakeText(int totalChars) {

	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(totalChars);
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	    }
	    return builder.toString();
	}
	
	public double randonNumber() {
		return rand.nextInt(500)+1;
	}

	public Date createDate() {
		return new Date();
	}
}
