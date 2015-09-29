package pages.admin

import geb.Page

class ProfilePage extends Page {
	static at = { $("a", text: contains('Log Out')).size() == 1 }

	static content = {
		logoutButton { $("a", text: contains('Log Out')) }
	}
}
