package pages.mail

import geb.Page

class GmailRevisitPasswordPage extends Page {
    static at = { $("#gaia_loginform") }

    static content = {
        passwordInput { $("#Passwd") }
        signInButton { $("#signIn") }
    }
}
