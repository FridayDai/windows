package pages.mail

import geb.Page

class GmailPasswordPage extends Page {
	static at = { $("#gaia_firstform") }

	static content = {
		passwordInput { $("#Passwd") }
		signInButton { $("#signIn") }
	}
}
