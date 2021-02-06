package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class ChasisSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * 
	 * @param pc,                   PC
	 * @param searchAmazon,         boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg,         boolean
	 */
	public ChasisSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Gets similar type of Chassis as the computer
	 */
	public void run() {
		try {
			// Getting Chasis
			String Chasis = "atx mid tower".toLowerCase();
			searchComponents(Chasis);

			// Checking if there is an avaliable Chasis of similar type
			if (validResults.size() > 0) {
				// Sorting
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get((int) validResults.size() / 3);
				getAndSetRelativeComponent(results, Chasis);
			} else {
				similarComponent = getAndSetRelativeComponent(results, Chasis);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for Chasis");
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
	public Component getAndSetRelativeComponent(ArrayList<Component> results, String productName) throws Exception {
		ArrayList<Component> validSecondaryResults = new ArrayList<Component>();
		for (Component result : results) {
			// Checking if result is a Chassis
			if (Utils.searchEquals(productName, result.getTitle())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to Chassis cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.03)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.065)) {
					validSecondaryResults.add(result);
				}
			}
		}

		// Sorting
		validSecondaryResults = Utils.sortByPrice(validSecondaryResults);
		if (validSecondaryResults.size() > 0) {
			relativeComponent = validSecondaryResults.get((int) validSecondaryResults.size() / 3);
		}
		return relativeComponent;
	}

}
