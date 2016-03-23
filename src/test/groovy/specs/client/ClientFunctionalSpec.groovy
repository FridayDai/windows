package specs.client

import pages.client.*
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise


@Stepwise
class ClientFunctionalSpec extends RatchetFunctionalSpec {
    def setupSpec() {
        browser.setBaseUrl(getClientUrl())
    }

    // Get confirm link by google api.

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

        and:
        confirmationPage.setPassword(agent.password)

        then:
        waitFor(30, 1) {
            browser.at LoginPage
        }
    }

    def "should login with the activate agent created by admin successfully"() {
        when:
        def loginPage = new LoginPage()
        at loginPage

        and:
        loginPage.login(agent.email, agent.password)

        then:
        waitFor(30, 1) {
            browser.at PatientsPage
        }
    }

    def "direct to groups page successfully"() {
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        and:
        patientsPage.goToGroupsPage()

        then:
        waitFor(30, 1) {
            browser.at GroupsPage
        }
    }


    def "add new group successfully"() {
        when:
        def groupsPage = new GroupsPage()
        at groupsPage

        and:
        groupsPage.addGroup(group)

        then: "Check add group successfully"
        waitFor(20, 1) {
            groupsPage.groupsTable.groupItems[0].groupName == group.groupName
        }
    }

    def "direct to accounts page"() {
        when:
        def groupsPage = new GroupsPage()
        at groupsPage

        and:
        groupsPage.goToAccountsPage()

        then:
        waitFor (30,1){
            browser.at AccountsPage
        }
    }

    def "add account successfully"() {
        when:
        def accountsPage = new AccountsPage()
        at accountsPage

        and:
        accountsPage.addNewAccount(account)

        and:
        accountsPage.goToAccountDetailPage()

        then:
        waitFor(30, 1) {
            browser.at AccountDetailPage
        }
    }

    def "check account detail"() {
        when:
        def accountDetailPage = new AccountDetailPage()
        at accountDetailPage

        then:
        accountDetailPage.checkDetail(account, group)
    }

    def "direct to account profile"() {
        when:
        def accountDetailPage = new AccountDetailPage()
        at accountDetailPage

        and:
        accountDetailPage.goToProfilePage()

        then:
        waitFor(30, 1) {
            browser.at AccountProfilePage
        }
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

    def "activate provider created by client successfully"() {
        when:
        def confirmationPage = new StaffEmailConfirmationPage()
        at confirmationPage

        and:
        confirmationPage.setPassword(account.password)

        then:
        waitFor(30, 1){
            browser.at LoginPage
        }

    }

    def "should login with the activate account created by client successfully"() {
        when:
        def loginPage = new LoginPage()
        at loginPage

        and:
        loginPage.login(account.email, account.password)

        and:
        loginPage.goToPatientsPage()

        then:
        waitFor(30,1){
            browser.at PatientsPage
        }
    }

    /**
     *  add patient and check confirm email.
     */

    def "add patient successfully and go to patient detail page"() {
        when:
        def patientsPage = new PatientsPage()
        at patientsPage

        and:
        patientsPage.addNewPatient(patient)

        then:
        waitFor(30, 1) {
            browser.at PatientDetailPage
        }
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
