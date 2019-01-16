package br.com.concrete.exception;

public class ValidateException extends RuntimeException{

	private static final long serialVersionUID = -9158955992805570172L;

	public ValidateException(final String message) {
		super(message);
	}
}
