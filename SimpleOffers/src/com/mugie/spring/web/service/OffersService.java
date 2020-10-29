package com.mugie.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.mugie.spring.web.dao.Offer;
import com.mugie.spring.web.dao.OffersDAO;

@Service("offersService")
public class OffersService {
	private OffersDAO dao;
	
	@Autowired
	public void setDao(OffersDAO dao) {
		this.dao = dao;
	}

	public List<Offer> getCurrent() {
		return dao.getOffers();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void create(Offer offer) {
		dao.create(offer);
	}
	
	public void throwTestException() {
		dao.getOffer(999999);
	}
}
