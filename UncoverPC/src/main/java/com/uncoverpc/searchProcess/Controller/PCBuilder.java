package com.uncoverpc.searchProcess.Controller;

import com.uncoverpc.searchProcess.Computer.CPUSearch;
import com.uncoverpc.searchProcess.Computer.ChasisSearch;
import com.uncoverpc.searchProcess.Computer.Components;
import com.uncoverpc.searchProcess.Computer.CustomPC;
import com.uncoverpc.searchProcess.Computer.GPUSearch;
import com.uncoverpc.searchProcess.Computer.HDDSearch;
import com.uncoverpc.searchProcess.Computer.MotherboardSearch;
import com.uncoverpc.searchProcess.Computer.PC;
import com.uncoverpc.searchProcess.Computer.PSSearch;
import com.uncoverpc.searchProcess.Computer.RAMSearch;
import com.uncoverpc.searchProcess.Computer.SSDSearch;

public class PCBuilder implements Runnable {

	//Variables
	private PC pc;
	private boolean amazonSearch;
	private boolean canadaComputerSearch;
	private boolean neweggSearch;
	private CustomPC similarPC;
	private CustomPC relativePC;

	/**
	 * Constructor
	 * @param pc, PC
	 * @param amazonSearch, boolean
	 * @param canadaComputerSearch, boolean
	 * @param neweggSearch, boolean
	 */
	public PCBuilder(PC pc, boolean amazonSearch, boolean canadaComputerSearch, boolean neweggSearch) {
		this.pc = pc;
		this.amazonSearch = amazonSearch;
		this.canadaComputerSearch = canadaComputerSearch;
		this.neweggSearch = neweggSearch;
	}

	/**
	 * Searches all components with threads
	 */
	public void run() {
		// Threads
		Thread[] threads = new Thread[8];
		//Searching for all components
		// CPU
		System.out.println("Searching...");
		CPUSearch cpuSearch = new CPUSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[0] = new Thread(cpuSearch);
		threads[0].start();
		// GPU
		GPUSearch gpuSearch = new GPUSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[1] = new Thread(gpuSearch);
		threads[1].start();
		// RAM
		RAMSearch ramSearch = new RAMSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[2] = new Thread(ramSearch);
		threads[2].start();
		// Power Supply
		PSSearch psSearch = new PSSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[3] = new Thread(psSearch);
		threads[3].start();
		// Hard Drive
		HDDSearch hddSearch = new HDDSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[4] = new Thread(hddSearch);
		threads[4].start();
		// Solid State Drive
		SSDSearch ssdSearch = new SSDSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[5] = new Thread(ssdSearch);
		threads[5].start();
		// Motherboard
		MotherboardSearch motherboardSearch = new MotherboardSearch(pc, amazonSearch, canadaComputerSearch,
				neweggSearch);
		threads[6] = new Thread(motherboardSearch);
		threads[6].start();
		// Chasis
		ChasisSearch chasisSearch = new ChasisSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		threads[7] = new Thread(chasisSearch);
		threads[7].start();

		// Stopping all threads
		try {
			for (int i = 0; i < threads.length; i++) {
				threads[i].join();
			}
		} catch (Exception e) {
			System.err.println("Error joinning threads...");
		}
		
		//Collecting results
		Components cpu = cpuSearch.getComponents();
		Components motherboard = motherboardSearch.getComponents();
		Components ram = ramSearch.getComponents();
		Components hdd = hddSearch.getComponents();
		Components ssd = ssdSearch.getComponents();
		Components gpu = gpuSearch.getComponents();
		Components ps = psSearch.getComponents();
		Components chasis = chasisSearch.getComponents();

		System.out.println("Searching Done!");
		
		//Creating new similarPC
		similarPC = new CustomPC(cpu.getSimilarComponent(), motherboard.getSimilarComponent(),
				ram.getSimilarComponent(), hdd.getSimilarComponent(), ssd.getSimilarComponent(),
				gpu.getSimilarComponent(), ps.getSimilarComponent(), chasis.getSimilarComponent());
		
		//Creating new relativePC
		relativePC = new CustomPC(cpu.getRelativeComponent(), motherboard.getRelativeComponent(),
				ram.getRelativeComponent(), hdd.getRelativeComponent(), ssd.getRelativeComponent(),
				gpu.getRelativeComponent(), ps.getRelativeComponent(), chasis.getRelativeComponent());
		
	}

	// FOR INDIVIDUAL TESTING PURPOSES
	
	/**
	 * Finds similar/exact component
	 * @return CPU
	 */
	public Components searchCPU() {
		CPUSearch cpuSearch = new CPUSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(cpuSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpuSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return GPU
	 */
	public Components searchGPU() {
		GPUSearch gpuSearch = new GPUSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(gpuSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gpuSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return RAM
	 */
	public Components searchRAM() {
		RAMSearch ramSearch = new RAMSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(ramSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ramSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return PSU
	 */
	public Components searchPS() {
		PSSearch psSearch = new PSSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(psSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return psSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return HDD
	 */
	public Components searchHDD() {
		HDDSearch hddSearch = new HDDSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(hddSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hddSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return SSD
	 */
	public Components searchSSD() {
		SSDSearch ssdSearch = new SSDSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(ssdSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ssdSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return Motherboard
	 */
	public Components searchMotherboard() {
		MotherboardSearch motherboardSearch = new MotherboardSearch(pc, amazonSearch, canadaComputerSearch,
				neweggSearch);
		Thread thread = new Thread(motherboardSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return motherboardSearch.getComponents();
	}

	/**
	 * Finds similar/exact component
	 * @return Chasis
	 */
	public Components searchChasis() {
		ChasisSearch chasisSearch = new ChasisSearch(pc, amazonSearch, canadaComputerSearch, neweggSearch);
		Thread thread = new Thread(chasisSearch);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chasisSearch.getComponents();
	}

	//Getters
	
	/**
	 * 
	 * @return PC
	 */
	public PC getPC() {
		return pc;
	}

	/**
	 * 
	 * @return similarPC
	 */
	public CustomPC getSimilarPC() {
		return similarPC;
	}
	
	/**
	 * 
	 * @return relativePC
	 */
	public CustomPC getRelativePC() {
		return relativePC;
	}
	
}
