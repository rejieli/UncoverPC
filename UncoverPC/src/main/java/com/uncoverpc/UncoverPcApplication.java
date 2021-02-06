package com.uncoverpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uncoverpc.controller.stats;

@SpringBootApplication
public class UncoverPcApplication {

	// Main
	public static void main(String[] args) {
		SpringApplication.run(UncoverPcApplication.class, args);
		// Adding to stats
		System.out.println("Searches Performed: " + stats.getSearches());
	}

}
