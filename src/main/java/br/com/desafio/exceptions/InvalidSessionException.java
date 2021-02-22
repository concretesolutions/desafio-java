package br.com.desafio.exceptions;

public class InvalidSessionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1884062971921471142L;

	public InvalidSessionException() {
		super("Sessão inválida");
	}

}
