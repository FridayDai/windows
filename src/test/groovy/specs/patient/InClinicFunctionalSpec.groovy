package specs.patient

import pages.client.InClinicPage
import pages.client.InClinicTaskPage
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec

import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class InClinicFunctionalSpec extends RatchetFunctionalSpec {

    @Shared CODE

    def setupSpec(){
        browser.setBaseUrl(getClientUrl())
    }
    def "should login with the activate account created by client successfully"() {

        when:
        def loginPage = new LoginPage()
        to loginPage

        and:
        loginPage.login(account.email,account.password)

        and:
        loginPage.goToPatientsPage()

        then:
        waitFor(30, 1){
            browser.at PatientsPage
        }

    }

    def "direct to patient detail Page"(){
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        and:
        patientsPage.goToPatientDetailPage()

        then:
        waitFor(30, 1){
            browser.at PatientDetailPage
        }

    }


    def "generate code of schedule and Click to other page"() {
        when:
        def patientDetailPage = new PatientDetailPage()
        at patientDetailPage

        and:
        CODE = patientDetailPage.generateCode()

        and:
        def link = patientDetailPage.codeLink.text()

        and:
        go link;

        then:
        waitFor(30, 1) {
            browser.at InClinicPage
        }
    }

    def " At InClinicPage"() {
        when:
        def inClinicPage = new InClinicPage()
        at inClinicPage

        and:
        inClinicPage.typeCode(CODE)

        and:
        inClinicPage.goToInClinicTaskPage()

        then: "Direct to question page"
        waitFor(30,1){
            browser.at InClinicTaskPage
        }

    }

    def "click to the question page" (){
        when: "click start button"
        def inClinicTaskPage = new InClinicTaskPage()
        at inClinicTaskPage

        then:
        inClinicTaskPage.clickStartButton()
    }
}
