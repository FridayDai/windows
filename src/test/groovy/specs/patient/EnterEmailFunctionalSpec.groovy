package specs.patient

import pages.client.LoginPage
import pages.client.PatientsPage
import groovy.json.JsonSlurper
import pages.patient.EnterEmailPage
import pages.patient.TaskCompletePage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class EnterEmailFunctionalSpec extends RatchetFunctionalSpec {
    @Shared PATIENT_EMAIL
    @Shared IDENTIFY
    @Shared PROVIDER_EMAIL
    @Shared PROVIDER_PASSWORD


    def setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        PATIENT_EMAIL = "ratchet.testing+pat1${IDENTIFY}@gmail.com"
        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
    }

    def "should enter email successfully"() {
        when: "at enter email page"
        at EnterEmailPage

        and: "wait for enterEmail model displayed"
        waitFor(30,1) {
            emailInput.displayed
        }

        and: "type in patient email"
        emailInput.value('')
        emailInput << PATIENT_EMAIL
        Thread.sleep(2000)
        enterButton.click()

        then: "at task complete page"
        waitFor(30,1) {
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


}
