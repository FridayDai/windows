package specs.client

import pages.client.*
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise
import groovy.json.JsonSlurper

@Stepwise
class ClientFunctionalSpec extends RatchetFunctionalSpec {
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

    static ACTIVATE_EMAIL_TITLE = "Activate your Ratchet Health Account!"
    static CONFIRM_EMAIL_TITLE = "Please Confirm your Email Address"


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
        PATIENT_ID = "${IDENTIFY}"
        PATIENT_PHONENUMBER = "6265557777"
        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
        PATIENT_LAST_NAME = "Patient"

        CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
        CAREGIVER_LAST_NAME = "Caregiver"
        CAREGIVER_EMAIL = "ratchet.testing+car${IDENTIFY}@gmail.com"
    }

    //get confirm link by google api.
    def "check agent email received and click the link"() {
        given:
        def link
        waitFor(500, 1) {
            (link = getConfirmLink(AGENT_FIRST_NAME)).length() >= 1
        }

        when:
        go link;

        then:
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }

    }

//    @Ignore
    def "activate agent created by admin successfully"() {
        when: "Add new password and confirm password to StaffEmailConfirmation"
        at StaffEmailConfirmationPage

        and: "Wait for new password input appear"
        waitFor(30, 1) { newPassword.displayed }

        and: "Type in password and repeat password"
        newPassword << ACCOUTN_PASSWORD
        confirmPassword << ACCOUTN_PASSWORD

        and: "Click active button"
        activeButton.click()

        then: "Direct to login page"
        waitFor(30, 1) {
            at LoginPage
        }

    }

//    @Ignore
    def "should login with the activate agent created by admin successfully"() {
        browser.setBaseUrl(getClientUrl())

        when: "Login with agent account"
        at LoginPage

        and: "Wait for email input appear"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in email and password"
//        emailInput << ACCOUNT_EMAIL
        passwordInput << ACCOUTN_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }
    }

    /**
     * create group for provider
     */
    def "direct to groups page successfully"() {
        when: "Click link to group page"
        at PatientsPage

        and: "Click group tab in navigation panel"
        groupTab.click()

        then: "Direct to accounts page"
        waitFor(30, 1) {
            at GroupsPage
        }

        Thread.sleep(3 * 1000)
    }

    def "add new group successfully"() {
        when: "Click new group link"
        at GroupsPage

        waitFor(30, 1) {
            newGroupButton.displayed
        }

        newGroupButton.click()

        and: "Wait for treatment model come up"
        waitFor(5, 1) { groupModelModule.displayed }
        groupModelModule.groupName << GROUP_NAME

        and: "Click create button"
        groupModelModule.createButton.click()

        then: "Check add group successfully"
        waitFor(20, 1) {
            $("tbody tr", 0).find("td", 1).text() == GROUP_NAME
        }
    }

    /**
     * create new provider and agent logout.
     */

//    @Ignore
    def "direct to accounts page"() {
        when: "Direct to account page"
        at GroupsPage
        accountTab.click()

        then: "Direct to accounts page"
        waitFor(30, 1) {
            at AccountsPage
        }
    }

//    @Ignore
    def "add account successfully"() {
        when: "has new account button"
        to AccountsPage

        and: "Click add account button"
        waitFor(30, 1) {
            newAccountButton.displayed
        }
        newAccountButton.click()

        and: "Wait for account model come up"
        waitFor(30, 1) { accountModelModule.displayed }
        Thread.sleep((3 * 1000) as long)

        and: "select doctor, type firstName lastName and email address, select provider and choose group"
        accountModelModule.isDoctor.value(true)
        accountModelModule.accountFirstName << PROVIDER_FIRST_NAME
        accountModelModule.accountLastName << PROVIDER_LAST_NAME
        accountModelModule.email << PROVIDER_EMAIL
        accountModelModule.isProvider.value(true)
        accountModelModule.npi << IDENTIFY.toString().take(10)
        accountModelModule.groupSelect.click()

        waitFor(20, 1) { accountModelModule.groupFirstResult.displayed }

        accountModelModule.groupFirstResult.click()

        and: "Click create button"
        accountModelModule.createButton.click()
        //you can also check the model disappear.

        then: "Account should created and displayed on page"
        waitFor(50, 1) {
            firstLine.find("td", 1).text().trim() == PROVIDER_FIRST_NAME + " " + PROVIDER_LAST_NAME
            firstLine.find("td", 2).text() == PROVIDER_EMAIL
        }
    }

    def "click to account details page"() {

        when: "Click first line of table"
        firstLine.click()

        then: "Direct to account detail page"
        waitFor(15, 1) {
            at AccountDetailPage
        }

        and: "Check the details"
        accountFirstName.text() == PROVIDER_FIRST_NAME
        accountLastName.text() == PROVIDER_LAST_NAME
        accountEmail.text() == PROVIDER_EMAIL
        accountStatus.text().trim() == "UNVERIFIED"
        provider.text() == "Yes"
        groups.text().trim() == GROUP_NAME
    }

    def "direct to account profile"() {

        when: "Click to account profile page"
        profileButton.click()

        then: "Direct to profile page"
        waitFor(15, 1) {
            at AccountProfilePage
        }

        and: "Check the details"
        accountFirstName.text() == AGENT_FIRST_NAME
        accountLastName.text() == AGENT_LAST_NAME
        accountEmail.text() == ACCOUNT_EMAIL
        accountStatus.text().trim() == "VERIFIED"
        provider.text() == "No"
    }

//    @Ignore
    def "should logout with the activate account created by admin successfully"() {
        when: "Direct to logout"
        at AccountProfilePage
        logoutLink.click()

        then: "Redirect to login page"
        waitFor(10, 1) {
            at LoginPage
        }
    }

    //    @Ignore
    def "check provider email received and click the link"() {
        given:
        def link
        waitFor(500, 1) {
            (link = getConfirmLink(PROVIDER_FIRST_NAME)).length() >= 1
        }

        when:
        go link;

        then:
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }

    }

//    @Ignore
    def "activate provider created by client successfully"() {
        when: "At staff email confirmation page"
        at StaffEmailConfirmationPage

        and: "Wait for new password input to displayed"
        waitFor(30, 1) { newPassword.displayed }

        and: "Type in new password and confirm password"
        newPassword << PROVIDER_PASSWORD
        confirmPassword << PROVIDER_PASSWORD

        and: "Click active button"
        activeButton.click()

        then: "Direct to login page"
        waitFor(10, 1) {
            at LoginPage
        }
    }

//    @Ignore
    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: "At login page"
        at LoginPage

        and: "Wait for email input to displayed"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in provider email and password"
//        emailInput << PROVIDER_EMAIL
        passwordInput << PROVIDER_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }
    }

    /**
     *  add patient and check confirm email.
     */

//    @Ignore
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

    def "check patient detail info and task"() {
        when: "Stay in patient details page"
        at PatientDetailPage

        then: "Check patient info"
        firstName.value() == PATIENT_FIRST_NAME
        lastName.value() == PATIENT_LAST_NAME
        patientId.text() == PATIENT_ID
        email.text().trim() == PATIENT_EMAIL
        phone.text().replaceAll("[^0-9]", "") == PATIENT_PHONENUMBER

        and: "Check pending task in sent items"
        waitFor(60, 1) {
            sentNoItem.displayed
        }
        and: "Check schedule task in schedule items"
        waitFor(60, 1) {
            pendingTask.size() >= 9
        }
    }

//    	@Ignore
    def "logout successfully"() {
        when: "At patient detail page"
        at PatientDetailPage

        and: "Click logout link"
        logoutLink.click()

        then: "redirect to login page"
        waitFor(10, 1) {
            at LoginPage
        }

    }

    def "archived agent email successfully"() {
        when:
        archivedQueryEmails(AGENT_FIRST_NAME)

        then:
        getAllLinks("${AGENT_FIRST_NAME} label:inbox").size() < 1

    }

    def "archived provider email successfully"() {
        when:
        archivedQueryEmails(PROVIDER_FIRST_NAME)

        then:
        getAllLinks("${PROVIDER_FIRST_NAME} label:inbox").size() < 1
    }
}
