package pages.client

import geb.Page

class StaffEmailConfirmationPage extends Page {
	static at = { $(".create-password-form") }

	static content = {
		newPassword { $("#password") }
        confirmPassword { $("#confirmPassword") }
        activeButton { $("button", type: "submit")}
	}
}
