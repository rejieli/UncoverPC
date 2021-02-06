package com.uncoverpc.controller;

import com.uncoverpc.searchProcess.Computer.Component;
import com.uncoverpc.searchProcess.Computer.CustomPC;
import com.uncoverpc.searchProcess.Computer.PC;
import com.uncoverpc.searchProcess.Controller.Computers;
import com.uncoverpc.searchProcess.Utilities.Utils;

public class resultController {

	public static String formatPC(CustomPC pc) {
		// Checking if component is null, and formatting it
		StringBuilder code = new StringBuilder();

		// CPU
		if (pc.getCpu() != null) {
			code.append(getVaildComponent(pc.getCpu()));
		} else {

		}
		// Motherboard
		if (pc.getMotherBoard() != null) {
			code.append(getVaildComponent(pc.getMotherBoard()));
		} else {

		}
		// GPU
		if (pc.getGpu() != null) {
			code.append(getVaildComponent(pc.getGpu()));
		} else {

		}
		// RAM
		if (pc.getRam() != null) {
			code.append(getVaildComponent(pc.getRam()));
		} else {

		}
		// HDD
		if (pc.getHdd() != null) {
			code.append(getVaildComponent(pc.getHdd()));
		} else {

		}
		// SSD
		if (pc.getSsd() != null) {
			code.append(getVaildComponent(pc.getSsd()));
		} else {

		}
		// Power Supply
		if (pc.getPowerSupply() != null) {
			code.append(getVaildComponent(pc.getPowerSupply()));
		} else {

		}
		// Chassis
		if (pc.getChasis() != null) {
			code.append(getVaildComponent(pc.getChasis()));
		} else {

		}

		return code.toString();
	}

	private static String getVaildComponent(Component component) {
		StringBuilder formattedComponent = new StringBuilder();
		// ImageLink
		formattedComponent.append("<div class=\"w3-panel w3-border w3-round-large w3-hover-border-green\">\r\n"
				+ "        <!-- Image link -->\r\n" + "        <img style=\"float:left\" ; src=\"");
		formattedComponent.append(component.getImageLink() + "\"");

		// Store Logo
		formattedComponent.append("width=\"80\" height=\"80\">\r\n" + "        <!-- Store Logo -->\r\n"
				+ "        <a target=\"_blank\" href=\"");
		// Sorting Store
		String link = component.getProductLink();
		if (link.contains("canadacomputer")) {
			formattedComponent.append("https://www.canadacomputers.com/" + "\"><img style=\"float:right\" ;\r\n"
					+ "                src=\"https://pbs.twimg.com/profile_images/1151830407433916417/SwjeSpOZ_400x400.jpg\" \r\n"
					+ "                width=\"100\" height=\"100\"></a>");
		} else if (link.contains("amazon")) {
			formattedComponent.append("https://www.amazon.ca/" + "\"><img style=\"float:right\" ;\r\n"
					+ "                src=\"https://i.pinimg.com/originals/18/e1/80/18e180218bdac1685b79197c9023259b.png\"\r\n"
					+ "                width=\"100\" height=\"100\"></a>");
		} else if (link.contains("newegg")) {
			formattedComponent.append("https://www.newegg.ca/" + "\"><img style=\"float:right\" ;\r\n"
					+ "                src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxIQEhMNDxIVFRIWDxAPFhAQFg8QFhIQFRUYFxYXFRUZICggGB0lHxUVIjEhJSkrLi4uFx8zODMsNygtLisBCgoKDg0OGxAQGi0mICUuLS8uLTUtLS8tLy0tLy0tLS0vLS8tLS0tLS0tLS0tLSstLS0tLS8tLi0tLS0rLS4tLf/AABEIAOEA4QMBEQACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAAAwQCBQYBBwj/xABCEAABAwICBgYHBgQFBQAAAAABAAIDBBEFEgYTITFBUQciYXGBoRQyQpGxwdEjUmJygpIkY6LwFUOy4fEzU4OT0v/EABsBAQACAwEBAAAAAAAAAAAAAAADBAIFBgEH/8QANBEBAAIBAgMFBgYBBQEAAAAAAAECAwQREiExBUFRodETInGBkbEUMmHB4fBCBiNSwvEz/9oADAMBAAIRAxEAPwD7igICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAg8c4Dadg5lBSmxWNuwEuP4d3vQVH40fZYB3klBgMZf91v8AV9UFiHGGnY9pHaOt5ILdXXxQt1kr2sadxebX7AN5PYsL5K0je07JcWHJlnakTLm67TyFuyGN0h+8fsmn33d5KjftKkfkiZ8v58m1xdi5bc8lojzn082nm08qD6kcTR2h7z78w+CrW7Ryz0iI8/Rdr2Lgj81pn6R+0sI9Oqob2xEcsrx55l5HaObv2+k+rKexdPPSbfWPRu8K04jkcI5o3McTYFl5Wk9wGYe4q1i7RradrRt5/wAqGo7GvSJtjtvH0/j7OomqGsF3G3Zx9y2LSqE2MD2GE9rtnkgqvxaT8I7gfmg8bi0g5HvH0QW6fGAdjxbtG0e5Bs2OBFwbjmEHqAgICAgICAgICAgIKVVXhuxvWPkPqg1NQ979ryT2cB3B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E1eTVO2ZYTVHNUgmWPCx4XWaNYg2eM0ch6zR1CeLRut2t+Hiug7L1PFT2VusdPh/H2aPtLSzS3ta9J6/H+RzSwlrthBsts1SeOVBZZKglbKgzEqAZEGDpUEL5UHtLAZD+Ebz8gg3DW2Fhu3IPUBAQEBAQEBAQV6ylErcp38DyKDQTwujOVw8eB7ig8bKgzEyDIT22goIsXw+OvYASGVDR1H8HD7p5j4bxxBq6nTRmj9e6V/Q662mt41nrH7x+v3cDW0skDzFK0tcOB4jm08R2rQ5MVqW4bRtLq8WWmavHSd4RtmUU1ZTRIJ15wseBnFVuY4PY4hwNw4bwV7WJrMTHWHlsUWia2jlLtsLxqKuaI3kR1IFgDsbJ+X6bx2roNLrIyxw25W+/wAPRzOu7Nvgnirzr9vj6pJGOYcrhY8ldaxk2VBK2ZBmJkAzIMHTILNLROf1ndVvme5Bt42BoDWiwHBBkgICAgICAgICAgIMJYmvGVwuO1Bq6jBuMbvB31CCk/D5R7BPdYoMBRyfcd7igmjwyU+zbtcR8kF+TCWys1VTaUcLixafwu3g9qjyYqZI2tG6bDnyYbcWOdpcziOgW800v6Jv/tv0Wuydm/8AC319f4brD233Za/OPSfVpJ9Eqxn+VmHNj4z5Eg+SqW0WeP8AHf5wv07U0tv8tvjEoWaM1h2Cnd4mMfErH8Jn/wCE+XqzntHSx/nHn6NjR6D1LrGRzIx3l7h4N2eanp2dlnrtHn/fqrZe2cFfyxM+Uf35O4ocMyRiKWR01vbky3HcRt95J7Vt8WOaV2m0z8XO6jLXLfirWK/pH9+2yCfBuLHeDvqPopECq7DJR7N+4t+aAMPm+75t+qCeLCHn1nAd13FBsKbD2M22uebtvu5ILSAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIP/Z\"\r\n"
					+ "                width=\"100\" height=\"100\"></a>");
		}
		// Product Link
		formattedComponent
				.append("<p><a target=\"_blank\"\r\n" + "                href=\"" + component.getProductLink() + "\">");
		// Product Title
		formattedComponent.append(component.getTitle() + "</a></p>");

		// Price
		formattedComponent.append("<b>" + component.getPrice() + "</b>\n</div>");

		return formattedComponent.toString();
	}

	public static String getPrebuiltComputer(PC pc) {
		StringBuilder formattedPC = new StringBuilder();
		formattedPC.append("      <!-- Selected PC -->\r\n"
				+ "    <div class=\"w3-panel w3-border w3-hover-border-red  w3-ul w3-card-4\"> \r\n" + "    \r\n"
				+ "      <div class=\"infoText\">      \r\n" + "        <!-- Image link -->\r\n"
				+ "        <img style=\"float:left\" ; src=");
		// ImageLink
		formattedPC.append(pc.getImageLink());
		formattedPC.append(
				"\r\n" + "        width=\"350\", height=\"350\">\r\n" + "        <br><a target=\"_blank\" href=\"");
		// Link
		formattedPC.append(pc.getLink() + "\">");
		// Title
		formattedPC.append(pc.getTitle() + "</a></br>\r\n" + "        <br>");
		// Price
		formattedPC.append("Price: " + pc.getPrice() + "</br>\r\n" + "        <br>");
		// CPU
		formattedPC.append("CPU: " + pc.getCpu() + "</br>\r\n" + "        <br>");
		// GPU
		formattedPC.append("GPU: " + pc.getGpu() + "</br>\r\n" + "        <br>");
		// RAM
		formattedPC.append("RAM: " + pc.getRamSize() + "</br>\r\n" + "        <br>");
		// HDD
		formattedPC.append("HDD: " + pc.getHddSize() + "</br>\r\n" + "        <br>");
		// SSD
		formattedPC.append("SSD: " + pc.getSddSize() + "</br>\r\n" + "        <br>");
		// PSU
		formattedPC.append("PSU: " + pc.getPowerSupply() + "</br>\r\n </div>\r\n</div>");

		return formattedPC.toString();
	}

	public static String getSavings(Computers computers) {
		StringBuilder formattedSavings = new StringBuilder();

		// Total Price
		formattedSavings.append("<div><b style=\"font-size: 30px; color: green;\r\n" + 
				"        font-family:\"Times New Roman\", Times, serif;\"><small> Total: $" + Utils.round(computers.getCustomPC().getTotalPrice())
				+ "</small><br> ");
		// Savings
		formattedSavings.append("Savings: $"
				+ Utils.round((computers.getPc().getDoublePrice() - computers.getCustomPC().getTotalPrice()))
				+ "</br></b></div>");

		return formattedSavings.toString();
	}

}
