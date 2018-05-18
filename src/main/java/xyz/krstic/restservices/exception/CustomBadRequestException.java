package xyz.krstic.restservices.exception;

public class CustomBadRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomBadRequestException(String message) {
		super(message);
	}
	
}