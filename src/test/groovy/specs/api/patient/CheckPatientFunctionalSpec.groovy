package specs.api.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.*

@Stepwise
class CheckPatientFunctionalSpec extends RatchetFunctionalSpec {

    @Shared ACCOUNT_EMAIL
    @Shared ACCOUTN_PASSWORD
    @Shared IDENTIFY
    @Shared PATIENTID
    @Shared PATIENT_NAME
    @Shared PATIENT_PHONE

    def setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        ACCOUNT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
        ACCOUTN_PASSWORD = "K(mRseYHZ>v23zGt23409"
        PATIENTID = "api${IDENTIFY}"
        PATIENT_NAME = "colin chen"
        PATIENT_PHONE = "231-323-1312"
    }
    def "should login with the activate agent successfully"() {
        browser.setBaseUrl(getClientUrl())

        when: "Login with agent account"
        to LoginPage

        and: "Wait for email input appear"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in email and password"
        emailInput.value('')
        emailInput << ACCOUNT_EMAIL
        passwordInput << ACCOUTN_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }
    }

    def "search if the patient add by api successfully"() {
        when: "at the patients page"
        at PatientsPage
        Thread.sleep(2000)
        and: "search the patient"
        searchInput.click()
        searchInput << PATIENTID
        and: "click the search icon"
        searchButton.click()

        and: "wait for patient table displayed"
        waitFor(10,1) {
            patientsTable.displayed
        }
        Thread.sleep(2000)
        then: "check the whether the patient exist"
        $("tr", 1).find("td", 0).text() == PATIENTID
        assert patientName.text() == PATIENT_NAME
        assert patientPhone.text() == PATIENT_PHONE
        Thread.sleep(1000)
        report "this page"
    }
}
