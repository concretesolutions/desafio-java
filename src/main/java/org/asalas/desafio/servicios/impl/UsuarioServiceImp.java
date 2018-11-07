package org.asalas.desafio.servicios.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.asalas.desafio.config.JwtTokenUtils;
import org.asalas.desafio.converters.PasswordUtils;
import org.asalas.desafio.domain.Phone;
import org.asalas.desafio.domain.ProfileEnum;
import org.asalas.desafio.domain.Usuario;
import org.asalas.desafio.repo.UsuarioRepo;
import org.asalas.desafio.excepciones.EmailExisteException;
import org.asalas.desafio.excepciones.NoAutorizadoException;
import org.asalas.desafio.excepciones.SessionInvalidaException;
import org.asalas.desafio.servicios.PhoneService;
import org.asalas.desafio.servicios.UsuarioService;
import org.asalas.desafio.excepciones.UsuarioYOClaveInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {
	@Autowired
	private UsuarioRepo repo;

	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtil;
	
	@Override
	@Transactional
	public Usuario register(Usuario u) throws EmailExisteException {
		if (repo.findByEmail(u.getEmail()) != null) {
			throw new EmailExisteException();
		}
		
		Calendar calendar = Calendar.getInstance();
		
		String password = PasswordUtils.generateBCrypt(u.getPassword());
		
		List<Phone> phones = new ArrayList<>();
		for (Phone phone : u.getPhones()) {
			phones.add(phoneService.save(phone));
		}
		
		u.setCreated(calendar);
		u.setModified(calendar);
		u.setLastLogin(calendar);
		u.setPassword(password);
		u.setPhones(phones);
		u.setProfile(ProfileEnum.ROLE_USER);
		
		u = repo.save(u);
		UserDetails userDetails = userDetailsService.loadUserByUsername(u.getEmail());
		
		String token = jwtTokenUtil.getToken(userDetails);
		u.setToken(token);
		u = repo.save(u);
		return u;
	
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return Optional.ofNullable(repo.findByEmail(email));
	}

	@Override
	public Usuario login(String email, String password) throws UsuarioYOClaveInvalidoException {
		Usuario u = repo.findByEmail(email);
		
		if (u == null) {
			throw new UsuarioYOClaveInvalidoException();
		}
		
		if (PasswordUtils.validPassword(password, u.getPassword()) == false) {
			throw new UsuarioYOClaveInvalidoException();
		}
		
		Calendar calendar = Calendar.getInstance();
		
		u.setModified(calendar);
		u.setLastLogin(calendar);
		
		return repo.save(u);
	}

	@Override
	public Usuario getUser(Long id, String token) throws NoAutorizadoException, SessionInvalidaException {
		Optional<Usuario> user = repo.findById(id);
		
		if (user == null) {
			throw new NoAutorizadoException();
		} 
		Usuario  u =  user.get();
		if (!u.getToken().equals(token)) {
			throw new NoAutorizadoException();
		}
		
		long difference = System.currentTimeMillis() - u.getLastLogin().getTimeInMillis();
		long differenceInMinutes = difference / 60000;
		if (differenceInMinutes >= 30) {
			throw new SessionInvalidaException();
		}
		
		return u;
	}
}
