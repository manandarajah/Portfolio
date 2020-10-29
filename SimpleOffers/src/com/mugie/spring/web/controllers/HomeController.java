package com.mugie.spring.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mugie.spring.web.dao.Offer;

@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String showHome() {
		
		logger.debug("Showing home page...");
		
		return "home";
	}
}
