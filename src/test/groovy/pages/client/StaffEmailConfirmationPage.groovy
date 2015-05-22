package pages.client

import geb.Page

class StaffEmailConfirmationPage extends Page {
	static at = { $("#password") }

	static content = {
		passwordInput { $("#password") }
	}
}
