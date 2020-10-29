package com.mugie.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("usersDao")
public class UsersDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UsersDAO() {
		System.out.println("Successfully loaded users DAO");
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean create(User user) {
		/*user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);*/
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());
		
		jdbc.update("insert into users (username, password, email, enabled) values (:username, :password, :email, :enabled)", params);
		
		return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params) == 1;
	}
	
	@Transactional
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username = :username", 
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		//return session().createQuery("from User").list();
		return jdbc.query("select * from users, authorities where users.username = authorities.username",
				BeanPropertyRowMapper.newInstance(User.class));
	}
}
