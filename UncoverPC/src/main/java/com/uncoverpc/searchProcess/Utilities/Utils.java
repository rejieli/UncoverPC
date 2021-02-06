package com.uncoverpc.searchProcess.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.uncoverpc.searchProcess.Computer.Component;


public class Utils {

	/**
	 * Returns whether productTitle matches the desired product
	 * @param keyWords, String
	 * @param productTitle, String
	 * @return productTitle is product
	 */
	public static boolean searchEquals(String keyWords, String productTitle) {
		//Spliting into individual words
		String[] keyWord = keyWords.split("\\s+");
		//Checking if every word is in productTitle
		for(String word : keyWord) {
			if(!productTitle.toLowerCase().contains(word.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * sorts valid components that match the keywords
	 * @param results, ArrayList<Component>
	 * @param keyWords, String
	 * @return list of valid components
	 */
	public static ArrayList<Component> sortValidResults(ArrayList<Component> results, String keyWords){
		ArrayList<Component> validResults = new ArrayList<Component>();
		for(int i = 0; i < results.size(); i++) {
			//Checking if result is valid
			if(searchEquals(keyWords, results.get(i).getTitle())) {
				validResults.add(results.get(i));
			}
		}
		return validResults;
	}
	 
	/**
	 * Sorts the results by their respective price
	 * @param results, ArrayList<Component>
	 * @return sorted version of results
	 */
	public static ArrayList<Component> sortByPrice(ArrayList<Component> results){
		
		//Comparator
		Comparator<Component> sortByPrice = new Comparator<Component>(){
			public int compare(Component c1, Component c2) {
				return c1.getNumberPrice().compareTo(c2.getNumberPrice());
			}
		};
		
		ArrayList<Component> validResults = new ArrayList<Component>();
		
		//Checking if prices are valid
		for(Component result: results) {
			if(result.getNumberPrice() != 404.01) {
				validResults.add(result);
			}
		}
		
		//Sorting by price
		Collections.sort(validResults, sortByPrice);
		
		return validResults;
	}
	
	/**
	 * Rounds to the hundredths
	 * @param price, Double
	 * @return rounded version price
	 */
	public static Double round(double price) {
		return ((double)(int)((price + 0.005) * 100)/100);
	}
	
}
