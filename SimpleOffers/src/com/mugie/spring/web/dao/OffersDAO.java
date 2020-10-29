package com.mugie.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

//import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	public OffersDAO() {
		System.out.println("Successfully loaded offers DAO");
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {
		//MapSqlParameterSource params = new MapSqlParameterSource("name", "Sue");
		//params.addValue("name","Sue");
		
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		});
	}
	
	public Offer getOffer(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		//params.addValue("name","Sue");
		params.addValue("id", id);
		
		return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
			
		});
	}
	
	public boolean create(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert ignore into offers (name,email,text) values (:name,:email,:text)", params) == 1;
	}
	
	@Transactional // when you use this annotation, it basically means this method will either completely succeed or fail, there's no in-between
	public int[] create(List<Offer> offers) {
		SqlParameterSource[] batchValues = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert ignore into offers (name,email,text) values (:name,:email,:text)", batchValues);
	}
	
	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("update offers set name=:name,email=:email,text=:text where id=:id", params) == 1;
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id = :id", params) == 1;
	}
}
