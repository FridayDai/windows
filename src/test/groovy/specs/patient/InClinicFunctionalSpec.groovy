package specs.patient

import groovy.json.JsonSlurper
import org.openqa.selenium.Keys
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
//    @Shared IDENTIFY
//    @Shared PROVIDER_EMAIL
//    @Shared PROVIDER_PASSWORD
//    @Shared PATIENT_FIRST_NAME
    @Shared WINDOW
    @Shared CODE

    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when:
        def loginPage = new LoginPage()
        to loginPage

        and:
        loginPage.login(account.email,account.password)

        then:
        loginPage.goToPatientsPage()
    }

    def "direct to patient detail Page"(){
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        then:
        patientsPage.goToPatientDetailPage()

    }


    def "generate code of schedule and Click to other page"() {
        when:
        def patientDetailPage = new PatientDetailPage()
        at patientDetailPage

        and:
        patientDetailPage.generateCode()

        then:
        patientDetailPage.clickURL()

    }

    def " At InClinicPage"() {
        given:
        driver.switchTo().window(WINDOW)

        when:
        def inClinicPage = new InClinicPage()
        at inClinicPage

        and:
        inClinicPage.typeCode(CODE)
        Thread.sleep(2000)

        then:
        inClinicPage.goToInClinicTaskPage()

    }

    def "click to the question page" (){
        when: "click start button"
        def inClinicTaskPage = new InClinicTaskPage()
        at inClinicTaskPage

        then:
        inClinicTaskPage.clickStartButton()
    }
}
