package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Utilities.Utils;

public class CPUSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * 
	 * @param pc,                   PC
	 * @param searchAmazon,         boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg,         searchNewegg
	 */
	public CPUSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);
	}

	/**
	 * Getting exact and similar CPU to the prebuilt model
	 */
	public void run() {
		try {
			// Getting CPU
			String CPU = pc.getCpu().concat(" Processor".toLowerCase());
			searchComponents(CPU);

			// Checking if there is an avaliable CPU of similar type
			if (validResults.size() > 0) {
				// Sorting
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get(0);
				getAndSetRelativeComponent(results);
			} else {
				similarComponent = getAndSetRelativeComponent(results);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for CPU");
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
		boolean isAMD = false;
		boolean isIntel = false;

		ArrayList<Component> validSecondaryResults = new ArrayList<Component>();

		// Checking required type of cpu
		if (pc.getCpu().toLowerCase().contains("intel".toLowerCase())
				|| pc.getCpu().toLowerCase().contains("core".toLowerCase())) {
			isIntel = true;
		} else if (pc.getCpu().toLowerCase().contains("amd".toLowerCase())
				|| pc.getCpu().toLowerCase().contains("ryzen".toLowerCase())) {
			isAMD = true;
		}
		for (Component result : results) {
			// Checking if result is a cpu
			if (result.getTitle().toLowerCase().contains(" processor".toLowerCase())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to CPU cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.2)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.28)) {
					// Checking if its required type
					if (isAMD && result.getTitle().toLowerCase().contains("amd".toLowerCase())
							|| result.getTitle().toLowerCase().contains("ryzen".toLowerCase())) {
						// is amd cpu
						validSecondaryResults.add(result);
					} else if (isIntel && result.getTitle().toLowerCase().contains("intel".toLowerCase())
							|| result.getTitle().toLowerCase().contains("core".toLowerCase())) {
						// is intel cpu
						validSecondaryResults.add(result);
					}
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
