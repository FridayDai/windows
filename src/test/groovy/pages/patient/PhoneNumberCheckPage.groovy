package pages.patient

import geb.Page
import model.PatientModel

class PhoneNumberCheckPage extends Page {

    static at = { $("#intro-form").size() == 1 }

    static content = {
        phoneNumberInput { $('input[name = "last4Number"]') }
        startButton { $(".btn-start-task") }
    }

    def startTask(PatientModel patient){
        when:
        waitFor(30,1) {
            phoneNumberInput.displayed
            startButton.displayed
        }

        and:"Type last 4 number and start to complete tasks"
        phoneNumberInput.value(patient.lastFourNumber)

        and:
        startButton.click()

        then:
        waitFor (30,1){
            browser.at TaskIntroPage
        }
    }
}
