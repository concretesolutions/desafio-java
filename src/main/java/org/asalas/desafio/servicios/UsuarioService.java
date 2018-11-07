package org.asalas.desafio.servicios;

import java.util.Optional;

import org.asalas.desafio.domain.Usuario;
import org.asalas.desafio.excepciones.EmailExisteException;
import org.asalas.desafio.excepciones.NoAutorizadoException;
import org.asalas.desafio.excepciones.SessionInvalidaException;
import org.asalas.desafio.excepciones.UsuarioYOClaveInvalidoException;

public interface UsuarioService {
	public Usuario register(Usuario u) throws EmailExisteException;
	
	public Optional<Usuario> findByEmail(String email);
	
	public Usuario login(String email, String password) throws UsuarioYOClaveInvalidoException;
	
	public Usuario getUser(Long id, String token) throws NoAutorizadoException, SessionInvalidaException;
}
