package com.ratchethealth.admin.exceptions

class AccountValidationException extends Exception {
	private Integer limitSeconds

	AccountValidationException() {
		super()
	}

	AccountValidationException(String message) {
		super(message)
	}

	AccountValidationException(String message, Integer time) {
		super(message)
		this.limitSeconds = time
	}
}
