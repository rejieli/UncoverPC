package com.uncoverpc.searchProcess.Connector;

import org.jsoup.nodes.Document;

import com.uncoverpc.searchProcess.Computer.PC;
import com.uncoverpc.searchProcess.Utilities.Utils;

public class BestBuy {

	/**
	 * Gets all components from the prebuilt
	 * 
	 * @param link
	 * @return PC
	 */
	public static PC getPC(String link) {
		// Connection
		Document page = Connector.forceConnectDocument(link);
		// Gather parts
		String cpu = getCPU(page);
		String gpu = getGPU(page);
		String ramSpeed = getRAMSpeed(page);
		String ramSize = getRAMSize(page);
		String hddSpeed = getHardDriveSpeed(page);
		String hddSize = getHardDrive(page);
		String ssdSize = getSSDSize(page);
		String powerSupply = getPowerSupply(page);
		String price = getPrice(page);
		String imageLink = getProductImage(page);
		String title = getTitle(page);
		// Creating new PC Object
		PC prebuilt = new PC(link, price, imageLink, cpu, ramSize, ramSpeed, hddSize, hddSpeed, ssdSize, gpu,
				powerSupply);
		prebuilt.setTitle(title);
		return prebuilt;
	}

	/**
	 * Checking if component is in the source code
	 * 
	 * @param htmlText,  String
	 * @param component, String
	 * @return whether component is in source code
	 */
	private static boolean isComponent(String htmlText, String component) {
		return htmlText.contains(component);
	}

	/**
	 * Checking if there is a dollar sign in the htmlcode
	 * 
	 * @param htmlText, String
	 * @param endIndex, String
	 * @return htmlcode contains dollar sign
	 */
	public int $check(String htmlText, int endIndex) {
		boolean found = false;
		short counter = 0;
		while (!found) {
			if (htmlText.substring(endIndex - counter - 1, endIndex - counter).equals("$")) {
				return endIndex - counter - 1;
			}
			counter++;
		}
		return endIndex;
	}

	/**
	 * Gets the end of a quotation mark '"'
	 * 
	 * @param htmlText,   String
	 * @param beginIndex, int
	 * @return the index of the nearest quotation mark '"'
	 */
	private static int getEnd(String htmlText, int beginIndex) {
		boolean found = false;
		short counter = 0;
		while (!found) {
			if (htmlText.substring(beginIndex + counter, beginIndex + counter + 1).equals("\"")) {
				return (beginIndex + counter);
			}
			counter++;
		}
		return beginIndex;
	}

	/**
	 * Scrapes the CPU type of prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return CPU from prebuilt computer
	 */
	private static String getCPU(Document page) {
		String htmlText = page.outerHtml();
		String cpu = null;
		String id = "\"Processor Type\",\"value\":\"";
		if (isComponent(htmlText, id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());
			cpu = htmlText.substring(beginIndex + id.length(), endIndex);
			// Formating
			cpu = cpu.toLowerCase();
			// Intel CPU
			if (cpu.contains("intel".toLowerCase())) {
				cpu = cpu.replace("ci", "core i");
				String[] info = cpu.split("\\s+");
				for (int i = 0; i < info.length; i++) {
					if (info[i].equals("intel".toLowerCase()) && info[i + 1].equals("core".toLowerCase())
							&& info[i + 2].charAt(0) == 'i') {
						cpu = info[i] + " " + info[i + 1] + " " + info[i + 2];
					}
				}
			} else if (cpu.contains("amd".toLowerCase()) || cpu.contains("ryzen".toLowerCase())) {
				// AMD CPU

			}
			return cpu;
		}
		return cpu;
	}

	/**
	 * Scrapes ram size of the prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return ram size from prebuilt
	 */
	private static String getRAMSize(Document page) {
		String htmlText = page.outerHtml();
		String ramSize = null;
		String id = "\"RAM Size\",\"value\":\"";
		if (htmlText.contains((id))) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = RAMSizeCheck(htmlText, beginIndex + id.length());
			ramSize = htmlText.substring(beginIndex + id.length(), endIndex);
			// Formating ramSize
			if (ramSize.contains(" ")) {
				return ramSize.replace(" ", "");
			} else {
				return ramSize;
			}

		}
		return ramSize;
	}

	/**
	 * subStrings ramSize to format it
	 * 
	 * @param htmlText,   String
	 * @param beginIndex, int
	 * @return index where it should subString
	 */
	private static int RAMSizeCheck(String htmlText, int beginIndex) {
		boolean found = false;
		short counter = 0;
		while (!found) {
			if (htmlText.substring(beginIndex + counter, beginIndex + counter + 1).equals("B")) {
				return (beginIndex + counter + 1);
			}
			counter++;
		}
		return beginIndex;
	}

	/**
	 * Scrapes ram speed from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return ram speed from prebuilt computer
	 */
	private static String getRAMSpeed(Document page) {
		String htmlText = page.outerHtml();
		String RAMSpeed = null;
		String id = "\"RAM\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());
			RAMSpeed = htmlText.substring(beginIndex + id.length(), endIndex);
			String RAMSize = getRAMSize(page);
			// Replacing ram size in the ram speed section
			if (Utils.searchEquals(RAMSize, RAMSpeed)) {
				String[] ramSplit = RAMSize.split("\\s+");
				for (String ram : ramSplit) {
					RAMSpeed = RAMSpeed.replace(ram, "");
				}
			}
		}
		return RAMSpeed.trim();
	}

	/**
	 * Scrapes hard drive size from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return hard drive size of prebuilt computer
	 */
	private static String getHardDrive(Document page) {
		String htmlText = page.outerHtml();
		String hardDrive = null;
		String id = "\"Hard Disk Drive Capacity\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf("Hard Disk Drive Capacity\",\"value\":\"");
			int endIndex = getEnd(htmlText, beginIndex + id.length() - 1);
			if (!htmlText.substring(beginIndex + id.length() - 1, endIndex).substring(0, 1).equals("0")) {
				hardDrive = htmlText.substring(beginIndex + id.length() - 1, endIndex);
			}
		}
		return hardDrive;
	}

	/**
	 * Scrapes hard drive speed from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return hard drive speed of prebuilt computer
	 */
	private static String getHardDriveSpeed(Document page) {
		String htmlText = page.outerHtml();
		String hardDriveSpeed = null;
		String id = "\"Hard Drive Speed (Revolutions Per Minute)\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());
			hardDriveSpeed = htmlText.substring(beginIndex + id.length(), endIndex);
		}
		return hardDriveSpeed;
	}

	/**
	 * Scrapes SSD size from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return SSD size of prebuilt computer
	 */
	private static String getSSDSize(Document page) {
		String htmlText = page.outerHtml();
		String SSD = null;
		String id = "\"Solid-State Drive Capacity\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());
			if (!htmlText.substring(beginIndex + id.length() - 1, endIndex).substring(0, 1).equals("0")) {
				SSD = htmlText.substring(beginIndex + id.length(), endIndex);
			}

		}
		return SSD;
	}

	/**
	 * Scrapes GPU type from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return GPU of prebuilt computer
	 */
	private static String getGPU(Document page) {
		String htmlText = page.outerHtml();
		String gpu = null;
		String id = "\"Graphics Card\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());

			gpu = htmlText.substring(beginIndex + id.length(), endIndex);
		}
		return gpu;
	}

	/**
	 * Scrapes power supply wattage from prebuilt computer using String manipulation
	 * 
	 * @param page, Document
	 * @return power supply wattage of prebuilt computer
	 */
	private static String getPowerSupply(Document page) {
		String htmlText = page.outerHtml();
		String powerSupply = null;
		String id = "\"Power Supply\",\"value\":\"";
		if (htmlText.contains(id)) {
			int beginIndex = htmlText.indexOf(id);
			int endIndex = getEnd(htmlText, beginIndex + id.length());
			powerSupply = htmlText.substring(beginIndex + id.length(), endIndex);
		}
		// Formating
		if (powerSupply.charAt(powerSupply.length() - 1) != 'W') {
			powerSupply = powerSupply.substring(0, powerSupply.indexOf("W") + 1);
		}
		return powerSupply;
	}

	/**
	 * Scrapes price of the prebuilt using selectors
	 * 
	 * @param page, Document
	 * @return price of prebuilt
	 */
	private static String getPrice(Document page) {
		String price = Connector.scrape(page,
				"#root > div > div > div.x-product-detail-page > div.row_1mOdd > div> div > div > span > div");
		if (price.contains(".")) {
			return price;
		}else {
			String s1 = price.substring(0,price.length()-3);
			String s2 = price.substring(price.length()-3, price.length());
			return s1 + "." + s2;
		}
	}

	/**
	 * Scrapes the image link of prebuilt using selectors
	 * 
	 * @param page, Document
	 * @return imagelink of prebuilt
	 */
	private static String getProductImage(Document page) {
		return Connector.scrape(page,
				"#root > div > div > div > div > div> div > div > div> div > div > div > div > div > div > div > div > img",
				"src").trim();
	}

	/**
	 * Scrapes the title of prebuilt using selectors
	 * 
	 * @param page, Document
	 * @return title of prebuilt
	 */
	public static String getTitle(Document page) {
		return Connector.scrape(page, "#root > div > div > div > h1");
	}

}
