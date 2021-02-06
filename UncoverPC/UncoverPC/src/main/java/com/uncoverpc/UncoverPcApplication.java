package com.uncoverpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uncoverpc.controller.CoreController;
import com.uncoverpc.searchProcess.Controller.Controller;

@SpringBootApplication
public class UncoverPcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UncoverPcApplication.class, args);
	}

}
