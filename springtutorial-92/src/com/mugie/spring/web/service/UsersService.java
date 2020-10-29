package com.mugie.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.mugie.spring.web.dao.Offer;
import com.mugie.spring.web.dao.OffersDAO;
import com.mugie.spring.web.dao.User;
import com.mugie.spring.web.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	private UsersDAO dao;
	
	@Autowired
	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}
	
	public void create(User user) {
		dao.create(user);
	}
	
	public boolean exists(String username) {
		return dao.exists(username);
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}
}
