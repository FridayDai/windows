package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.client.AccountsPage
import pages.client.GroupsPage
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.client.StaffEmailConfirmationPage
import pages.mail.GmailAboutPage
import pages.mail.GmailAppPage
import pages.mail.GmailRevisitPasswordPage
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

import java.security.acl.Group

@Stepwise
class ClientSmokeFunctionalSpec extends RatchetSmokeFunctionalSpec {
    @Shared IDENTIFY
    @Shared GMAIL_WINDOW
    @Shared AGENT_FIRST_NAME
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

    static GMAIL_ACCOUNT = "ratchet.testing@gmail.com"
    static GMAIL_PASSWORD = "K6)VkqMUDy(mRseYHZ>v23zGt"
    static ACTIVATE_EMAIL_TITLE = "Activate your Ratchet Health Account!"
    static CONFIRM_EMAIL_TITLE = "Please Confirm your Email Address"


    def setupSpec() {

        def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
        def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
        def db = client.getDB('ratchet-tests')
        IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value
        GMAIL_WINDOW = ''

        //**temporary********************
//        IDENTIFY = new Date().getTime()
//        ACCOUNT_EMAIL = "edith.sun+r1@xplusz.com"
//        ACCOUTN_PASSWORD = "test123"
//        PROVIDER_EMAIL = "edith.sun+r1@xplusz.com"
//        PROVIDER_PASSWORD = "test123"
        //**temporary******************

        AGENT_FIRST_NAME = "FN+ast${IDENTIFY}"

        GROUP_NAME = "group${IDENTIFY}"

        ACCOUNT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
        ACCOUTN_PASSWORD = "K(mRseYHZ>v23zGt23409"

        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
        PROVIDER_FIRST_NAME = "FN+pro${IDENTIFY}"
        PROVIDER_LAST_NAME = "Provider"

        PATIENT_EMAIL = "ratchet.testing+pat${IDENTIFY}@gmail.com"
        PATIENT_ID = "${IDENTIFY}"
        PATIENT_PHONENUMBER = "7777777777"
        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
        PATIENT_LAST_NAME = "Patient"

        CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
        CAREGIVER_LAST_NAME = "Caregiver"
        CAREGIVER_EMAIL = "ratchet.testing+car${IDENTIFY}@gmail.com"
    }

    /**
     * go to Gmail and login.
     */
//    @Ignore
    def "go to Gmail and login successfully"() {
        browser.setBaseUrl(getGmailUrl())

        when: "Go to gmail about page"
        to GmailAboutPage

        and: "Click sign in link at about page"
        waitFor { signInLink.click() }

        then: "At gmail password page"
        at GmailRevisitPasswordPage

        when: "Type in email password and click sign in button"
        waitFor(3, 1) { passwordInput.displayed }

        passwordInput << GMAIL_PASSWORD

        signInButton.click()

        then: "Log into gmail successfully"
        waitFor(30, 1) {
            at GmailAppPage
        }
    }

    /**
     * confirm the agent that created by admin portal and login.
     * @return
     */

//    @Ignore
    def "direct to admin agent email confirmation page successfully"() {

        when: "Type agent first name in search input"
        waitFor(20, 1) {
            at GmailAppPage
        }
        searchInput << AGENT_FIRST_NAME

        and: "Click search button"
        searchButton.click()

        then: "Wait for Activate Ratchet Health Account line"
        waitFor(30, 1) {
            $('td', text: contains(ACTIVATE_EMAIL_TITLE)).size() >= 1
        }

        when: "Click activate ratchet health account line"
        $('td', text: contains(ACTIVATE_EMAIL_TITLE)).click()

        waitFor(20, 1) {
            $('a', href: contains(getClientDomain())).displayed
        }
        GMAIL_WINDOW = currentWindow

        switchToNewWindow {
            $('a', href: contains(getClientDomain())).click()
        }

        then: "Direct to staff email confirmation page"

        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }
    }

//    @Ignore
    def "activate agent created by admin successfully"() {
        when: "add new password and confirm password to StaffEmailConfirmation"
        at StaffEmailConfirmationPage

        newPassword << ACCOUTN_PASSWORD
        confirmPassword << ACCOUTN_PASSWORD

        activeButton.click()

        then: "Direct to login page"
        at LoginPage

    }

//    @Ignore
    def "should login with the activate agent created by admin successfully"() {
        browser.setBaseUrl(getClientUrl())

        when: "login with agent account"
        to LoginPage

        emailInput << ACCOUNT_EMAIL
        passwordInput << ACCOUTN_PASSWORD

        loginButton.click()

        then: "direct to patients page"
        waitFor(15, 1) {
            at PatientsPage
        }
    }

    /**
     * create group for provider
     */
    def "direct to group"() {
        when: "click link to group page"
        at PatientsPage
        groupTab.click()

        then: "direct to accounts page"
        waitFor(30, 1) {
            at GroupsPage
        }
    }

    def "add a group successfully"() {
        when: "click new group link"
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

        then: "check add group successfully"
        waitFor(20, 1) {
            $("tbody tr", 0).find("td", 1).text() == GROUP_NAME
        }
    }

    /**
     * create new provider and agent logout.
     */

//    @Ignore
    def "direct to accounts page"() {
        when: "direct to account page"
        at GroupsPage
        accountTab.click()

        then: "direct to accounts page"
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
        waitFor(3, 1) { accountModelModule.displayed }

        and: "select doctor, type firstName lastName and email address, select provider and choose group"
        accountModelModule.accountFirstName << PROVIDER_FIRST_NAME
        accountModelModule.accountLastName << PROVIDER_LAST_NAME
        accountModelModule.email << PROVIDER_EMAIL
        accountModelModule.isDoctor.value(true)
        accountModelModule.isProvider.value(true)
        accountModelModule.groupSelect.click()

        waitFor(20, 1) { accountModelModule.groupFirstResult.displayed }

        accountModelModule.groupFirstResult.jquery.trigger('mouseup')

        and: "Click create button"
        accountModelModule.createButton.click()
        //you can also check the model disappear.

        then: "Account should created and displayed on page"
        waitFor(50, 1) {
            $("tbody tr", 0).find("td", 1).text().trim() == PROVIDER_FIRST_NAME + " " + PROVIDER_LAST_NAME
            $("tbody tr", 0).find("td", 2).text() == PROVIDER_EMAIL
        }

    }

//    @Ignore
    def "should logout with the activate account created by admin successfully"() {
        when: "direct to logout"
        at AccountsPage
        logoutLink.click()

        then: "redirect to login page"
        at LoginPage
    }

    def "switch to first gmail window"() {
        when: "switch gmail window"
        at LoginPage
        driver.close()//close the current window

        switchToWindow(GMAIL_WINDOW)
        then: "to gmail page"
        at GmailAppPage

    }

    /**
     * confirm the provider and login.
     */
//    @Ignore
    def "direct to  provider email confirmation page successfully"() {
        when: "Type provider first name in search input"
        at GmailAppPage
//        Thread.sleep(50000)

//        searchInput.value("")
//        searchInput << PROVIDER_FIRST_NAME
//        searchButton.click()

        indexButton.click()
        then: "Wait for Activate Ratchet Health Account line"
        waitFor(300, 3) {
            $('td', text: contains(PROVIDER_FIRST_NAME)).size() >= 1
        }

        when: "Click activate ratchet health account line"
        $('td', text: contains(PROVIDER_FIRST_NAME)).click()

        waitFor(20, 1) {
            $('a', href: contains(getClientDomain())).displayed
        }

        and: "switch to another window"
        switchToNewWindow {
            $('a', href: contains(getClientDomain())).click()
        }


        then: "Direct to staff email confirmation page"
        waitFor(20, 1) {
            at StaffEmailConfirmationPage
        }
    }

//    @Ignore
    def "activate provider created by client successfully"() {
        when: "add new password and confirm password to StaffEmailConfirmation"
        at StaffEmailConfirmationPage

        newPassword << PROVIDER_PASSWORD
        confirmPassword << PROVIDER_PASSWORD

        activeButton.click()

        then: "Direct to login page"
        at LoginPage

    }

//    @Ignore
    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: "login with agent account"
        to LoginPage

        emailInput << PROVIDER_EMAIL
        passwordInput << PROVIDER_PASSWORD

        loginButton.click()

        then: "direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }

    }

    /**
     *  add patient and check confirm email.
     */

//    @Ignore
    def "add patient successfully"() {
        when: "add a patient"
        to PatientsPage

        and: "Click add patient button"
        waitFor(30, 1) {
            addPatientButton.displayed
        }
        addPatientButton.click()

        and: "Wait for treatment model come up"
        waitFor(3, 1) { patientIdModel.displayed }
        patientIdModel.patientId << PATIENT_ID

        and: "Click create button"
        patientIdModel.createButton.click()

        and: "Wait for agent model disappear"
        waitFor(20, 1) { newPatientModel.displayed }

        and: "select doctor, type firstName lastName and email address, select provider and choose group"
        newPatientModel.patientFirstName << PATIENT_FIRST_NAME
        newPatientModel.patientLastName << PATIENT_LAST_NAME
        newPatientModel.phoneNumber << PATIENT_PHONENUMBER
        newPatientModel.email << PATIENT_EMAIL
        newPatientModel.caregiverFirstName << CAREGIVER_FIRST_NAME
        newPatientModel.caregiverLastName << CAREGIVER_LAST_NAME
        newPatientModel.caregiverEmail << CAREGIVER_EMAIL

        newPatientModel.relationshipSelect.jquery.focus()
        waitFor(10, 1) { relationshipFirstResult.displayed }
        relationshipFirstResult.click()

        newPatientModel.isPermission.value(true)

        newPatientModel.groupSelect.jquery.focus()
        waitFor { groupFirstResult.displayed }
        groupFirstResult.click()

        newPatientModel.providerSelect.jquery.focus()
        waitFor { providerFirstResult.displayed }
        providerFirstResult.click()


        newPatientModel.treatmentSelect.jquery.focus()
        waitFor { treatmentFirstResult.displayed }
        treatmentFirstResult.click()

        newPatientModel.surgeryDateSelect.click()
        waitFor(10, 1) { datepickerDate.displayed }
        datepickerDate.click()

        and: "Click create button"
        newPatientModel.createButton.click()

        then: "Treatment should created and displayed on page"
        waitFor(10, 1) {
            at PatientDetailPage
        }

    }

//    	@Ignore
    def "logout successfully"() {
        when: "At treatment detail page"
        at PatientDetailPage
        logoutLink.click()

        then: "redirect to login page"
        at LoginPage
    }

//    @Ignore
    def "switch to gmail window"() {
        when: "switch gmail window"
        switchToWindow(GMAIL_WINDOW)

        then: "to gmail page"
        at GmailAppPage

    }

//    @Ignore
    def "check patient and caregiver confirm email received"() {
        when: "Type patient first name in search input"
        at GmailAppPage
        Thread.sleep(50000)

        searchInput.value("")
        searchInput << PATIENT_FIRST_NAME
        searchButton.click()

        then: "Wait for Activate Ratchet Health Account line"
        waitFor(30, 1) {
            $('td', text: contains(CONFIRM_EMAIL_TITLE)).size() >= 1
        }

        when: "Click activate ratchet health account line"
        $('td', text: contains(CONFIRM_EMAIL_TITLE)).click()

        then: "Got activate email"
        waitFor(300, 1) {
            $('td', text: contains(PATIENT_FIRST_NAME)).size() >= 1
        }

        when: "Type caregiver first name in search input"
        at GmailAppPage
        searchInput.value("")
        searchInput << CAREGIVER_FIRST_NAME

        and: "Click search button"
        searchButton.click()

        then: "Wait for Activate Ratchet Health Account line"
        waitFor(30, 1) {
            $('td', text: contains(CONFIRM_EMAIL_TITLE)).size() >= 1
        }

        when: "Click activate ratchet health account line"
        $('td', text: contains(CONFIRM_EMAIL_TITLE)).click()

        then: "Got activate email"
        waitFor(300, 1) {
            $('td', text: contains(CAREGIVER_FIRST_NAME)).size() >= 1
        }
    }

}
