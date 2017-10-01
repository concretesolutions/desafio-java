package br.com.desafio.services;

import java.util.Optional;

import br.com.desafio.entities.User;
import br.com.desafio.exceptions.EmailAlreadyExistsException;
import br.com.desafio.exceptions.InvalidSessionException;
import br.com.desafio.exceptions.NotAuthorizedException;
import br.com.desafio.exceptions.UserAndOrPasswordInvalidException;

public interface UserService {
	
	public User register(User user) throws EmailAlreadyExistsException;
	
	public Optional<User> findByEmail(String email);
	
	public User login(String email, String password) throws UserAndOrPasswordInvalidException;
	
	public User getUser(Long id, String token) throws NotAuthorizedException, InvalidSessionException;
	
}
