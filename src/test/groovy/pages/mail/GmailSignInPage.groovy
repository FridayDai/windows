package pages.mail

import geb.Page

class GmailSignInPage extends Page {

	static at = { $("#gaia_loginform") }

	static content = {
		emailInput { $("#Email") }
		nextButton { $("#next") }
	}
}
