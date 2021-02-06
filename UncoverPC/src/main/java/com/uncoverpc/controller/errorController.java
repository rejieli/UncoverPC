package com.uncoverpc.controller;

public class errorController {

	/**
	 * 
	 * @param error, String
	 * @return error message
	 */
	public static String getErrorType(String error) {
		return error;
	}
	
	/**
	 * 
	 * @param error, String
	 * @return error protocol
	 */
	public static String getErrorMessage(String error) {
		if(error.toLowerCase().equals("Product Error".toLowerCase())) {
			return "The producted entered is not of type gaming desktop";
		}else if(error.toLowerCase().equals("Price Error".toLowerCase())) {
			return "The product entered is less than $900";
		}else if(error.toLowerCase().equals("Website Error".toLowerCase())) {
			return "The website entered is not from the supported store(s) BestBuy.ca";
		}else {
			return "Check inputted link";
		}
	}
	
	/**
	 * 
	 * @param error, String
	 * @return error solution
	 */
	public static String getSolution(String error) {
		if(error.toLowerCase().equals("Product Error".toLowerCase())) {
			return "Enter a product that is a gaming desktop";
		}else if(error.toLowerCase().equals("Price Error".toLowerCase())) {
			return "Enter a product that is greater than $900";
		}else if(error.toLowerCase().equals("Website Error".toLowerCase())) {
			return "Enter a product from BestBuy.ca";
		}else {
			return "Try again. This product may not be supported";
		}
	}
	
}
