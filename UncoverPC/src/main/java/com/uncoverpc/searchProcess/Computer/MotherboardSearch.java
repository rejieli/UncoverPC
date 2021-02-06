package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

import com.uncoverpc.searchProcess.Connector.Connector;
import com.uncoverpc.searchProcess.Utilities.Utils;

public class MotherboardSearch extends SearchComponent implements Runnable {

	/**
	 * Constructor
	 * @param pc, PC
	 * @param searchAmazon, boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg, boolean
	 */
	public MotherboardSearch(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		super(pc, searchAmazon, searchCanadaComputer, searchNewegg);

	}

	/**
	 * Searches for CPU socket and finds a motherboard that matches
	 */
	public void run() {
		try {
			//Getting motherboard socket
			String socket = getMotherboardSocket();
			if(socket.equals("Error")) {
				System.err.println("Error, cannot find CPU Socket");
				return;
			}
			// Getting motherboard type
			String Motherboard = socket;
			//Searching
			searchComponents(Motherboard);

			// Checking if there is an avaliable Motherboard of similar type
			if (validResults.size() > 0) {
				// Sorting for cheapest price
				validResults = Utils.sortByPrice(validResults);
				similarComponent = validResults.get((int) validResults.size() / 3);
				getAndSetRelativeComponent(results, socket);
			} else {
				similarComponent = getAndSetRelativeComponent(results, socket);
			}
		} catch (Exception e) {
			System.err.println("Error Searching for Motherboards");
			e.printStackTrace();
		}
	}

	/**
	 * Searches online for CPU socket
	 * @return CPU socket
	 */
	public String getMotherboardSocket() {
		// Checking cpu type
		if (pc.getCpu().toLowerCase().contains("intel".toLowerCase())
				|| pc.getCpu().toLowerCase().contains("core".toLowerCase())) {
			// If Intel CPU
			
			Document page = new Document("");
			//Checking the type
			if (pc.getCpu().contains("i3")) {
				page = Connector.forceConnectDocument("https://www.cpu-monkey.com/en/cpu_family-intel_core_i3-6");
			} else if (pc.getCpu().contains("i5")) {
				page = Connector.forceConnectDocument("https://www.cpu-monkey.com/en/cpu_family-intel_core_i5-5");
			} else if (pc.getCpu().contains("i7")) {
				page = Connector.forceConnectDocument("https://www.cpu-monkey.com/en/cpu_family-intel_core_i7-7");
			} else if (pc.getCpu().contains("i9")) {
				page = Connector.forceConnectDocument("https://www.cpu-monkey.com/en/cpu_family-intel_core_i9-37");
			}

			//Scraping all the models from site
			String info = Connector.scrape(page, "tr > td:nth-child(1) > a", "href");

			//Looping through each CPU to find exact model
			String[] cpuInfos = info.split("\n");
			for (int i = 0; i < cpuInfos.length - 1; i += 2) {
				
				if (Utils.searchEquals(pc.getCpu(), cpuInfos[i])) {
					// Searching more in depth once it found the exact model
					Document cpuPage = Connector
							.forceConnectDocument("https://www.cpu-monkey.com/en/" + cpuInfos[i + 1]);

					//Getting socket
					String socket = Connector.scrape(cpuPage,
							"#page > div.full_col.frame > div.cpu_col2 > table:nth-child(3) > tbody > tr:nth-child(19) > td:nth-child(4)");
					//Formating and returning socket
					return socket.split("-")[0];
				}
			}
		} else if (pc.getCpu().toLowerCase().contains("amd".toLowerCase())
				|| pc.getCpu().toLowerCase().contains("ryzen".toLowerCase())) {
			// If AMD CPU
			// Connecting to site full of information about amd cpus
			Document page = Connector.forceConnectDocument("https://www.cpu-monkey.com/en/cpu_family-amd_ryzen-32");

			//Scraping all types of CPU
			String info = Connector.scrape(page, "tr > td:nth-child(1) > a", "href");

			//Looping through and finding exact type of CPU
			String[] cpuInfos = info.split("\n");
			for (int i = 0; i < cpuInfos.length - 1; i += 2) {

				if (Utils.searchEquals(cpuInfos[i], pc.getCpu())) {
					// Searching more in depth
					Document cpuPage = Connector
							.forceConnectDocument("https://www.cpu-monkey.com/en/" + cpuInfos[i + 1]);

					//Getting socket
					String socket = Connector.scrape(cpuPage,
							"#page > div.full_col.frame > div.cpu_col2 > table:nth-child(3) > tbody > tr:nth-child(19) > td:nth-child(4)");

					return socket;
				}
			}
		}
		return "Error";
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
	public Component getAndSetRelativeComponent(ArrayList<Component> results, String socket) throws Exception {
		// Find Similar Components

		ArrayList<Component> validSecondaryResults = new ArrayList<Component>();
		for (Component result : results) {
			// Checking if result is a a motherboard
			if (Utils.searchEquals(socket, result.getTitle())) {
				// Checking if it is desired price
				// These numbers are based of research of Total PC cost to motherbaored cost ratio
				if (result.getNumberPrice() > (pc.getDoublePrice() * 0.05)
						&& result.getNumberPrice() < (pc.getDoublePrice() * 0.16)) {
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
