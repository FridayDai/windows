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
import pages.mail.GmailPasswordPage
import pages.mail.GmailSignInPage
import spock.lang.Shared
import spock.lang.Stepwise


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

    static ACTIVATE_EMAIL_TITLE = "Activate your Ratchet Health Account!"
    static CONFIRM_EMAIL_TITLE = "Please Confirm your Email Address"


    def setupSpec() {

        def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
        def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
        def db = client.getDB('ratchet-tests')
        IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value
        GMAIL_WINDOW = ''

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

    def "invite email should received"() {
        browser.setBaseUrl(getGmailUrl())

        when: "Go to gmail about page"
        to GmailAboutPage

        and: "Click sign in link at about page"
        signInLink.click()

        then: "At gmail sign in page"
        at GmailSignInPage

        when: "Type in email account and click next button"
        emailInput << GMAIL_ACCOUNT

        nextButton.click()

        then: "At gmail password page"
        at GmailPasswordPage

        when: "Type in email password and click sign in button"
        waitFor(10, 1) { passwordInput.displayed }

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
        searchInput << AGENT_FIRST_NAME

        and: "Click search button"
        searchButton.click()

        then: "Wait for Activate Ratchet Health Account line"
        waitFor(300, 5) {
            mailTable.find('td', text: contains(ACTIVATE_EMAIL_TITLE), 0).displayed
        }

        when: "Click activate ratchet health account line"
        mailTable.find('td', text: contains(ACTIVATE_EMAIL_TITLE), 0).click()

        waitFor(100, 5) {
            mailContent.find('a', href: contains(getClientDomain()), 0).displayed
        }

        GMAIL_WINDOW = currentWindow

        switchToNewWindow {
            mailContent.find('a', href: contains(getClientDomain()), 0).click()
        }

        then: "Direct to staff email confirmation page"

        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }
    }

//    @Ignore
    def "activate agent created by admin successfully"() {
        when: "Add new password and confirm password to StaffEmailConfirmation"
        at StaffEmailConfirmationPage

        and: "Wait for new password input appear"
        waitFor(10, 1) { newPassword.displayed }

        and: "Type in password and repeat password"
        newPassword << ACCOUTN_PASSWORD
        confirmPassword << ACCOUTN_PASSWORD

        and: "Click active button"
        activeButton.click()

        then: "Direct to login page"
        at LoginPage
    }

//    @Ignore
    def "should login with the activate agent created by admin successfully"() {
        browser.setBaseUrl(getClientUrl())

        when: "Login with agent account"
        at LoginPage

        and: "Wait for email input appear"
        waitFor(10, 1) { emailInput.displayed }

        and: "Type in email and password"
        emailInput << ACCOUNT_EMAIL
        passwordInput << ACCOUTN_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(15, 1) {
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
        when: "Direct to logout"
        at AccountsPage
        logoutLink.click()

        then: "Redirect to login page"
        at LoginPage
    }

    def "switch to first gmail window"() {
        when: "Switch gmail window"
        at LoginPage

        and: "Close current window - ratchet health client window"
        driver.close()//close the current window

        switchToWindow(GMAIL_WINDOW)

        then: "To gmail page"
        at GmailAppPage
    }

    /**
     * confirm the provider and login.
     */
//    @Ignore
    def "direct to provider email confirmation page successfully"() {
        when: "At gmail app page"
        at GmailAppPage

        and: "Wait inbox button to displayed"
        waitFor(10, 1) { inboxButton.displayed }

        and: "Click inbox button"
        inboxButton.click()

        and: "Type provider first name in search input"
        searchInput << PROVIDER_FIRST_NAME

        and: "Click search button"
        searchButton.click()

        then: "Wait for Activate Ratchet Health Account line"
        waitFor(300, 2) {
            mailTable.find('td', text: contains(PROVIDER_FIRST_NAME), 0).displayed
        }//email should be wait for a couple of minutes, because provider just been created several seconds ago.

        when: "Click activate ratchet health account line"
        mailTable.find('td', text: contains(PROVIDER_FIRST_NAME), 0).click()

        waitFor(20, 1) {
            mailContent.find('a', href: contains(getClientDomain()), 0).displayed
        }

        and: "Switch to another window"
        switchToNewWindow {
            mailContent.find('a', href: contains(getClientDomain()), 0).click()
        }


        then: "Direct to staff email confirmation page"
        waitFor(20, 1) {
            at StaffEmailConfirmationPage
        }
    }

//    @Ignore
    def "activate provider created by client successfully"() {
        when: "At staff email confirmation page"
        at StaffEmailConfirmationPage

        and: "Wait for new password input to displayed"
        waitFor(10, 1) { newPassword.displayed }

        and: "Type in new password and confirm password"
        newPassword << PROVIDER_PASSWORD
        confirmPassword << PROVIDER_PASSWORD

        and: "Click active button"
        activeButton.click()

        then: "Direct to login page"
        at LoginPage
    }

//    @Ignore
    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: "At login page"
        at LoginPage

        and: "Wait for email input to displayed"
        waitFor(10, 1) { emailInput.displayed }

        and: "Type in provider email and password"
        emailInput << PROVIDER_EMAIL
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
        waitFor(3, 1) { patientIdModel.displayed }

        and: "Type in patient id"
        patientIdModel.patientId << PATIENT_ID

        and: "Click patient id model create button"
        patientIdModel.createButton.click()

        and: "Wait for agent model disappear"
        waitFor(20, 1) { newPatientModel.displayed }

        and: "Type in patient basic information"
        newPatientModel.patientFirstName << PATIENT_FIRST_NAME
        newPatientModel.patientLastName << PATIENT_LAST_NAME
        newPatientModel.phoneNumber << PATIENT_PHONENUMBER
        newPatientModel.email << PATIENT_EMAIL

        and: "Type in care giver basic information"
        newPatientModel.caregiverFirstName << CAREGIVER_FIRST_NAME
        newPatientModel.caregiverLastName << CAREGIVER_LAST_NAME
        newPatientModel.caregiverEmail << CAREGIVER_EMAIL

        newPatientModel.relationshipSelect.jquery.focus()
        waitFor(10, 1) { relationshipFirstResult.displayed }
        relationshipFirstResult.click()

        newPatientModel.isPermission.value(true)

        and: "Choose group"
        newPatientModel.groupSelect.jquery.focus()
        waitFor { groupFirstResult.displayed }
        groupFirstResult.click()

        and: "Choose provider"
        newPatientModel.providerSelect.jquery.focus()
        waitFor { providerFirstResult.displayed }
        providerFirstResult.click()

        and: "Choose treatment"
        newPatientModel.treatmentSelect.jquery.focus()
        waitFor { treatmentFirstResult.displayed }
        treatmentFirstResult.click()

        and: "Choose surgery date"
        newPatientModel.surgeryDateSelect.click()
        waitFor(10, 1) { datepickerDate.displayed }
        datepickerDate.click()

        and: "Click new patient create button"
        newPatientModel.createButton.click()

        then: "Treatment should created and displayed on page"
        waitFor(10, 1) {
            at PatientDetailPage
        }
    }

//    	@Ignore
    def "logout successfully"() {
        when: "At patient detail page"
        at PatientDetailPage

        and: "Click logout link"
        logoutLink.click()

        then: "redirect to login page"
        at LoginPage
    }

//    @Ignore
    def "switch back to gmail window"() {
        when: "Switch gmail window"
        at LoginPage

        and: "Close current window - ratchet health client window"
        driver.close()//close the current window

        switchToWindow(GMAIL_WINDOW)

        then: "To gmail page"
        at GmailAppPage
    }
}
