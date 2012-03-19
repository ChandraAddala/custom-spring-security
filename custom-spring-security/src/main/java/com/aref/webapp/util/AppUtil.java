package com.aref.webapp.util;

/**
 * 
 * Util class for the application.
 * 
 * @author chandra
 *
 */
public class AppUtil {

	private AppUtil() {		
	}
	
	
	/**
	 * Returns true if input data is null or empty
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isNullOrEmpty(String input) {
		return (input == null ? true : (input.length() <= 0 ? true : false)); 
	}
	
	
	/**
	 * 
	 * Returns empty string in input is empty, else returns a 
	 * trimmer string.
	 * 
	 * @param input
	 * @return
	 */
	public static String trim(String input) {
		return (input == null ? "" : input.trim());
	}

	/**
	 * 
	 * Returns empty string if input is empty, else returns the 
	 * actual string.
	 * 
	 * @param input
	 * @return
	 */
	public static String emptyIfNull(String input) {
		return (input == null ? "" : input);
	}
	
}
