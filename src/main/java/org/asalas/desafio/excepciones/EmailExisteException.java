package org.asalas.desafio.excepciones;

public class EmailExisteException extends Exception {
	public EmailExisteException() {
		super("Correo ya existe en la base de datos");
	}
}
