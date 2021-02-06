package com.uncoverpc.searchProcess.Controller;

import com.uncoverpc.searchProcess.Computer.CustomPC;
import com.uncoverpc.searchProcess.Computer.PC;

public class Computers {

	//Variables
	private CustomPC customPC;
	private PC pc;
	
	/**
	 * Constructor
	 * @param customPC, CustomPC
	 * @param pc, PC
	 */
	public Computers(CustomPC customPC, PC pc) {
		this.customPC = customPC;
		this.pc = pc;
	}

	/**
	 * 
	 * @return customPC
	 */
	public CustomPC getCustomPC() {
		return customPC;
	}

	/**
	 * 
	 * @return pc
	 */
	public PC getPc() {
		return pc;
	}
	
	
	
}
