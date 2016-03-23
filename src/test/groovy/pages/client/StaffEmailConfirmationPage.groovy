package pages.client

import geb.Page

class StaffEmailConfirmationPage extends Page {
	static at = { $(".create-password-form") }

	static content = {
		newPassword { $("#password") }
        confirmPassword { $("#confirmPassword") }
        activeButton { $("button", type: "submit")}
	}

	def setPassword(password) {
		and:
		waitFor(30, 1) { newPassword.displayed }

		and:
		newPassword << password
		confirmPassword << password

		and:
		activeButton.click()

	}
}
