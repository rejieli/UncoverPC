package com.uncoverpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.searchProcess.Computer.CustomPC;
import com.uncoverpc.searchProcess.Controller.Computers;

@Controller
public class CentralController{

	/**
	 * 
	 * @return index page
	 */
	// Home page
	@RequestMapping("/")
	public String getIndex() {
		return "/pages/index.html";
	}

	/**
	 * 
	 * @return search page
	 */
	// SearchPage
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String getLink() {
		return "/pages/search.html";
	}

	/**
	 * Starts search process, and displays result
	 * @param link, String
	 * @return result page
	 */
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public ModelAndView result(@RequestParam("link") String link) {
		//Adding to stats
		stats.addSearches();
		try {
			// Search Process
			Computers computers = com.uncoverpc.searchProcess.Controller.Controller.searchPC(link, true, true, true);
			// Checking for errors
			if (!computers.getCustomPC().getIsError()) {
				ModelAndView model = new ModelAndView("/pages/result.jsp");
				// Prebuilt
				model.addObject("PREBUILTPC", resultController.getPrebuiltComputer(computers.getPc()));
				// CustomPC
				model.addObject("CUSTOMPC", resultController.formatPC(computers.getCustomPC()));
				// Savings
				model.addObject("SAVINGS", resultController.getSavings(computers));
				return model;
			}else {
				//Identifying error
				String error = errorController.getErrorType(computers.getCustomPC().getErrorCode());
				ModelAndView errorModel = new ModelAndView("/pages/error.jsp");
				errorModel.addObject("errorType", error);
				errorModel.addObject("errorInfo", errorController.getErrorMessage(error));
				errorModel.addObject("solution", errorController.getSolution(error));
				return errorModel;
			}
		} catch (Exception e) {
			// Error
			ModelAndView errorModel = new ModelAndView("/pages/error.jsp");
			errorModel.addObject("errorTitle", "Unexpected Error :/");
			errorModel.addObject("solution", "No solution provided.");
			return errorModel;
		}
	}
	
	/**
	 * 
	 * @return how to page
	 */
	//HowTo page
	@RequestMapping("/howTo")
	public String getHowToPage() {
		return "/pages/howTo.html";
	}

}
