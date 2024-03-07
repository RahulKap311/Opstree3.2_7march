package com.buildpiper.utils;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class StringUtility {


	public static boolean isNotEmpty(String str) {
		return null != str && !"".equals(str);
	}

	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}

	public static boolean isEmptyOrNull(String string){

		if((string == null) || StringUtils.isEmpty(string) || StringUtils.isBlank(string) ){
			return true;
		}
		return false;
	}

	public static String returnNullSafe(String string){

		if(isEmptyOrNull(string)) {
			return "";
		}
		return string;
	}

	/**
	 * @author smehta
	 * 
	 * This method will provide the random number or alphabets
	 * @param type: NUMBER/ALPHABET
	 * @param length number	
	 */
	public static String randomGenarotor(String type,int length) {

		switch(type.toUpperCase()){		  
		case "NUMBER" :	
			return String.valueOf(length < 1 ? 0 : new Random()
					.nextInt((9 * (int) Math.pow(10, length - 1)) - 1)
					+ (int) Math.pow(10, length - 1));

		case "ALPHABET" :	
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for(int i = 0; i < length; i++) {
				int index = random.nextInt(alphabet.length());
				char randomChar = alphabet.charAt(index);
				sb.append(randomChar);
			}

			return sb.toString();
		default: return "NA";

		}

	}
}
