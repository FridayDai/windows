package specs.client

import jdk.nashorn.internal.ir.annotations.Ignore
import pages.client.*
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise
import utils.ModelHelper
import utils.Utility

@Stepwise
class ClientFunctionalSpec extends RatchetFunctionalSpec {


    //get confirm link by google api.
    @Ignore
    def "check agent email received and click the link"() {
        given:
        def link

        (link = getConfirmLink(agent.firstName)).length() >= 1


        when:
        go link;

        then:
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }

    }
    @Ignore
    def "activate agent created by admin successfully"() {
        when:
        def confirmationPage = new StaffEmailConfirmationPage()
        at confirmationPage

       // def agent = ModelHelper.getAgent()

        then:
        confirmationPage.setPassword(agent.password)
    }

    def "should login with the activate agent created by admin successfully"() {
        when:
        def loginPage = new LoginPage()
        to loginPage

//        and:
//        loginPage.checkAutoSetEmail(agent.email)

        then:
        loginPage.login(agent.email,agent.password)
    }

    def "direct to groups page successfully"() {
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        then:
        patientsPage.goToGroupsPage()
    }


    def "add new group successfully"() {
        when:
        def groupsPage = new GroupsPage()
        at groupsPage

        then:
        groupsPage.addGroup(group)
    }

    def "direct to accounts page"() {
        when:
        def groupsPage = new GroupsPage()
        at groupsPage

        then:
        groupsPage.goToAccountsPage()
    }

//    @Ignore
    def "add account successfully"() {
        when:
        def accountsPage = new AccountsPage()
        at accountsPage

        and:
        accountsPage.addNewAccount(account)

        then:
        accountsPage.goToAccountDetailPage()
    }

    def "check account detail"() {
        when:
        def accountDetailPage = new AccountDetailPage()
        at accountDetailPage

        then:
        accountDetailPage.checkDetail(account,group)
    }

    def "direct to account profile"() {
        when:
        def accountDetailPage = new AccountDetailPage()
        at accountDetailPage

        then:
        accountDetailPage.goToProfilePage()
    }

    def "check agent profile detail"(){
        when:
        def accountProfilePage = new AccountProfilePage()
        at accountProfilePage

        then:
        accountProfilePage.checkProfileDetail(agent)
    }

//    @Ignore
    def "should logout with the activate account created by admin successfully"() {
        when: 
        def accountProfilePage = new AccountProfilePage()
        at accountProfilePage
        
        then: 
        accountProfilePage.logout()
    }

//    @Ignore
//    def "check provider email received and click the link"() {
//        given:
//        def link
//
//        waitFor(500, 1) {
//            (link = getConfirmLink(account.firstName)).length() >= 1
//        }
//
//        when:
//        go link;
//
//        then:
//        waitFor(10, 1) {
//            at StaffEmailConfirmationPage
//        }
//
//    }
//
//    @Ignore
//    def "activate provider created by client successfully"() {
//        when: "At staff email confirmation page"
//
//        at StaffEmailConfirmationPage
//
//        and: "Wait for new password input to displayed"
//        waitFor(30, 1) { newPassword.displayed }
//
//        and: "Type in new password and confirm password"
//        newPassword << account.password
//        confirmPassword << account.password
//
//        and: "Click active button"
//        activeButton.click()
//
//        then: "Direct to login page"
//        waitFor(20, 1) {
//            at LoginPage
//        }
//    }

//    @Ignore
//    doctor portal
    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: 
        def loginPage = new LoginPage()
        at loginPage

        and:
        loginPage.login("875606747@qq.com","92623Daiyi")
        //loginPage.login(account.email,account.password)

        then:
        loginPage.goToPatientsPage()
    }

    /**
     *  add patient and check confirm email.
     */

//    @Ignore
    def "add patient successfully and go to patient detail page"() {
        when: 
        def patientsPage = new PatientsPage()
        at patientsPage

        then:
        patientsPage.addNewPatient(patient)
    }

    def "check patient detail info and task"() {
        when: "Stay in patient details page"
        def patientDetailPage = new PatientDetailPage()
        at patientDetailPage

        then: 
        patientDetailPage.checkPatientDetail(patient) 
    }

//    	@Ignore
    def "logout successfully"() {
        when:
        def patientDetailPage = new PatientDetailPage()
        at patientDetailPage

        then:
        patientDetailPage.logout()
    }



    def "archived agent email successfully"() {
        when:
        archivedQueryEmails(agent.firstName)

        then:
        getAllLinks("${agent.firstName} label:inbox").size() < 1
    }

    def "archived provider email successfully"() {
        when:
        archivedQueryEmails(account.firstName)

        then:
        getAllLinks("${account.firstName} label:inbox").size() < 1
    }
}
