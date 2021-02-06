package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class PSSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * @param pc, PC
	 * @param searchAmazon, boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg, boolean
	 */
	public PSSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Gets exact or similar type of power supply unit as the prebuilt
	 */
	public void run() {
		try {
			// Getting PowerSupply
			String PS = pc.getPowerSupply().concat(" Power Supply Gold".toLowerCase());
			searchComponents(PS);

			// Checking if there is an avaliable PSU of similar type
			if (validResults.size() > 0) {
				// Sorting for cheapest price
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get(0);
				getAndSetRelativeComponent(results);
			} else {
				similarComponent = getAndSetRelativeComponent(results);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for PowerSupply");
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
			// Checking if result is a PSU
			if (Utils.searchEquals("Power Supply Gold", result.getTitle())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to PSU cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.03)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.06)) {
					validSecondaryResults.add(result);
				}
			}
		}

		// Sorting
		validSecondaryResults = Utils.sortByPrice(validSecondaryResults);
		if (validSecondaryResults.size() > 0) {
			relativeComponent = validSecondaryResults.get(0);
		}
		return relativeComponent;
	}

}
