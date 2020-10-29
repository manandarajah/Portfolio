package com.mugie.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mugie.spring.web.dao.User;
import com.mugie.spring.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/mugie/spring/web/config/dao-context.xml",
		"classpath:com/mugie/spring/web/config/security-context.xml",
		"classpath:com/mugie/spring/web/test/config/beans.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersDAOTest {
	
	@Autowired
	private UsersDAO dao;
	
	@Autowired
	private BasicDataSource beans;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(beans);
	}

	@Test
	public void testCreateUser() {
		User user = new User("classobject", "xminibike6000", true, "user", "class@object.com");
		
		dao.create(user);
		
		assertEquals("Dummy test", 1, 1);
	}
}
