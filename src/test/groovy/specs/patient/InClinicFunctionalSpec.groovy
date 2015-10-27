package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class InClinicFunctionalSpec extends RatchetFunctionalSpec {
    @Shared IDENTIFY
    @Shared PROVIDER_EMAIL
    @Shared PROVIDER_PASSWORD
    @Shared PATIENT_FIRST_NAME

    def setupSpec() {
        def APP_VAR_PATH = "src/test/resources/var.json"

        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
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
