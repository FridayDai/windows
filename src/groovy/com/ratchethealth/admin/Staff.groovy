package com.ratchethealth.admin

class Staff {
	long id
	long clientId
	String email
	String firstName
	String lastName
	int type = 10 // 9 : Provider, 10: Non-provider
	boolean isDoctor = false
}
