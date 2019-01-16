package br.com.concrete.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class UserResponseDto extends UserDto {

	@JsonIgnore
	private MultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<String, String>();

	private String password;

	private Set<PhoneDto> phones = new HashSet<PhoneDto>();

	private String id;

	private LocalDateTime created;

	private LocalDateTime modified;

	private LocalDateTime last_login;

	public MultiValueMap<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.last_login = lastLogin;
	}

	public LocalDateTime getLastLogin() {
		return last_login;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getModified() {
		return this.modified;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getCreated() {
		return this.created;
	}

	public Set<PhoneDto> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneDto> phones) {
		this.phones = phones;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.responseHeaders.add(HttpHeaders.AUTHORIZATION, token);
	}

}
