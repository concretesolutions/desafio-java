package br.com.desafio.exceptions;

public class NotAuthorizedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1099185388538609670L;

	public NotAuthorizedException() {
		super("Não autorizado");
	}

}
