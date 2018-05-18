package xyz.krstic.restservices.exception;

public class CustomInternalServerError extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomInternalServerError(String message) {
		super(message);
	}

}