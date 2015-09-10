package pages.patient

import geb.Page

class PhoneNumberCheckPage extends Page {

    static at = { $("#intro-form").size() == 1 }

    static content = {
        phoneNumberInput { $('input[name = "last4Number"]') }
        startButton { $(".btn-start-task") }
    }
}
