package com.apiautomationframework.utils;

import com.github.javafaker.Faker;

public final class FakerUtils {
	private static final Faker faker = new Faker();

	  static int generateNumber(int min, int max) {
	    return faker.number().numberBetween(min, max);
	  }

	  static String generateFirstname() {
	    return faker.name().firstName();
	  }

	  static String generateLastname() {
	    return faker.name().lastName();
	  }

}
