package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class SSDSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * @param pc, PC
	 * @param searchAmazon, boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg, boolean
	 */
	public SSDSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Searches for similar/exact SSD as prebuilt
	 */
	public void run() {
		try {
			// Getting SSD
			String SSD = pc.getSddSize().concat(" 2.5\" Sata SSD".toLowerCase());
			searchComponents(SSD);

			// Checking if there is an avaliable SSD of similar type
			if (validResults.size() > 0) {
				// Sorting
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get((int) validResults.size() / 3);
				getAndSetRelativeComponent(results);
			} else {
				similarComponent = getAndSetRelativeComponent(results);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for SSD");
			e.printStackTrace();
		}
	}

	/**
	 * Searches for components that are in the price point of the prebuilt (using
	 * price to component ratio)
	 * 
	 * @param results,     String
	 * @param productName, String
	 * @return components that aren't exactly the ones in the prebuilt, but in the
	 *         price point
	 * @throws Exception
	 */
	public Component getAndSetRelativeComponent(ArrayList<Component> results) throws Exception {
		// Find Similar Components

		ArrayList<Component> validSecondaryResults = new ArrayList<Component>();

		for (Component result : results) {
			// Checking if result is a SSD
			if (result.getTitle().toLowerCase().contains(" SSD".toLowerCase())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to SSD cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.03)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.06)) {
					validSecondaryResults.add(result);
				}
			}
		}

		// Sorting for cheapest price
		validSecondaryResults = Utils.sortByPrice(validSecondaryResults);
		if (validSecondaryResults.size() > 0) {
			relativeComponent = validSecondaryResults.get((int) validSecondaryResults.size() / 3);
		}
		return relativeComponent;
	}

}
