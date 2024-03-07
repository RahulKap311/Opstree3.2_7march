package com.buildpiper.utils;

import java.util.Random;

public class RandomStrings {

	public static void main(String[] args) {
		String baseName = "automation";
		int length = 6; // the length of the random suffix to be added to the baseName

		String randomName = baseName + generateRandomString(length);

		System.out.println(randomName);
	}

	public static String generateRandomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars.charAt(random.nextInt(chars.length()));
			sb.append(c);
		}
		return sb.toString();
	}

	public static int generateRandomInt(int length) {
			  Random random = new Random();
		        int randomNumber = random.nextInt(9) + 1;
				return randomNumber;
       }
	}
