package com.ratchethealth.admin.exceptions

class AccountValidationException extends Exception {
	private String limitSeconds

	AccountValidationException() {
		super()
	}

	AccountValidationException(String message) {
		super(message)
	}

	AccountValidationException(String message, String time) {
		super(message)
		this.limitSeconds = time
	}
}
