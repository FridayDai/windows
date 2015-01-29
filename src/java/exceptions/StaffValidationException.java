package exceptions;

public class StaffValidationException extends Exception {
	public StaffValidationException() {
		super();
	}

	public StaffValidationException(String message) {
		super(message);
	}
}
