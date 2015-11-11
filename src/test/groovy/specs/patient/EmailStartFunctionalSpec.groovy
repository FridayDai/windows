package specs.patient

import groovy.json.JsonSlurper
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared

/**
 * Created by thomas on 11/10/15.
 */
class EmailStartFunctionalSpec extends RatchetFunctionalSpec {
    @Shared IDENTIFY
    @Shared GMAIL_WINDOW
    @Shared AGENT_FIRST_NAME
    @Shared AGENT_LAST_NAME
    @Shared ACCOUNT_EMAIL
    @Shared ACCOUTN_PASSWORD
    @Shared PROVIDER_EMAIL
    @Shared PROVIDER_PASSWORD
    @Shared PROVIDER_FIRST_NAME
    @Shared PROVIDER_LAST_NAME
    @Shared GROUP_NAME

    @Shared PATIENT_EMAIL
    @Shared PATIENT_ID
    @Shared PATIENT_PHONENUMBER
    @Shared PATIENT_FIRST_NAME
    @Shared PATIENT_LAST_NAME
    @Shared CAREGIVER_FIRST_NAME
    @Shared CAREGIVER_LAST_NAME
    @Shared CAREGIVER_EMAIL


    def setupSpec() {
        def APP_VAR_PATH = "src/test/resources/var.json"

        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        GMAIL_WINDOW = ''

        AGENT_FIRST_NAME = "FN+ast${IDENTIFY}"
        AGENT_LAST_NAME = "AST"
        GROUP_NAME = "group${IDENTIFY}"

        ACCOUNT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
        ACCOUTN_PASSWORD = "K(mRseYHZ>v23zGt23409"

        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
        PROVIDER_FIRST_NAME = "FN+pro${IDENTIFY}"
        PROVIDER_LAST_NAME = "Provider"

        PATIENT_EMAIL = "ratchet.testing+pat${IDENTIFY}@gmail.com"
//        PATIENT_ID = "${IDENTIFY}"
        PATIENT_ID = "test"
        PATIENT_PHONENUMBER = "6265557777"
        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
        PATIENT_LAST_NAME = "Patient"

        CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
        CAREGIVER_LAST_NAME = "Caregiver"
        CAREGIVER_EMAIL = "ratchet.testing+car${IDENTIFY}@gmail.com"
    }

    def "click home button to add a new patient"() {
        when: "check at patientDetail page"
        at PatientDetailPage

        then: "click logo button"
        logoButton.click()
        waitFor(30,1) {
            at PatientsPage
        }
    }

    def "add patient successfully"() {
        when: "At patients page"
        at PatientsPage

        and: "Wait add patient button to displayed"
        waitFor(30, 1) { addPatientButton.displayed }

        and: "Click add patient button"
        addPatientButton.click()

        and: "Wait for treatment model come up"
        waitFor(30, 1) { patientIdModel.displayed }

        and: "Type in patient id"
        patientIdModel.patientId << PATIENT_ID

        and: "Click patient id model create button"
        patientIdModel.createButton.click()

        and: "Wait for agent model disappear"
        waitFor(30, 1) { newPatientModel.displayed }

        and: "Type in patient basic information"
        Thread.sleep(1000 as long)
        newPatientModel.patientFirstName << PATIENT_FIRST_NAME

        Thread.sleep(1000 as long)
        newPatientModel.patientLastName << PATIENT_LAST_NAME

        Thread.sleep(1000 as long)
        newPatientModel.phoneNumber << PATIENT_PHONENUMBER

        Thread.sleep(1000 as long)
        newPatientModel.email << PATIENT_EMAIL

        and: "Type in care giver basic information"
        Thread.sleep(1000 as long)
        newPatientModel.caregiverFirstName << CAREGIVER_FIRST_NAME

        Thread.sleep(1000 as long)
        newPatientModel.caregiverLastName << CAREGIVER_LAST_NAME

        Thread.sleep(1000 as long)
        newPatientModel.caregiverEmail << CAREGIVER_EMAIL

        newPatientModel.relationshipSelect.next().click()
        waitFor(30, 1) { relationshipFirstResult.displayed }
        relationshipFirstResult.click()

        newPatientModel.isPermission.value(true)

        and: "Choose group"
        newPatientModel.groupSelect.next().click()
        waitFor(30, 1) { groupFirstResult.displayed }
        groupFirstResult.click()

        and: "Choose provider"
        newPatientModel.providerSelect.next().click()
        waitFor(30, 1) { providerFirstResult.displayed }
        providerFirstResult.click()

        and: "Choose treatment"
        newPatientModel.treatmentSelect.next().click()
        waitFor(30, 1) { treatmentFirstResult.displayed }
        treatmentFirstResult.click()

        and: "Choose surgery date"
        newPatientModel.surgeryDateSelect.click()
        waitFor(30, 1) { datepickerDate.displayed }
        nextMonthButton.click()

        Thread.sleep(500 as long)
        datepickerDate.click()

        and: "Click new patient create button"
        newPatientModel.createButton.click()

        then: "Treatment should created and displayed on page"
        waitFor(10, 1) {
            at PatientDetailPage
        }
    }
}