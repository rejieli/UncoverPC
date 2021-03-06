package com.uncoverpc.searchProcess.Connector;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.uncoverpc.searchProcess.Computer.Component;
import com.uncoverpc.searchProcess.Utilities.Utils;

public class Newegg implements Runnable{

	//Variables
	private String product;
	private ArrayList<Component> results;
	
	/**
	 * Constructor
	 * @param product, String
	 */
	public Newegg(String product) {
		this.product = product;
		results = new ArrayList<Component>();
	}
	
	/**
	 * Connects to website and scrapes results
	 */
	public void run() {
		//Connection
		Document page = Connector.forceConnectDocument(getLink(product));
		
		//Retrieving important info
		String info = Connector.scrape(page, "div > div.item-info > a , " //Title
				+ " ul > li.price-current , " //Price
				+ " #app > div> section > div > div > div > div > div > div > div > div > div > div > div > div > a > img", //Image 
				"href , " //Link to page
				+ "src"); // Link to Image

		//Sorting	
		//Spliting by every empty space (Every result)
		String[] products = info.split("\\n\\n");
		//Checking if products contain all 4 information, Title, Price, Link, Image Link
		for(String p : products) {
			String[] productInformation = p.trim().split("\\r?\\n");
			if(productInformation.length == 4) {
				//Getting Product Image Link
				String productImageLink = productInformation[0];
				//Getting Product Title
				String productTitle = productInformation[1];
				//Getting Product Link
				String productLink = productInformation[2];
				//Getting Price
				String productPrice = String.valueOf(Utils.parseDouble(productInformation[3].split("\\s+")[0]));
				//Adding to results
				results.add(new Component(productTitle, productImageLink, productLink, productPrice));
			}
		}
	}
	
	/**
	 * 
	 * @return results
	 */
	public ArrayList<Component> getResults() {
		return results;
	}
	
	/**
	 * Returns results that match desired product
	 * @return valid results
	 */
	public ArrayList<Component> getValidResults(){
		return Utils.sortValidResults(results, product);
	}
	
	/**
	 * Sort results by price
	 * @return sorted results by price
	 */
	public ArrayList<Component> getSortedResults(){
		return Utils.sortByPrice(Utils.sortValidResults(results, product));
	}
	
	/**
	 * Generates direct link to store
	 * @param product, String
	 * @return link to store
	 */
	private String getLink(String product) {
		String formattedProduct = URLEncoder.encode(product, StandardCharsets.UTF_8);
		return "https://www.newegg.ca/p/pl?d=" + formattedProduct;
	}
}
