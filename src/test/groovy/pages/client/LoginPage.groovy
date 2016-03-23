package pages.client

import geb.Page
import model.StaffModel

class LoginPage extends Page {
    static url = "/login"

    static at = { title.startsWith("Welcome to Ratchet Health") }

    static content = {
        emailInput { $(".email") }
        passwordInput { $("input[name='password']") }
        loginButton { $("#btnLogin") }
        patientButton { $(".icon-patient")}
    }

    def checkAutoSetEmail(email) {
        when: "Wait for email input appear"
        waitFor(10, 1) { emailInput.displayed }

        then: 'Checking auto set email value'
        emailInput.value() == email
    }

    def login(email,password) {
        and: "Wait for email input appear"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in email and password"
        emailInput << email
        and:
        passwordInput << password

        and: "Click login button"
        Thread.sleep(2000 as long)
        loginButton.click()
    }

    def goToPatientsPage(){
        and:
        waitFor(30, 1) {
            patientButton.displayed
        }

        and:
        patientButton.click()
    }
}
