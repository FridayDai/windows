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
		when: "Wait for new password input appear"
		waitFor(30, 1) { newPassword.displayed }

		and: "Type in password and repeat password"
		newPassword << password
		confirmPassword << password

		and: "Click active button"
		activeButton.click()

		then: "Direct to client login page"
		Thread.sleep(2000 as long)
		waitFor(30, 1) {
            browser.at LoginPage
		}
	}
}
