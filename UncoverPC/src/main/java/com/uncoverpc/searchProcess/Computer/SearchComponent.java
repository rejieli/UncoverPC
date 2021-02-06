package com.uncoverpc.searchProcess.Computer;

import java.util.ArrayList;

import com.uncoverpc.searchProcess.Connector.Amazon;
import com.uncoverpc.searchProcess.Connector.CanadaComputer;
import com.uncoverpc.searchProcess.Connector.Newegg;


public class SearchComponent {

	// Variables
	protected PC pc;
	protected Component relativeComponent;
	protected Component similarComponent;
	protected boolean searchAmazon;
	protected boolean searchCanadaComputer;
	protected boolean searchNewegg;
	protected ArrayList<Component> validResults = new ArrayList<Component>();
	protected ArrayList<Component> results = new ArrayList<Component>();

	/**
	 * Constructor
	 * 
	 * @param pc,                   PC
	 * @param searchAmazon,         boolean
	 * @param searchCanadaComputer, boolean
	 * @param searchNewegg,         boolean
	 */
	public SearchComponent(PC pc, boolean searchAmazon, boolean searchCanadaComputer, boolean searchNewegg) {
		this.pc = pc;
		this.searchAmazon = searchAmazon;
		this.searchCanadaComputer = searchCanadaComputer;
		this.searchNewegg = searchNewegg;
	}

	/**
	 * Searches all selected websites for desired components
	 * 
	 * @param name
	 * @throws InterruptedException
	 */
	protected void searchComponents(String name) throws InterruptedException {
		// Searching all selected
		Amazon amazonScrape = new Amazon(name);
		CanadaComputer CCScrape = new CanadaComputer(name);
		Newegg neweggScrape = new Newegg(name);

		// Threads
		Thread amazonThread = new Thread(amazonScrape);
		Thread CCThread = new Thread(CCScrape);
		Thread neweggThread = new Thread(neweggScrape);

		// Starting Threads
		// Searching Amazon
		if (searchAmazon) {
			amazonThread.start();
		}
		// Searching CanadaComputer
		if (searchCanadaComputer) {
			CCThread.start();
		}
		// Searching Newegg
		if (searchNewegg) {
			neweggThread.start();
		}

		// Stopping Threads
		if (searchAmazon) {
			// Stopping Thread
			amazonThread.join();
			// Collecting Data
			validResults.addAll(amazonScrape.getValidResults());
			results.addAll(amazonScrape.getResults());
		}
		if (searchCanadaComputer) {
			// Stopping Thread
			CCThread.join();
			// Collecting Data
			validResults.addAll(CCScrape.getValidResults());
			results.addAll(CCScrape.getResults());
		}
		if (searchNewegg) {
			// Stopping Thread
			neweggThread.join();
			// Collecting Data
			validResults.addAll(neweggScrape.getValidResults());
			results.addAll(neweggScrape.getResults());
		}
	}

	/**
	 * 
	 * @return similarComponent
	 */
	public Component getSimilarComponentComponent() {
		return similarComponent;
	}

	/**
	 * 
	 * @return relativeComponent
	 */
	public Component getRelativeComponent() {
		return relativeComponent;
	}

	/**
	 * removes index from validResults arraylist
	 * 
	 * @param index, int
	 */
	public void removeValidResults(int index) {
		validResults.remove(index);
	}

	/**
	 * removes index from results arraylist
	 * 
	 * @param index, int
	 */
	public void removeResults(int index) {
		results.remove(index);
	}

	/**
	 * 
	 * @return a new Components made from relativeComponent + similarComponent
	 */
	public Components getComponents() {
		return new Components(relativeComponent, similarComponent);
	}
}