package org.asalas.desafio.excepciones;

public class NoAutorizadoException extends Exception {
	public NoAutorizadoException(){
		super("Usuario no Autorizado");
	}
}
