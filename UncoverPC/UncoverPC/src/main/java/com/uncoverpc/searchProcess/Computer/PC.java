package com.uncoverpc.searchProcess.Computer;

public class PC {

	// Class Variables
	private String link;
	private String price;
	private String imageLink;
	private String cpu;
	private String motherboard;
	private String ramSize;
	private String ramSpeed;
	private String hddSize;
	private String hddSpeed;
	private String sddSize;
	private String gpu;
	private String powerSupply;
	private String chasis;
	//Optional
	private String title;

	public PC(String link, String price, String imageLink, String cpu, String motherboard, String ramSize, String ramSpeed,
			String hddSize, String hddSpeed, String sddSize, String gpu, String powerSupply, String chasis) {
		this.price = price;
		this.imageLink = imageLink;
		this.cpu = cpu.replace("-", " ");
		this.motherboard = motherboard;
		this.ramSize = ramSize;
		this.ramSpeed = ramSpeed;
		this.hddSize = hddSize;
		this.hddSpeed = hddSpeed;
		this.sddSize = sddSize;
		this.gpu = gpu;
		this.powerSupply = powerSupply;
		this.chasis = chasis;
	}

	/**
	 * Constructor
	 * 
	 * @param price,       String
	 * @param imageLink,   String
	 * @param cpu,         String
	 * @param ramSize,     String
	 * @param ramSpeed,    String
	 * @param hddSize,     String
	 * @param hddSpeed,    String
	 * @param sddSize,     String
	 * @param gpu,         String
	 * @param powerSupply, String
	 */
	public PC(String link, String price, String imageLink, String cpu, String ramSize, String ramSpeed, String hddSize,
			String hddSpeed, String sddSize, String gpu, String powerSupply) {
		this.price = price;
		this.imageLink = imageLink;
		this.cpu = cpu.replace("-", " ");
		this.ramSize = ramSize;
		this.ramSpeed = ramSpeed;
		this.hddSize = hddSize;
		this.hddSpeed = hddSpeed;
		this.sddSize = sddSize;
		this.gpu = gpu;
		this.powerSupply = powerSupply;
	}

	/**
	 * Paramter-less constructor
	 */
	public PC() {
		
	}
	
	// Getters and Setters

	/**
	 * 
	 * @return link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * 
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * 
	 * @return price in double format
	 */
	public double getDoublePrice() {
		return Double.parseDouble(price.replace(",", "").replace("$", ""));
	}

	/**
	 * 
	 * @return imageLink
	 */
	public String getImageLink() {
		return imageLink;
	}

	/**
	 * 
	 * @return cpu
	 */
	public String getCpu() {
		return cpu;
	}

	/**
	 * 
	 * @return motherboard
	 */
	public String getMotherboard() {
		return motherboard;
	}

	/**
	 * sets motherboard
	 * 
	 * @param motherBoard, String
	 */
	public void setMotherboard(String motherBoard) {
		this.motherboard = motherBoard;
	}

	/**
	 * 
	 * @return ramSize
	 */
	public String getRamSize() {
		return ramSize;
	}

	/**
	 * 
	 * @return ramSpeed
	 */
	public String getRamSpeed() {
		return ramSpeed;
	}

	/**
	 * 
	 * @return formatted ram
	 */
	public String getRamRecSpeed() {
		if (ramSize.contains("8")) {
			return "2400";
		} else if (ramSize.contains("16")) {
			return "3200";
		} else {
			return "3600";
		}
	}

	/**
	 * 
	 * @return hddSize
	 */
	public String getHddSize() {
		return hddSize;
	}

	/**
	 * 
	 * @return hddSpeed
	 */
	public String getHddSpeed() {
		return hddSpeed;
	}

	/**
	 * 
	 * @return ssdSize
	 */
	public String getSddSize() {
		return sddSize;
	}

	/**
	 * 
	 * @return gpu
	 */
	public String getGpu() {
		return gpu;
	}

	/**
	 * 
	 * @return powerSupply
	 */
	public String getPowerSupply() {
		return powerSupply;
	}

	/**
	 * 
	 * @return chasis
	 */
	public String getChasis() {
		return chasis;
	}

	/**
	 * sets chasis
	 * 
	 * @param chasis, String
	 */
	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	/**
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets title
	 * @param title, String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
