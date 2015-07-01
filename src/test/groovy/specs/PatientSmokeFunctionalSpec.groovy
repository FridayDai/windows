package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.mail.GmailAboutPage
import pages.mail.GmailAppPage
import pages.mail.GmailPasswordPage
import pages.mail.GmailRevisitPasswordPage
import pages.mail.GmailSignInPage
import pages.patient.EmailConfirmationPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class PatientSmokeFunctionalSpec extends RatchetSmokeFunctionalSpec {
    @Shared IDENTIFY
    @Shared GMAIL_WINDOW
    @Shared PATIENT_EMAIL
    @Shared PATIENT_FIRST_NAME
    @Shared PATIENT_LAST_NAME
    @Shared CAREGIVER_FIRST_NAME
    @Shared CAREGIVER_LAST_NAME
    @Shared PATIENT_DOMAIN
    @Shared SEARCH_INPUT

    static GMAIL_ACCOUNT = "ratchet.testing@gmail.com"
    static GMAIL_PASSWORD = "K6)VkqMUDy(mRseYHZ>v23zGt"
    static RAT_COM = "ratchethealth.com"
    static RAT_COM_IDENTIFY = "email/confirmation"
    static MAIL_COMPONENT = "/tasks/"
    static LAST_4_NUMBER = "7777"
    static CONFIRM_EMAIL_TITLE = "Confirm your Email Address"

    def setupSpec() {

        def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
        def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
        def db = client.getDB('ratchet-tests');

        IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value

        PATIENT_EMAIL = "ratchet.testing+pat${IDENTIFY}@gmail.com"
        PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
        PATIENT_LAST_NAME = "Patient"

        CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
        CAREGIVER_LAST_NAME = "Caregiver"

        GMAIL_WINDOW = ""
        PATIENT_DOMAIN = ""

        SEARCH_INPUT = "immediate " + PATIENT_FIRST_NAME

    }

//    def "login gmail successfully"() {
//        browser.setBaseUrl(getGmailUrl())
//
//        when: "Go to gmail about page"
//        to GmailAboutPage
//
//        and: "Click sign in link at about page"
//        waitFor { signInLink.click() }
//
//        then: "At gmail password page"
//        at GmailRevisitPasswordPage
//
//        when: "Type in email password and click sign in button"
//        waitFor(3, 1) { passwordInput.displayed }
//
//        passwordInput << GMAIL_PASSWORD
//
//        signInButton.click()
//
//        then: "Log into gmail successfully"
//        waitFor(30, 1) {
//            at GmailAppPage
//        }
//    }

//    @Ignore
    def "click confirm patient email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Type patient first name in search input and click search button"
        indexButton.click()
        waitFor(300, 3) {
            $("table").find("td", text: contains(PATIENT_FIRST_NAME)).size() >= 1
        }

        searchInput << PATIENT_FIRST_NAME
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).displayed
        }

        $("table").find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).click()


        waitFor(30, 1) {
            $('a', href: contains(RAT_COM_IDENTIFY)).displayed
        }

        def urlHref = $('a', href: contains(RAT_COM_IDENTIFY)).attr('href')

        PATIENT_DOMAIN = urlHref.substring(0, urlHref.indexOf(RAT_COM) + RAT_COM.length()) + "/"

        def confirmPatientDomain = PATIENT_DOMAIN + "patient"

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(confirmPatientDomain)).click()
        }

        then: "Direct to patient email confirmation page"
        waitFor(30, 1) {
            at EmailConfirmationPage
        }
    }

//    @Ignore
    def "switch form patient email confirmation back to gmail"() {
        when: "At EmailConfirmationPage"
        at EmailConfirmationPage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "confirm emergency contact which belongs to the patient"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Type caregiver first name in search input and click search button"
        indexButton.click()

        waitFor(300, 3) {
            $("table").find("td", text: contains(CAREGIVER_FIRST_NAME)).size() >= 1
        }

        searchInput << CAREGIVER_FIRST_NAME
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).displayed
        }

        $("table").find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).click()

        def confirmEmergencyContactDomain = PATIENT_DOMAIN + "emergency_contact"

        waitFor(30, 1) {
            $('a', href: contains(confirmEmergencyContactDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(confirmEmergencyContactDomain)).click()
        }

        then: "Direct to emergency contact email confirmation page"
        waitFor(30, 1) {
            at EmailConfirmationPage
        }

    }

    //    @Ignore
    def "switch from emergency contact page back to gmail"() {
        when: "At EmailConfirmationPage"
        at EmailConfirmationPage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click DASH immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Type immediate and patient first name in search input and click search button"
        indexButton.click()

        waitFor(300, 1) {
            $("table").find("td", text: contains(SEARCH_INPUT)).size() >= 6
        }

        searchInput.value("")
        searchInput << "immediate" + " " + PATIENT_FIRST_NAME
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("Disabilities of the Arm, Shoulder and Hand"), 0).displayed
        }

        $("table").find("td", text: contains("Disabilities of the Arm, Shoulder and Hand"), 0).click()

        def dashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "DASH"

        waitFor(30, 1) {
            $('a', href: contains(dashTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(dashTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check DASH phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to DASH task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete DASH immediate task"() {
        when: "At DASH task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[0].click()
        choicesList[6].click()
        choicesList[12].click()
        choicesList[18].click()
        choicesList[24].click()
        choicesList[28].click()
        choicesList[32].click()
        choicesList[36].click()
        choicesList[40].click()
        choicesList[46].click()
        choicesList[52].click()
        choicesList[58].click()
        choicesList[64].click()
        choicesList[68].click()
        choicesList[72].click()
        choicesList[76].click()
        choicesList[80].click()
        choicesList[86].click()
        choicesList[92].click()
        choicesList[98].click()
        choicesList[104].click()
        choicesList[108].click()
        choicesList[112].click()
        choicesList[116].click()
        choicesList[120].click()
        choicesList[126].click()
        choicesList[132].click()
        choicesList[138].click()
        choicesList[144].click()
        choicesList[148].click()


        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from dash complete page back to gmail"() {
        when: "At DashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check DASH immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click dash task link again"

        def dashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "DASH"

        waitFor(100, 1) {
            $('a', href: contains(dashTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(dashTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check dash complete page back to gmail"() {
        when: "At DashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click NDI immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click search button"
        indexButton.click()
        searchInput << "immediate" + " " + PATIENT_FIRST_NAME
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("Neck Disability Index"), 0).displayed
        }

        $("table").find("td", text: contains("Neck Disability Index"), 0).click()

        def ndiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NDI"

        waitFor(30, 1) {
            $('a', href: contains(ndiTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(ndiTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check NDI phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to ndi task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete NDI immediate task"() {
        when: "At NDI task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[0].click()
        choicesList[7].click()
        choicesList[14].click()
        choicesList[21].click()
        choicesList[28].click()
        choicesList[35].click()
        choicesList[36].click()
        choicesList[43].click()
        choicesList[50].click()
        choicesList[57].click()

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from ndi complete page back to gmail"() {
        when: "At NDICompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check NDI immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click ndi link again"
        def ndiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NDI"

        waitFor(30, 1) {
            $('a', href: contains(ndiTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(ndiTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check ndi complete page back to gmail"() {
        when: "At NDICompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click QuickDash immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click search button"
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("QuickDASH"), 0).displayed
        }

        $("table").find("td", text: contains("QuickDASH"), 0).click()

        def qucikDashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "QuickDASH"

        waitFor(100, 1) {
            $('a', href: contains(qucikDashTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(qucikDashTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check QuickDASH phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to QuickDASH task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete QuickDash immediate task"() {
        when: "At QuickDash task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[0].click()
        choicesList[6].click()
        choicesList[12].click()
        choicesList[18].click()
        choicesList[24].click()
        choicesList[28].click()
        choicesList[32].click()
        choicesList[36].click()
        choicesList[40].click()
        choicesList[46].click()
        choicesList[52].click()

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from quickDash complete page back to gmail"() {
        when: "At QuickDashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check QuickDash immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click qucikDash link again"
        def qucikDashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "QuickDASH"

        waitFor(100, 1) {
            $('a', href: contains(qucikDashTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(qucikDashTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check quickDash complete page back to gmail"() {
        when: "At QuickDashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click NRS-BACK immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click search button"
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("(NRS) for Back Pain"), 0).displayed
        }

        $("table").find("td", text: contains("(NRS) for Back Pain"), 0).click()

        def nrsBackTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-BACK"

        waitFor(100, 1) {
            $('a', href: contains(nrsBackTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(nrsBackTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check NRS-BACK phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to NRS-BACK task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete NRS-BACK immediate task"() {
        when: "At NRS-BACK task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[5].click()
        choicesList[16].click()

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from nrs back complete page back to gmail"() {
        when: "At NRSBackhCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check NRS-BACK immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click nrs back link again"
        def nrsBackTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-BACK"

        waitFor(100, 1) {
            $('a', href: contains(nrsBackTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(nrsBackTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check nrs back complete page back to gmail"() {
        when: "At NRSBackhCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click NRS-NECK immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click search button"
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("(NRS) for Neck Pain"), 0).displayed
        }

        $("table").find("td", text: contains("(NRS) for Neck Pain"), 0).click()

        def nrsNeckTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-NECK"

        waitFor(100, 1) {
            $('a', href: contains(nrsNeckTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(nrsNeckTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check NRS-NECK phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to NRS-NECK task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete NRS-NECK immediate task"() {
        when: "At NRS-NECK task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[5].click()
        choicesList[16].click()

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from nrs neck complete page back to gmail"() {
        when: "At NRSNeckCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check NRS-NECK immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click nrs neck link again"
        def nrsNeckTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-NECK"

        waitFor(100, 1) {
            $('a', href: contains(nrsNeckTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(nrsNeckTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check nrs neck complete page back to gmail"() {
        when: "At NRSNeckCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "click ODI immediate task email successfully"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click search button"
        searchButton.click()

        waitFor(30, 1) {
            $("table").find("td", text: contains("Oswestry Disability Index"), 0).displayed
        }

        $("table").find("td", text: contains("Oswestry Disability Index "), 0).click()

        def odiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "ODI"

        waitFor(100, 1) {
            $('a', href: contains(odiTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(odiTaskDomain)).click()
        }

        then: "Direct to phone number check page"
        waitFor(30, 1) {
            at PhoneNumberCheckPage
        }
    }

//    @Ignore
    def "check ODI phone number successfully"() {
        when: "At phone number check page"
        at PhoneNumberCheckPage

        and: "Type last 4 number and start to complete tasks"
        phoneNumberInput << LAST_4_NUMBER
        startButton.click()

        then: "Direct to ODI task page"
        waitFor(30, 1) {
            at TaskIntroPage
        }
    }

//    @Ignore
    def "complete ODI immediate task"() {
        when: "At ODI task page"
        at TaskIntroPage

        and: "Complete tasks"
        choicesList[0].click()
        choicesList[7].click()
        choicesList[14].click()
        choicesList[21].click()
        choicesList[28].click()
        choicesList[35].click()
        choicesList[36].click()
        choicesList[43].click()
        choicesList[50].click()
        choicesList[57].click()

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from odi complete page back to gmail"() {
        when: "At odiCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check ODI immediate task email link successfully after completing task"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Click odi link again"
        def odiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "ODI"

        waitFor(100, 1) {
            $('a', href: contains(odiTaskDomain)).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(odiTaskDomain)).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }
}
