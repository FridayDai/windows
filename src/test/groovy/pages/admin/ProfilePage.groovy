package pages.admin

import geb.Page

class ProfilePage extends Page {
	static at = { $("a", text: contains('Log Out')) }

	static content = {
		logoutButton { $("a", text: contains('Log Out')) }
	}
}
