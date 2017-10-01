package br.com.desafio.exceptions;

public class EmailAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8798696866122727742L;

	public EmailAlreadyExistsException() {
		super("E-mail já existente");
	}

}
