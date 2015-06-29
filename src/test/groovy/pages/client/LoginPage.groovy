package pages.client

import geb.Page

class LoginPage extends Page {
    static url = "/login"

    static at = { $(".login-form") }

    static content = {
        emailInput { $(".email") }
        passwordInput { $("input[name='password']") }
        loginButton { $("button", type: "submit") }
    }
}
