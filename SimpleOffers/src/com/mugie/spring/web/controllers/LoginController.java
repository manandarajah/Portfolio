package com.mugie.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mugie.spring.web.dao.User;
import com.mugie.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	private UsersService service;

	@Autowired
	public void setService(UsersService service) {
		this.service = service;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = service.getAllUsers();
			
		model.addAttribute("users", users);
		
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {
		
		if (result.hasErrors()) {
			return "newaccount";
		}
		
		user.setAuthority("user");
		user.setEnabled(true);
		
		if (service.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		try {
			service.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		return "accountcreated";
	}
}