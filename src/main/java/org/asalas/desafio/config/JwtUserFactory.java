package org.asalas.desafio.config;

import java.util.ArrayList;
import java.util.List;

import org.asalas.desafio.domain.JwtUsuario;
import org.asalas.desafio.domain.ProfileEnum;
import org.asalas.desafio.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {
	private JwtUserFactory() {
		
	}
	
	public static JwtUsuario create(Usuario user) {
		return new JwtUsuario(user.getId(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getProfile()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}
}
