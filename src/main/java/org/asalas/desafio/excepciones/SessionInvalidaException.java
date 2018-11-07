package org.asalas.desafio.excepciones;

public class SessionInvalidaException extends Exception {
	public SessionInvalidaException() {
		super("La sesion es invalida");
	}
}
