package com.xplusz.ratchet.exceptions

/**
 * Created by sky on 1/6/15.
 */
class AccountValidationException extends Exception{
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
