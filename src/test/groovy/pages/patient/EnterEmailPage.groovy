package pages.patient

import geb.Page


class EnterEmailPage extends Page {
    static at = { title.startsWith("Enter email") }
    static content = {
        emailInput { $("#email") }
        enterButton { $("#enter-email-button") }
        skipButton { $("#skip-button") }
    }

    def inputEmail(email){
        when:"wait for enterEmail model displayed"
        waitFor(30,1) {
            emailInput.displayed
        }

        and: "type in patient email"
        emailInput.value('')
        emailInput << email
        Thread.sleep(2000)
        enterButton.click()

        then: "at task complete page"
        waitFor(30,1) {
            browser.at TaskCompletePage
        }
    }
}
