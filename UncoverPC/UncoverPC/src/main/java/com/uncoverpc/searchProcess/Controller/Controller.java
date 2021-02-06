package com.uncoverpc.searchProcess.Controller;

import com.uncoverpc.searchProcess.Computer.CustomPC;
import com.uncoverpc.searchProcess.Computer.PC;
import com.uncoverpc.searchProcess.Connector.BestBuy;
import com.uncoverpc.searchProcess.Utilities.StopWatch;
import com.uncoverpc.searchProcess.Utilities.Utils;

public class Controller {
	
	/**
	 * Finds exact/similar component of the prebuilt PC
	 * 
	 * @param link,           String
	 * @param amazon,         boolean
	 * @param canadaComputer, boolean
	 * @param newegg,         boolean
	 */
	public static Computers searchPC(String link, boolean amazon, boolean canadaComputer, boolean newegg) {
		CustomPC customPC;
		PC pc = new PC();
		StopWatch total = new StopWatch();
		total.start();
		StopWatch bestBuy = new StopWatch();
		bestBuy.start();

		try {
			if (link.toLowerCase().contains("BestBuy.ca".toLowerCase())) {
				pc = BestBuy.getPC(link);
				System.out.println("BestBuy Scraping Time: " + bestBuy.stop());

				if (pc.getDoublePrice() > 900) {
					if (pc.getTitle().toLowerCase().contains("gaming".toLowerCase())) {
						PCBuilder pcBuilder = new PCBuilder(pc, amazon, canadaComputer, newegg);
						Thread thread = new Thread(pcBuilder);
						thread.start();
						try {
							thread.join();
						} catch (InterruptedException e) {
							System.err.println("ERROR JOINNING THREAD");
						}

						// Result
						StringBuilder result = new StringBuilder();
						result.append("\n" + pcBuilder.getSimilarPC().toString());
						result.append("\nOriginal Price: " + pc.getPrice() + "Our Price: $"
								+ Utils.round(pcBuilder.getSimilarPC().getTotalPrice()) + "\nSavings: $"
								+ Utils.round((pc.getDoublePrice() - pcBuilder.getSimilarPC().getTotalPrice())));
						System.out.println("Total Process Time: " + total.stop());
						customPC = pcBuilder.getSimilarPC();
					} else {
						customPC = new CustomPC(true, "Product Error");
					}
				} else {
					customPC=  new CustomPC(true, "Price Error");
				}
			} else {
				customPC =  new CustomPC(true, "Website Error");
			}
		} catch (Exception e) {
			customPC=  new CustomPC(true, "Unexpected Error");
		}
		return new Computers(customPC, pc);
	}
}
