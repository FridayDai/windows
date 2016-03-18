package specs.client
import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise
import utils.ModelHelper

@Stepwise
class PatientWithoutEmailFunctionalSpec extends RatchetFunctionalSpec {

//    def setupSpec() {
//        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
//
//        GMAIL_WINDOW = ''
//
//        AGENT_FIRST_NAME = "FN+ast${IDENTIFY}"
//        AGENT_LAST_NAME = "AST"
//        GROUP_NAME = "group${IDENTIFY}"
//
//        ACCOUNT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
//        ACCOUTN_PASSWORD = "K(mRseYHZ>v23zGt23409"
//
//        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
//        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
//
//        PROVIDER_FIRST_NAME = "FN+pro${IDENTIFY}"
//        PROVIDER_LAST_NAME = "Provider"
//
//        PATIENT_EMAIL = "ratchet.testing+pat1${IDENTIFY}@gmail.com"
//        PATIENT_ID = "fdsfdsfdsf"
//
//        PATIENT_PHONENUMBER = "6265557777"
//        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
//        PATIENT_LAST_NAME = "Patient"
//
//        CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
//        CAREGIVER_LAST_NAME = "Caregiver"
//        CAREGIVER_EMAIL = "ratchet.testing+car${IDENTIFY}@gmail.com"
//
//        PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
//    }


    def "should login with the activate account created by client successfully"() {

        when: 
        def loginPage = new LoginPage()
        to loginPage

        and:
        loginPage.login(account.email,account.password)
        //loginPage.login("875606747@qq.com","92623Daiyi")

        then:
        loginPage.goToPatientsPage()
    }

    def "add patient successfully and go to patient detail page"() {
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        then:
        patientsPage.addNewPatient(patient)
    }

}