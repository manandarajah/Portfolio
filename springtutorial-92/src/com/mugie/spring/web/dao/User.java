package com.mugie.spring.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.mugie.spring.web.validation.ValidEmail;

@Entity
@Table(name="users")
public class User {
	
	@NotBlank(message="Username cannot be blank", groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=8, max=15, groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\w{8,}$", message="Username can only consists of letters, numbers and the underscore character", groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Id
	@Column(name="username")
	private String username;
	
	@NotBlank(message="Password cannot be blank", groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Size(min=8, max=15, message="Password must be between 8 and 15 characters", groups= {FormValidationGroup.class})
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private boolean enabled = false;
	
	@Column(name="authority")
	private String authority;
	
	@ValidEmail(groups= {PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="email")
	private String email;
	
	public User() {
		
	}
	
	public User(String username, String password, boolean enabled, String authority, String email) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
