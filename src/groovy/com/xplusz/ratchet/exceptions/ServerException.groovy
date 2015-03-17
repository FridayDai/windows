package com.xplusz.ratchet.exceptions

class ServerException extends Exception {

	private Integer statusId;

	ServerException() {
		super()
	}

	ServerException(String message) {
		super(message)
	}

	ServerException(Integer status, String message) {
		super(message)
		this.statusId = status
	}
}
