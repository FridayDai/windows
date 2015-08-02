package com.ratchethealth.admin.pages

class LoginPage extends RatchetPage {
	static url = "/login"

	static at = { $("#loginForms").size() == 1 }

	static content = {
		emailInput { $("#email") }
		passwordInput { $("#password") }
		loginButton { $("button", type: "submit") }
	}

	static ADMIN_ACCOUNT = "admin@ratchethealth.com"
	static ADMIN_PASSWORD = "qEWD2LDvE9MWrR"

	void login(account = ADMIN_ACCOUNT, password = ADMIN_PASSWORD) {
		emailInput << account
		passwordInput << password

		loginButton.click()
	}
}
