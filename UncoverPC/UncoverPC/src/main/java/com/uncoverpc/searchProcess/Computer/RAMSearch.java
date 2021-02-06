package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class RAMSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * 
	 * @param pc,                   PC
	 * @param searchAmazon,         boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg,         boolean
	 */
	public RAMSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Searches for exact or similar type of ram as the prebuilt
	 */
	public void run() {
		try {
			// Getting RAM
			String RAM = " ".concat(pc.getRamSize().concat(" " + pc.getRamRecSpeed()).concat(" Mhz".toLowerCase()));
			searchComponents(RAM);

			// Checking if there is an avaliable RAM of similar type
			if (validResults.size() > 0) {
				// Sorting for cheapest price
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get(0);
				getAndSetRelativeComponent(results);
			} else {
				similarComponent = getAndSetRelativeComponent(results);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for RAM");
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
			// Checking if result is a RAM
			if (result.getTitle().toLowerCase().contains(" DDR4".toLowerCase())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to RAM cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.03)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.06)) {
					validSecondaryResults.add(result);
				}
			}
		}

		// Sorting for cheapest price
		validSecondaryResults = Utils.sortByPrice(validSecondaryResults);
		if (validSecondaryResults.size() > 0) {
			relativeComponent = validSecondaryResults.get(0);
		}
		return relativeComponent;
	}

}
