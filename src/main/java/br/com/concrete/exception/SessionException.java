package br.com.concrete.exception;

public class SessionException extends RuntimeException {

	private static final long serialVersionUID = 1873915504545713701L;

	public SessionException(final String message) {
		super(message);
	}

}
