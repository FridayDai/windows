package pages.mail

import geb.Page

class GmailAppPage extends Page {
	static at = { title.endsWith("- Gmail") }

	static content = {
		searchInput { $('#gbqfq') }
		searchButton { $('#gbqfb') }
	}
}
