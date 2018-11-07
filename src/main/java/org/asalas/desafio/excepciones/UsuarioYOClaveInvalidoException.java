package org.asalas.desafio.excepciones;

public class UsuarioYOClaveInvalidoException extends Exception {
	public UsuarioYOClaveInvalidoException() {
		super("Usuario o Clave invalido.");
	}
}
