package it.food.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -5464030706060936824L;

	public ValidationException(String message) {
		super(message);
	}
}
