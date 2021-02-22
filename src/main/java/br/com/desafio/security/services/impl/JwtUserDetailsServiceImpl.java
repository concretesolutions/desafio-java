package br.com.desafio.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.desafio.entities.User;
import br.com.desafio.security.JwtUserFactory;
import br.com.desafio.services.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByEmail(username);
		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}
		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
