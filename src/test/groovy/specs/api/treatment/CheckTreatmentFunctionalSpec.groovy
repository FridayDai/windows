package specs.api.patient
import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.*

@Stepwise
class CheckTreatmentFunctionalSpec extends RatchetFunctionalSpec {

    @Shared ACCOUNT_EMAIL
    @Shared ACCOUTN_PASSWORD
    @Shared IDENTIFY
    @Shared PATIENTID
    @Shared PATIENT_NAME
    @Shared PATIENT_PHONE
    @Shared TREATMENT_TITLE_FIRST
    def setupSpec() {
        ACCOUTN_PASSWORD = "K(mRseYHZ>v23zGt23409"
        IDENTIFY = new JsonSlurper().parseText(new File(RatchetFunctionalSpec.APP_VAR_PATH).text).IDENTIFY
        PATIENTID = "api${IDENTIFY}"
        PATIENT_NAME = "colin chen"
        PATIENT_PHONE = "231-323-1312"
        TREATMENT_TITLE_FIRST = 'Archived Treatment'
    }
    def "should login with the activate agent successfully"() {
        browser.setBaseUrl(getClientUrl())

        when: "Login with agent account"
        to LoginPage

        and: "Wait for email input appear"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in email and password"

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
        assert patientId.text() == PATIENTID
        assert patientName.text() == PATIENT_NAME
        assert patientPhone.text() == PATIENT_PHONE
        Thread.sleep(2000)
    }

    def "check treatment whether have been deleted"() {
        when: "at patients page"
        at PatientsPage

        and: "click the firstLine to the patients detail page"
        firstLine.click()

        then: "go to the patients detail page"
        waitFor(30,1) {
            at PatientDetailPage
        }
        Thread.sleep(2000)
        assert treatmentTitle.text() == TREATMENT_TITLE_FIRST
        report "this page"
    }
}
