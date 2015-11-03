package specs.patient

import groovy.json.JsonSlurper
import pages.client.InClinicPage
import pages.client.InClinicTaskPage
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import pages.patient.TaskIntroPage
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class InClinicFunctionalSpec extends RatchetFunctionalSpec {
    @Shared IDENTIFY
    @Shared PROVIDER_EMAIL
    @Shared PROVIDER_PASSWORD
    @Shared PATIENT_FIRST_NAME
    @Shared INCLINIC_WINDOW
    @Shared CODE
//    @Shared PATIENT_ID1
//    @Shared PATIENT_ID2

    def setupSpec() {
        def APP_VAR_PATH = "src/test/resources/var.json"

        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
        CODE = ''
/*        PATIENT_ID1 = ''
        PATIENT_ID2 = ''*/

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

    def "generate  code of schedule"() {
        when: "Click first line of table"
        firstLine.click()

        then: "Direct to account detail page"
        waitFor(30, 1) {
            at PatientDetailPage
        }
        and:
        waitFor(10,1){
            generateCodeButton.displayed
        }

//        Thread.sleep(1000 as long)

        when:
        generateCodeButton.click()
        waitFor(30,1){
            generateCodeModel.displayed
        }

        CODE = treatmentCode.text()
//        PATIENT_ID2 = patientName.text()

        then: "GenerateCode model display"

        waitFor(10, 1) {
            at PatientDetailPage
        }

        Thread.sleep(2000)
    }



    def "Click to other page"(){
        when: "go to url"
        at PatientDetailPage
        waitFor(30,1) {
            codeLink.displayed
        }

        then:
        withNewWindow({ codeLink.click() },  wait: false, close: false){
            INCLINIC_WINDOW = currentWindow
             title == "Patient Portal"
        }
    }

    def "At InClinicPage"() {
        given:
        driver.switchTo().window(INCLINIC_WINDOW)

        when: "Type in code in codeInput"
        at InClinicPage
//        PATIENT_ID1 = portalName.text()

//        assert PATIENT_ID1 == PATIENT_ID2

        codeInput.value('')
        Thread.sleep(1000)
        codeInput << CODE
        Thread.sleep(2000)

        and: "Click go button"
        goButton.click()

        then: "Direct to question page"
        waitFor(30,1){
            at InClinicTaskPage
        }
    }

    def "click to the question page" (){
        when: "click start button"
        taskStartButton.click()

        then: "go to the question page"
        at TaskIntroPage
        Thread.sleep(2000)
    }


}
