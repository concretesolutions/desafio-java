package br.com.desafio.exceptions;

public class UserAndOrPasswordInvalidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -528838676759182010L;

	public UserAndOrPasswordInvalidException() {
		super("Usuário e/ou senha inválidos");
	}

}
