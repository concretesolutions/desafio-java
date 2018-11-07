package org.asalas.desafio.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;


public class JwtUsuario implements UserDetails {

	private Long id;
	
	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
 	public JwtUsuario(Long id, String username, String password, 
 			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
 	
 	public Long getId() {
		return id;
	}
 	@Override
	public String getUsername() {
		return username;
	}
 	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
 	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
 	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
 	@Override
	public String getPassword() {
		return password;
	}
 	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
 	@Override
	public boolean isEnabled() {
		return true;
	}

}
