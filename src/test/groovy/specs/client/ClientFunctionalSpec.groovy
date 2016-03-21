package specs.client

//import jdk.nashorn.internal.ir.annotations.Ignore
import pages.client.*
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise
import utils.Utility
import spock.lang.Ignore


class ClientFunctionalSpec extends RatchetFunctionalSpec {


    //get confirm link by google api.

    def "check agent email received and click the link"() {
        given:
        def link
        waitFor(30,1) {
            (link = getConfirmLink(agent.firstName)).length() >= 1
        }

        when:
        go link;

        then:
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }

    }

    def "activate agent created by admin successfully"() {
        when:
        def confirmationPage = new StaffEmailConfirmationPage()
        at confirmationPage

        then:
        confirmationPage.setPassword(agent.password)
    }

    def "should login with the activate agent created by admin successfully"() {
        browser.setBaseUrl(getClientUrl())
        when:
        def loginPage = new LoginPage()
        at loginPage

        then:
        loginPage.login(agent.email,agent.password)
    }

    def "direct to groups page successfully"() {
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        then:
        patientsPage.goToGroupsPage(agent.email,agent.password)
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

    def "should logout with the activate account created by admin successfully"() {
        when:
        def accountProfilePage = new AccountProfilePage()
        at accountProfilePage

        then:
        accountProfilePage.logout()
    }


    def "check provider email received and click the link"() {
        given:
        def link

        (link = getConfirmLink(account.firstName)).length() >= 1

        when:
        go link;

        then:
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }

    }
//

    def "activate provider created by client successfully"() {
        when:
        def confirmationPage = new StaffEmailConfirmationPage()
        at confirmationPage

        then:
        confirmationPage.setPassword(account.password)

    }


//    doctor portal
    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when:
        def loginPage = new LoginPage()
        at loginPage

        and:
        //loginPage.login("875606747@qq.com","92623Daiyi")
        loginPage.login(account.email,account.password)

        then:
        loginPage.goToPatientsPage()
    }

    /**
     *  add patient and check confirm email.
     */

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

    def "logout successfully"() {
        when:
        def patientDetailPage = new PatientDetailPage()
        at patientDetailPage

        then:
        patientDetailPage.logout()
    }



//    def "archived agent email successfully"() {
//        when:
//        archivedQueryEmails(agent.firstName)
//
//        then:
//        getAllLinks("${agent.firstName} label:inbox").size() < 1
//    }
//
//    def "archived provider email successfully"() {
//        when:
//        archivedQueryEmails(account.firstName)
//
//        then:
//        getAllLinks("${account.firstName} label:inbox").size() < 1
//    }
}
