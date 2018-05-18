package xyz.krstic.restservices.exception;

public class CustomNoContentException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomNoContentException(String message) {
		super(message);
	}
	
}