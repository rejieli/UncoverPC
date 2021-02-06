package com.uncoverpc.searchProcess.Computer;

public class CustomPC {

	// Class Variables
	private Component cpu;
	private Component motherBoard;
	private Component ram;
	private Component hdd;
	private Component ssd;
	private Component gpu;
	private Component powerSupply;
	private Component chasis;
	private boolean error;
	private String errorCode;

	/**
	 * Constructor
	 * 
	 * @param cpu
	 * @param motherBoard
	 * @param ram
	 * @param hdd
	 * @param ssd
	 * @param gpu
	 * @param powerSupply
	 * @param chasis
	 */
	public CustomPC(Component cpu, Component motherBoard, Component ram, Component hdd, Component ssd, Component gpu,
			Component powerSupply, Component chasis) {
		this.cpu = cpu;
		this.motherBoard = motherBoard;
		this.ram = ram;
		this.hdd = hdd;
		this.ssd = ssd;
		this.gpu = gpu;
		this.powerSupply = powerSupply;
		this.chasis = chasis;
	}
	
	/**
	 * Error Constructor
	 * @param error, boolean
	 * @param errorCode, String
	 */
	public CustomPC(boolean error, String errorCode) {
		this.error = error;
		this.errorCode = errorCode;
	}

	// Getters and Setters
	/**
	 * 
	 * @return cpu
	 */
	public Component getCpu() {
		return cpu;
	}

	/**
	 * 
	 * @return motherBoard
	 */
	public Component getMotherBoard() {
		return motherBoard;
	}

	/**
	 * 
	 * @return ram
	 */
	public Component getRam() {
		return ram;
	}

	/**
	 * 
	 * @return hdd
	 */
	public Component getHdd() {
		return hdd;
	}

	/**
	 * makes hdd null, incase computer does not have one
	 */
	public void clearHdd() {
		hdd = new Component(null, null, null, null);
	}

	/**
	 * 
	 * @return ssd
	 */
	public Component getSsd() {
		return ssd;
	}

	/**
	 * makes ssd null, incase computer does not have one
	 */
	public void clearSsd() {
		ssd = new Component(null, null, null, null);
	}

	/**
	 * 
	 * @return gpu
	 */
	public Component getGpu() {
		return gpu;
	}

	/**
	 * 
	 * @return powerSupply
	 */
	public Component getPowerSupply() {
		return powerSupply;
	}

	/**
	 * 
	 * @return chasis
	 */
	public Component getChasis() {
		return chasis;
	}

	/**
	 * 
	 * @return error
	 */
	public boolean getIsError() {
		return error;
	}
	
	/**
	 * 
	 * @return errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	@Override
	/**
	 * Formats and creates a String representation of this Custom PC
	 * 
	 * @retuns a String representation of this CustomPC
	 */
	public String toString() {
		StringBuilder stringRep = new StringBuilder();

		// Checking if components are null,
		// If not, appending component to the String
		if (cpu!=null) {
			stringRep.append(cpu);
		} else {
			stringRep.append("\n\nCPU NOT FOUND");
		}
		if (motherBoard!=null) {
			stringRep.append("\n\n" + motherBoard);
		} else {
			stringRep.append("\n\nMOTHERBOARD NOT FOUND");
		}
		if (ram!=null) {
			stringRep.append("\n\n" + ram);
		} else {
			stringRep.append("\n\nRAM NOT FOUND");
		}
		if (ssd!=null) {
			stringRep.append("\n\n" + ssd);
		} else {
			stringRep.append("\n\nSSD NOT FOUND");
		}
		if (hdd!=null) {
			stringRep.append("\n\n" + hdd);
		} else {
			stringRep.append("\n\nHDD NOT FOUND");
		}
		if (gpu!=null) {
			stringRep.append("\n\n" + gpu);
		} else {
			stringRep.append("\n\nGPU NOT FOUND");
		}
		if (powerSupply!=null) {
			stringRep.append("\n\n" + powerSupply);
		} else {
			stringRep.append("\n\nPOWERSUPPLY NOT FOUND");
		}
		if (chasis!=null) {
			stringRep.append("\n\n" + chasis);
		} else {
			stringRep.append("\n\nCHASIS NOT FOUND");
		}
		return stringRep.toString();
	}

	/**
	 * Calculates total price of custom PC
	 * 
	 * @return
	 */
	public double getTotalPrice() {
		double total = 0.0;
		// Checking if components are null,
		// If not, adding its price to total
		if (cpu!=null) {
			total += cpu.getNumberPrice();
		}
		if (motherBoard!=null) {
			total += motherBoard.getNumberPrice();
		}
		if (ram!=null) {
			total += ram.getNumberPrice();
		}
		if (gpu!=null) {
			total += gpu.getNumberPrice();
		}
		if (powerSupply!=null) {
			total += powerSupply.getNumberPrice();
		}
		if (chasis!=null) {
			total += chasis.getNumberPrice();
		}
		if (ssd!=null) {
			total += ssd.getNumberPrice();
		}
		if (hdd!=null) {
			total += hdd.getNumberPrice();
		}
		return total;
	}
}
