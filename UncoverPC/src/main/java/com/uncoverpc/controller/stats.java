package com.uncoverpc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class stats {

	/**
	 * adds to the total searches
	 */
	public static void addSearches() {
		try {
			// File
			File stats = new File("stats.txt");
			// Getting Data
			Scanner scanner = new Scanner(stats);
			int searches = Integer.parseInt(scanner.next());

			// Overwriting data
			PrintWriter pw = new PrintWriter(stats);
			pw.print(searches + 1);
			System.out.println("Searches Performed: " + (searches + 1));
			pw.close();
		} catch (FileNotFoundException e) {
			// Error
			System.err.println("Could not add stats");
		}
	}

	/**
	 * 
	 * @return num of total searches
	 */
	public static int getSearches() {
		try {
			// File
			File stats = new File("stats.txt");
			// Getting Data
			Scanner scanner = new Scanner(stats);
			int searches = Integer.parseInt(scanner.next());
			return searches;

		} catch (FileNotFoundException e) {
			//Error
			System.err.println("Could not get stats");
		}
		return 0;
	}

}
