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
    def goToPatientsPage(){
        when:
        patientButton.click()
        then:
        waitFor(30,1){
            browser.at PatientsPage
        }
    }

}
