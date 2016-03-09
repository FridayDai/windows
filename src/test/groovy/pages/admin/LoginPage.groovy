package pages.admin

import geb.Page

class LoginPage extends Page {
	static url = "/login"

	static at = { $("#loginForm").size() == 1 }

	static content = {
		emailInput { $("#email") }
		passwordInput { $("#password") }
		loginButton { $("button", type: "submit") }
	}

	def login(account, password) {
		emailInput << account
		passwordInput << password

		loginButton.click()
	}
}
