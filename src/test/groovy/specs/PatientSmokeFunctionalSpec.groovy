package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.mail.GmailAboutPage
import pages.mail.GmailAppPage
import pages.mail.GmailRevisitPasswordPage
import pages.patient.EmailConfirmationPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
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

    static RAT_COM = "ratchethealth.com"
    static RAT_COM_IDENTIFY = "email/confirmation"
    static RAT_COM_PATIENT_IDENTIFY = "ratchethealth.com/patient"
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

    def "login gmail successfully"() {
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

//    @Ignore
    def "should receive confirm patient email successfully and click email to confirm patient"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Wait inbox button to displayed and click inbox button"
        waitFor(10, 1) { gmail_inboxButton().displayed }
        gmail_inboxButton().click()

        and:"Wait confirm patient email to displayed"
        Thread.sleep(2000 as long)
        repeatActionWaitFor(300, 5, {
            gmail_refreshButton().click()
        }, {
            gmail_mailTable().find("td", text: contains(PATIENT_FIRST_NAME), 0).displayed
        })


        and: "Type patient first name in search input and click search button"
        searchInput << "${PATIENT_FIRST_NAME} ${RAT_COM_PATIENT_IDENTIFY}"
        searchButton.click()

        repeatActionWaitFor(300, 5, {
            gmail_refreshButton().click()
        }, {
            gmail_mailTable().find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).displayed
        })

        gmail_mailTable().find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).click()

        and:"Wait patient domain to displayed and get the patient domain"
        waitFor(300, 5) {
            $('a', href: contains(RAT_COM_IDENTIFY), 0).displayed
        }

        def urlHref = $('a', href: contains(RAT_COM_IDENTIFY), 0).attr('href')

        PATIENT_DOMAIN = urlHref.substring(0, urlHref.indexOf(RAT_COM) + RAT_COM.length()) + "/"

        def confirmPatientDomain = PATIENT_DOMAIN + "patient"

        GMAIL_WINDOW = currentWindow

        and:"Wait confirm patient link displayed and click to confirm"
        waitFor(300, 5) {
            gmail_mailContent().find('a', href: contains(confirmPatientDomain), 0).displayed
        }

        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(confirmPatientDomain), 0).click()
        }

        then: "Direct to patient email confirmation page"
        waitFor(30, 1) {
            at EmailConfirmationPage
        }
    }

//    @Ignore
    def "switch from patient email confirmation back to gmail"() {
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
    def "should receive confirm emergency contact email successfully and click email to confirm emergency contact"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Wait inbox button to displayed"
        waitFor(10, 1) { gmail_inboxButton().displayed }
        gmail_inboxButton().click()

        and:"Wait confirm emergency contact email to displayed"
        Thread.sleep(2000 as long)

        repeatActionWaitFor(300, 5, {
            gmail_refreshButton().click()
        }, {
            gmail_mailTable().find("td", text: contains(CAREGIVER_FIRST_NAME), 0).displayed
        })

        and: "Type caregiver first name in search input and click search button"
        searchInput << CAREGIVER_FIRST_NAME
        searchButton.click()

        repeatActionWaitFor(300, 5, {
            gmail_refreshButton().click()
        }, {
            gmail_mailTable().find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).displayed
        })

        gmail_mailTable().find("td", text: contains(CONFIRM_EMAIL_TITLE), 0).click()

        def confirmEmergencyContactDomain = PATIENT_DOMAIN + "emergency_contact"

        and:"Wait confirm emergency contact link to displayed and click to confirm"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(confirmEmergencyContactDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(confirmEmergencyContactDomain), 0).click()
        }

        then: "Direct to emergency contact email confirmation page"
        waitFor(30, 1) {
            at EmailConfirmationPage
        }

    }

    //    @Ignore
    def "switch from emergency contact email confirmation page back to gmail"() {
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
    def "should receive 6 kinds immediate task email successfully and click DASH immediate task email"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Wait inbox button to displayed and click inbox button"
        waitFor(10, 1) { gmail_inboxButton().displayed }
        gmail_inboxButton().click()

        and: "Wait 6 kinds immediate task email to displayed"
        Thread.sleep(2000 as long)
        repeatActionWaitFor(300, 5, {
            gmail_refreshButton().click()
        }, {
            gmail_mailTable().find("td", text: contains(PATIENT_FIRST_NAME)).size() >= 7
        })

        and: "Type immediate and patient first name in search input and click search button"
        searchInput << SEARCH_INPUT
        searchButton.click()

        and:"Wait patient Dash immediate task email to displayed and click it"
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("Disabilities of the Arm, Shoulder and Hand"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("Disabilities of the Arm, Shoulder and Hand"), 0).click()

        def dashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "DASH"

        and:"Wait dash task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(dashTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(dashTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[0].click() //question 1 choice 1
        choicesList[6].click() //question 2 choice 2
        choicesList[12].click() //question 3 choice 3
        choicesList[18].click() //question 4 choice 4
        choicesList[24].click() //question 5 choice 5
        choicesList[28].click() //question 6 choice 4
        choicesList[32].click() //question 7 choice 3
        choicesList[36].click() //question 8 choice 2
        choicesList[40].click() //question 9 choice 1
        choicesList[46].click() //question 10 choice 2
        choicesList[52].click() //question 11 choice 3
        choicesList[58].click() //question 12 choice 4
        choicesList[64].click() //question 13 choice 5
        choicesList[68].click() //question 14 choice 4
        choicesList[72].click() //question 15 choice 3
        choicesList[76].click() //question 16 choice 2
        choicesList[80].click() //question 17 choice 1
        choicesList[86].click() //question 18 choice 2
        choicesList[92].click() //question 19 choice 3
        choicesList[98].click() //question 20 choice 4
        choicesList[104].click() //question 21 choice 5
        choicesList[108].click() //question 22 choice 4
        choicesList[112].click() //question 23 choice 3
        choicesList[116].click() //question 24 choice 2
        choicesList[120].click() //question 25 choice 1
        choicesList[126].click() //question 26 choice 2
        choicesList[132].click() //question 27 choice 3
        choicesList[138].click() //question 28 choice 4
        choicesList[144].click() //question 29 choice 5
        choicesList[148].click() //question 30 choice 1

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
    def "click dash task email link again should direct to taskCompletePage after completing dash tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK! Click dash task link again should direct to taskCompletePage"
        def dashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "DASH"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(dashTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(dashTaskDomain), 0).click()
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
        searchButton.click()

        and:"Wait patient NDI immediate task email to displayed and click it "
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("Neck Disability Index"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("Neck Disability Index"), 0).click()

        def ndiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NDI"

        and:"Wait ndi task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(ndiTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(ndiTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[0].click()  //question 1 choice 1
        choicesList[7].click()  //question 2 choice 2
        choicesList[14].click() //question 3 choice 3
        choicesList[21].click() //question 4 choice 4
        choicesList[28].click() //question 5 choice 5
        choicesList[35].click() //question 6 choice 6
        choicesList[36].click() //question 7 choice 1
        choicesList[43].click() //question 8 choice 2
        choicesList[50].click() //question 9 choice 3
        choicesList[57].click() //question 10 choice 4

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
    def "click ndi task email link again should direct to taskCompletePage after completing dash tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK! Click NDI task link again should direct to taskCompletePage"
        def ndiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NDI"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(ndiTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(ndiTaskDomain), 0).click()
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

        and:"Wait patient QuickDASH immediate task email to displayed and click it "
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("QuickDASH"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("QuickDASH"), 0).click()

        def qucikDashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "QuickDASH"

        and:"Wait quickDash task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(qucikDashTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(qucikDashTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[0].click()  //question 1 choice 1
        choicesList[6].click()  //question 2 choice 2
        choicesList[12].click() //question 3 choice 3
        choicesList[18].click() //question 4 choice 4
        choicesList[24].click() //question 5 choice 5
        choicesList[28].click() //question 6 choice 4
        choicesList[32].click() //question 7 choice 3
        choicesList[36].click() //question 8 choice 2
        choicesList[40].click() //question 9 choice 1
        choicesList[46].click() //question 10 choice 2
        choicesList[52].click() //question 11 choice 3

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
    def "click quickDash task email link again should direct to taskCompletePage after completing quickDash tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK!Click qucikDash link again should direct to taskCompletePage"
        def qucikDashTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "QuickDASH"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(qucikDashTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(qucikDashTaskDomain), 0).click()
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

        and:"Wait patient NRS-BACK immediate task email to displayed and click it "
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("(NRS) for Back Pain"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("(NRS) for Back Pain"), 0).click()

        def nrsBackTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-BACK"

        and:"Wait nrs-back task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(nrsBackTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(nrsBackTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[5].click()  //question 1 choice 5
        choicesList[16].click() //question 2 choice 5

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
    def "check NRS-BACK immediate task email link again should direct to taskCompletePage after completing NRS-BACK tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK!Click nrs back link again should direct to taskCompletePage"
        def nrsBackTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-BACK"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(nrsBackTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(nrsBackTaskDomain), 0).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check nrs back complete page back to gmail"() {
        when: "At NRS-Back CompletePage"
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

        and:"Wait patient NRS-NECK immediate task email to displayed and click it "
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("(NRS) for Neck Pain"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("(NRS) for Neck Pain"), 0).click()

        def nrsNeckTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-NECK"

        and:"Wait nrs neck task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(nrsNeckTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(nrsNeckTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[5].click()  //question 1 choice 5
        choicesList[16].click() //question 2 choice 5

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from nrs neck complete page back to gmail"() {
        when: "At NRS-Neck CompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        driver.close()
        switchToWindow(GMAIL_WINDOW)

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
    def "check NRS-NECK immediate task email link again should direct to taskCompletePage after completing NRS-NECK tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK!Click nrs neck link again should direct to taskCompletePage"
        def nrsNeckTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "NRS-NECK"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(nrsNeckTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(nrsNeckTaskDomain), 0).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from check nrs neck complete page back to gmail"() {
        when: "At NRS-Neck CompletePage"
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

        and:"Wait patient ODI immediate task email to displayed and click it "
        waitFor(300, 5) {
            gmail_mailTable().find("td", text: contains("Oswestry Disability Index"), 0).displayed
        }

        gmail_mailTable().find("td", text: contains("Oswestry Disability Index "), 0).click()

        def odiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "ODI"

        and:"Wait odi task email link to displayed and click it"
        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(odiTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(odiTaskDomain), 0).click()
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

        and: "Complete tasks and click done button"
        choicesList[0].click()   //question 1 choice 1
        choicesList[7].click()   //question 2 choice 2
        choicesList[14].click()  //question 3 choice 3
        choicesList[21].click()  //question 4 choice 4
        choicesList[28].click()  //question 5 choice 5
        choicesList[35].click()  //question 6 choice 6
        choicesList[36].click()  //question 7 choice 1
        choicesList[43].click()  //question 8 choice 2
        choicesList[50].click()  //question 9 choice 3
        choicesList[57].click()  //question 10 choice 4

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //    @Ignore
    def "switch from odi complete page back to gmail"() {
        when: "At odi CompletePage"
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
    def "check ODI immediate task email link again should direct to taskCompletePage after completing ODI tasks"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "JUST CHECK!Click odi link again should direct to taskCompletePage"
        def odiTaskDomain = PATIENT_DOMAIN + PATIENT_FIRST_NAME + MAIL_COMPONENT + "ODI"

        waitFor(100, 5) {
            gmail_mailContent().find('a', href: contains(odiTaskDomain), 0).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            gmail_mailContent().find('a', href: contains(odiTaskDomain), 0).click()
        }

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

    //@Ignore
    def "switch from check odi complete page back to gmail"() {
        when: "At odi CompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

    //@Ignore
    def "archive all mails"(){
        when: 'Refresh the page'
        refresh()

        then: 'Still in GmailAppPage'
        waitFor(30, 1) {
            at GmailAppPage
        }

        when: "Click inbox button"
        waitFor(100, 2) { gmail_inboxButton().displayed }

        gmail_inboxButton().click()

        Thread.sleep(2000 as long)

        and:"Click choose button"
        waitFor(100, 2) { gmail_chooseButton().displayed }
        gmail_chooseButton().click()

//        and: "Wait for choose menu come out"
//        waitFor(30, 2) { gmail_chooseMenu().displayed }
//        gmail_allMenuItem().click()

        and:"Wait archive button display and click to archive"
        waitFor(30, 2) { gmail_archiveButton().displayed }
        gmail_archiveButton().click()

        then: "There is no new mail"
        waitFor(30, 3) {
            gmail_mainContent().find('td', text: contains("No new mail!"), 0)
        }
    }

}
