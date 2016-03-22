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

    def "should login with the activate account created by client successfully"() {

        when: 
        def loginPage = new LoginPage()
        to loginPage

        and:
        loginPage.login(account.email,account.password)

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