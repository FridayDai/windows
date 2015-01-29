package exceptions;

public class ClientValidationException extends Exception {
	public ClientValidationException() {
		super();
	}

	public ClientValidationException(String message) {
		super(message);
	}
}
