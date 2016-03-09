package pages.client

import geb.Page

class LoginPage extends Page {
    static url = "/login"

    static at = { $(".login-form") }

    static content = {
        emailInput { $(".email") }
        passwordInput { $("input[name='password']") }
        loginButton { $("#btnLogin") }
    }

    def checkAutoSetEmail(email) {
        when: "Wait for email input appear"
        waitFor(10, 1) { emailInput.displayed }

        then: 'Checking auto set email value'
        emailInput.value() == email
    }

    def login(email, password) {
        when: "Wait for email input appear"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in email and password"
        emailInput << email
        passwordInput << password

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            browser.at PatientsPage
        }
    }
}
