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
//    @Shared PATIENT_EMAIL
//    @Shared IDENTIFY
//    @Shared PROVIDER_EMAIL
//    @Shared PROVIDER_PASSWORD


//    def setupSpec() {
//        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
//        PATIENT_EMAIL = "ratchet.testing+pat1${IDENTIFY}@gmail.com"
//        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
//        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
//    }

    def "should enter email successfully"() {
        when: "at enter email page"
        def enterEmailPage = new EnterEmailPage()
        at enterEmailPage

        then:
        enterEmailPage.inputEmail(patient.email)
    }

    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when:
        def loginPage = new LoginPage()
        to loginPage

        then:
        loginPage.login(account.email,account.password)

    }


}
