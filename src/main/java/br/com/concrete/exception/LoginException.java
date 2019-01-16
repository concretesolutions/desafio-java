package br.com.concrete.exception;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 6925430246577029457L;

	public LoginException(final String message) {
		super(message);
	}

}
