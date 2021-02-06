package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class GPUSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * @param pc, PC
	 * @param searchAmazon, boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg, boolean
	 */
	public GPUSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Searches for exact and similar type of GPU from prebuilt
	 */
	public void run() {
		try {
			// Getting GPU + removing unnecessary parts
			String GPU = pc.getGpu().toLowerCase().replace("NVIDIA".toLowerCase(), "")
					.replace("Geforce".toLowerCase(), "").replace("(OC Ready)".toLowerCase(), "")
					.replace("AMD".toLowerCase(), "").concat(" card".toLowerCase()).trim();
			//Searching
			searchComponents(GPU);
			// Checking if there is an avaliable CPU of similar type
			if (validResults.size() > 0) { 
				// Sorting cheapest price
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get(0);
				getAndSetRelativeComponent(results);
			} else {
				similarComponent = getAndSetRelativeComponent(results);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for GPU");
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
			// Checking if result is a gpu
			if (result.getTitle().toLowerCase().contains(" Card".toLowerCase())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to GPU cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.2)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.28)) {
					validSecondaryResults.add(result);
				}
			}
		}

		// Sorting cheapest price
		validSecondaryResults = Utils.sortByPrice(validSecondaryResults);
		if (validSecondaryResults.size() > 0) {
			relativeComponent = validSecondaryResults.get(0);
		}
		return relativeComponent;
	}

}
