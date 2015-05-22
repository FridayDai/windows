package pages.admin

import geb.Page

class LoginPage extends Page {
	static url = "/login"

	static at = { $("#loginForm") }

	static content = {
		emailInput { $("#email") }
		passwordInput { $("#password") }
		loginButton { $("button", type: "submit") }
	}
}
