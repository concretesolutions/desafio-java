package br.com.desafio.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import br.com.desafio.enums.ProfileEnum;

public class UserDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 237892305251059312L;

	private Long id;
	
	private Calendar created;
	
	private Calendar modified;
	
	private Calendar lastLogin;
	
	private String token;

	private String name;
	
	private String email;
	
	private String password;
	
	private List<PhoneDto> phones;
	
	private ProfileEnum profile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}
	
}
