package com.uncoverpc.searchProcess.Computer;

public class Component {

	// Class variables
	private String title;
	private String imageLink;
	private String productLink;
	private String price;

	/**
	 * Constructor
	 * 
	 * @param title,       String
	 * @param imageLink,   String
	 * @param productLink, String
	 * @param price,       String
	 */
	public Component(String title, String imageLink, String productLink, String price) {
		super();
		this.title = title;
		this.imageLink = imageLink;
		this.productLink = productLink;
		this.price = price;
	}

	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title
	 * @param title, String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * 
	 * @return productLink
	 */
	public String getProductLink() {
		return productLink;
	}

	/**
	 * 
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Converts price from String to Double
	 * @return price in Double format
	 */
	public Double getNumberPrice() {
		try {
			// Converting String to Double
			return Double.parseDouble(price.replace(",", ""));
		} catch (NumberFormatException e) {
			System.err.println("Error converting " + price + " to a Double");
		}
		//Error code
		return 404.01;
	}

	/**
	 * Formatted version of Component
	 */
	@Override
	public String toString() {
		return "Component Title: " + title + "\nImageLink: " + imageLink + "\nProduct Link: " + productLink
				+ "\nPrice: " + price;
	}

}
