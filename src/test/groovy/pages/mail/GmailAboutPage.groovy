package pages.mail

import geb.Page

class GmailAboutPage extends Page {
	static url = "/intl/en/mail/help/about.html"

	static content = {
		signInLink { $("#gmail-sign-in") }
	}
}
