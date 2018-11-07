package org.asalas.desafio.servicios.impl;

import java.util.Optional;

import org.asalas.desafio.config.JwtUserFactory;
import org.asalas.desafio.domain.Usuario;
import org.asalas.desafio.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UsuarioService serv;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = serv.findByEmail(username);
		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}
		throw new UsernameNotFoundException("Email no encontrado");
	}

}
