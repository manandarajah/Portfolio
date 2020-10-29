package com.mugie.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mugie.spring.web.dao.Offer;
import com.mugie.spring.web.service.OffersService;

@Controller
public class OffersController {
	
	private OffersService service;

	@Autowired
	public void setService(OffersService service) {
		this.service = service;
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		
		System.out.println("ID is : " + id);
		return "home";
	}
	
	/*@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	}*/
	
	@RequestMapping("/offers")
	public String showOffers(Model model) {
		
		//service.throwTestException();
		
		List<Offer> offers = service.getCurrent();
		
		model.addAttribute("offers", offers);
		
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String createOffer(Model model) {
		
		model.addAttribute("offer", new Offer());
		
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result) {
		
		if (result.hasErrors()) {
			return "createoffer";
		}
		
		service.create(offer);
		
		return "offercreated";
	}
}