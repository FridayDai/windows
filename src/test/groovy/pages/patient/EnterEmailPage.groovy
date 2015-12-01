package pages.patient

import geb.Page


class EnterEmailPage extends Page {
    static at = { title.startsWith("Enter email") }
    static content = {
        emailInput { $("#email") }
        enterButton { $("#enter-email-button") }
        skipButton { $("#skip-button") }
    }
}
