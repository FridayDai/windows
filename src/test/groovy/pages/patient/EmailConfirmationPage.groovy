package pages.patient

import geb.Page

class EmailConfirmationPage extends Page {
    static at = { title.startsWith("Email Confirmation") }

    static content = {
        emailConfirmText { $("p.message") }
    }
}
