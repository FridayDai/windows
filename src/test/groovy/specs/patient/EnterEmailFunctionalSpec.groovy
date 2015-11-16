package specs.patient

import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.patient.EnterEmailPage
import pages.patient.TaskCompletePage
import specs.RatchetFunctionalSpec
import spock.lang.Shared

/**
 * Created by thomas on 11/16/15.
 */
class EnterEmailFunctionalSpec extends RatchetFunctionalSpec {
    @Shared PATIENT_EMAIL


    def setupSpec() {
        PATIENT_EMAIL = "ratchet.testing+pat1${IDENTIFY}@gmail.com"
    }

    def "should enter email successfully"() {
        when: "to enter email page"
        to EnterEmailPage

        and: "wait for enterEmail model displayed"
        waitFor(30,1) {
            emailInput.displayed
        }

        and: "type in patient email"
        emailInput.value('')
        emailInput << PATIENT_EMAIL
        Thread.sleep(1000)
        enterButton.click()

        then: "at task complete page"
        wait(30,1) {
            at TaskCompletePage
        }
    }

    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: "At login page"
        to LoginPage

        and: "Wait for email input to displayed"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in provider email and password"
        emailInput.value('')
        emailInput << PROVIDER_EMAIL
        passwordInput << PROVIDER_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }
    }

    def "check QuickDASH score in patientDetail after finish it"() {
        when: "Click first line of table"
        firstLine.click()

        then: "Direct to account detail page"
        waitFor(30, 1) {
            at PatientDetailPage
        }

        waitFor(30, 1) {
            QuickDASHCompleteTaskbox.find('.score').text() == '43.18\nTotal Result'
        }
    }
}
