package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
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
    @Shared PROVIDER_EMAIL
    @Shared PROVIDER_PASSWORD
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

    static DASH_NO_DIFFICULTY_CHOICE = "No difficulty"
    static DASH_MID_DIFFICULTY_CHOICE = "Mild difficulty"
    static DASH_MODERATE_DIFFICULTY_CHOICE = "Moderate difficulty"
    static DASH_SEVERE_DIFFICULTY_CHOICE = "Severe difficulty"
    static DASH_UNABLE_CHOICE = "Unable"
    static DASH_NONE_CHOICE = "None"
    static DASH_MILD_CHOICE = "Mild"
    static DASH_MODERATE_CHOICE = "Moderate"
    static DASH_SEVERE_CHOICE = "Severe"
    static DASH_EXTREME_CHOICE = "Extreme"

    static QUICK_DASH_NO_DIFFICULTY_CHOICE = "NO DIFFICULTY"
    static QUICK_DASH_MID_DIFFICULTY_CHOICE = "MILD DIFFICULTY"
    static QUICK_DASH_MODERATE_DIFFICULTY_CHOICE = "MODERATE DIFFICULTY"
    static QUICK_DASH_SEVERE_DIFFICULTY_CHOICE = "SEVERE DIFFICULTY"
    static QUICK_DASH_UNABLE_CHOICE = "UNABLE"

    static QUICK_DASH_NONE_CHOICE = "NONE"
    static QUICK_DASH_MILD_CHOICE = "MILD"
    static QUICK_DASH_MODERATE_CHOICE = "MODERATE"
    static QUICK_DASH_SEVERE_CHOICE = "SEVERE"
    static QUICK_DASH_EXTREME_CHOICE = "EXTREME"


    def setupSpec() {

        def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
        def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
        def db = client.getDB('ratchet-tests');

        IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value

        PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
        PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

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
    def "should receive confirm emergency contact email successfully and click email to confirm emergency contact"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Wait inbox button to displayed"
        waitFor(10, 1) { gmail_inboxButton().displayed }
        gmail_inboxButton().click()

//        and:"Wait confirm emergency contact email to displayed"
//        Thread.sleep(2000 as long)
//
//        repeatActionWaitFor(300, 5, {
//            gmail_refreshButton().click()
//        }, {
//            gmail_mailTable().find("td", text: contains(CAREGIVER_FIRST_NAME), 0).displayed
//        })

        and: "Type caregiver first name in search input and click search button"
        waitFor(30, 1) { searchInput.displayed }
        searchInput.value('')
        searchInput << CAREGIVER_FIRST_NAME
        searchButton.click()

        Thread.sleep(2000 as long)

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

//        @Ignore
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
    def "should receive confirm patient email successfully and click email to confirm patient"() {
        when: "At GmailAppPage now"
        at GmailAppPage

        and: "Wait inbox button to displayed and click inbox button"
        waitFor(10, 1) { gmail_inboxButton().displayed }
        gmail_inboxButton().click()

//        and:"Wait confirm patient email to displayed"
//        Thread.sleep(2000 as long)
//        repeatActionWaitFor(300, 5, {
//            gmail_refreshButton().click()
//        }, {
//            gmail_mailTable().find("td", text: contains(PATIENT_FIRST_NAME), 0).displayed
//        })

        and: "Type patient first name in search input and click search button"
        waitFor(30, 1) { searchInput.displayed }
        searchInput.value('')
        searchInput << "${PATIENT_FIRST_NAME} ${RAT_COM_PATIENT_IDENTIFY}"
        searchButton.click()

        Thread.sleep(2000 as long)

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
        waitFor(30, 1) { searchInput.displayed }
        searchInput.value('')
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

        and: "Check every question and complete tasks and click done button"
        def choiceList = answerList.find('.text')
        waitFor {choiceList.displayed}

        waitFor (3,1){
            $(questionList[0]).text().trim()=="1. Open a tight or new jar."
            $(choiceList[0]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[1]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[2]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[3]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[4]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[0].click() //question 1 choice 1

        waitFor (3,1){
            $(questionList[1]).text().trim()=="2. Write."
            $(choiceList[5]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[6]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[7]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[8]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[9]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[6].click() //question 2 choice 2

        waitFor (3,1){
            $(questionList[2]).text().trim()=="3. Turn a key."
            $(choiceList[10]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[11]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[12]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[13]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[14]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[12].click() //question 3 choice 3

        waitFor (3,1){
            $(questionList[3]).text().trim()=="4. Prepare a meal."
            $(choiceList[15]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[16]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[17]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[18]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[19]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[18].click() //question 4 choice 4

        waitFor (3,1){
            $(questionList[4]).text().trim()=="5. Push open a heavy door."
            $(choiceList[20]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[21]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[22]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[23]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[24]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[24].click() //question 5 choice 5

        waitFor (3,1){
            $(questionList[5]).text().trim()=="6. Place an object on a shelf above your head."
            $(choiceList[25]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[26]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[27]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[28]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[29]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[28].click() //question 6 choice 4

        waitFor (3,1){
            $(questionList[6]).text().trim()=="7. Do heavy household chores (e.g., wash walls, wash floors)."
            $(choiceList[30]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[31]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[32]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[33]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[34]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[32].click() //question 7 choice 3

        waitFor (3,1){
            $(questionList[7]).text().trim()=="8. Garden or do yard work."
            $(choiceList[35]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[36]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[37]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[38]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[39]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[36].click() //question 8 choice 2

        waitFor (3,1){
            $(questionList[8]).text().trim()=="9. Make a bed."
            $(choiceList[40]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[41]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[42]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[43]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[44]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[40].click() //question 9 choice 1

        waitFor (3,1){
            $(questionList[9]).text().trim()=="10. Carry a shopping bag or briefcase."
            $(choiceList[45]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[46]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[47]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[48]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[49]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[46].click() //question 10 choice 2

        waitFor (3,1){
            $(questionList[10]).text().trim()=="11. Carry a heavy object (over 10 lbs)."
            $(choiceList[50]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[51]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[52]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[53]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[54]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[52].click() //question 11 choice 3

        waitFor (3,1){
            $(questionList[11]).text().trim()=="12. Change a lightbulb overhead."
            $(choiceList[55]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[56]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[57]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[58]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[59]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[58].click() //question 12 choice 4

        waitFor (3,1){
            $(questionList[12]).text().trim()=="13. Wash or blow dry your hair."
            $(choiceList[60]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[61]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[62]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[63]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[64]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[64].click() //question 13 choice 5

        waitFor (3,1){
            $(questionList[13]).text().trim()=="14. Wash your back."
            $(choiceList[65]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[66]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[67]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[68]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[69]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[68].click() //question 14 choice 4

        waitFor (3,1){
            $(questionList[14]).text().trim()=="15. Put on a pullover sweater."
            $(choiceList[70]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[71]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[72]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[73]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[74]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[72].click() //question 15 choice 3

        waitFor (3,1){
            $(questionList[15]).text().trim()=="16. Use a knife to cut food."
            $(choiceList[75]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[76]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[77]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[78]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[79]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[76].click() //question 16 choice 2

        waitFor (3,1){
            $(questionList[16]).text().trim()=="17. Recreational activities which require little effort (e.g., cardplaying, knitting, etc.)."
            $(choiceList[80]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[81]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[82]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[83]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[84]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[80].click() //question 17 choice 1

        waitFor (3,1){
            $(questionList[17]).text().trim()=="18. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
            $(choiceList[85]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[86]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[87]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[88]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[89]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[86].click() //question 18 choice 2

        waitFor (3,1){
            $(questionList[18]).text().trim()=="19. Recreational activities in which you move your arm freely (e.g., playing frisbee, badminton, etc.)."
            $(choiceList[90]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[91]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[92]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[93]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[94]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[92].click() //question 19 choice 3

        waitFor (3,1){
            $(questionList[19]).text().trim()=="20. Manage transportation needs (getting from one place to another)."
            $(choiceList[95]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[96]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[97]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[98]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[99]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[98].click() //question 20 choice 4

        waitFor (3,1){
            $(questionList[20]).text().trim()=="21. Sexual activities."
            $(choiceList[100]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[101]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[102]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[103]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[104]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[104].click() //question 21 choice 5

        waitFor (3,1){
            $(questionList[21]).text().trim()=="22. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
            $(choiceList[105]).text().trim() =="Not at all"
            $(choiceList[106]).text().trim() =="Slightly"
            $(choiceList[107]).text().trim() =="Moderately"
            $(choiceList[108]).text().trim() =="Quite a bit"
            $(choiceList[109]).text().trim() =="Extremely"
        }
        choicesList[108].click() //question 22 choice 4

        waitFor (3,1){
            $(questionList[22]).text().trim()=="23. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
            $(choiceList[110]).text().trim() =="Not limited at all"
            $(choiceList[111]).text().trim() =="Slightly limited"
            $(choiceList[112]).text().trim() =="Moderately limited"
            $(choiceList[113]).text().trim() =="Very limited"
            $(choiceList[114]).text().trim() ==DASH_UNABLE_CHOICE
        }
        choicesList[112].click() //question 23 choice 3

        waitFor (3,1){
            $(questionList[23]).text().trim()=="24. Arm, shoulder or hand pain."
            $(choiceList[115]).text().trim() ==DASH_NONE_CHOICE
            $(choiceList[116]).text().trim() ==DASH_MILD_CHOICE
            $(choiceList[117]).text().trim() ==DASH_MODERATE_CHOICE
            $(choiceList[118]).text().trim() ==DASH_SEVERE_CHOICE
            $(choiceList[119]).text().trim() ==DASH_EXTREME_CHOICE
        }
        choicesList[116].click() //question 24 choice 2

        waitFor (3,1){
            $(questionList[24]).text().trim()=="25. Arm, shoulder or hand pain when you performed any specific activity."
            $(choiceList[120]).text().trim() ==DASH_NONE_CHOICE
            $(choiceList[121]).text().trim() ==DASH_MILD_CHOICE
            $(choiceList[122]).text().trim() ==DASH_MODERATE_CHOICE
            $(choiceList[123]).text().trim() ==DASH_SEVERE_CHOICE
            $(choiceList[124]).text().trim() ==DASH_EXTREME_CHOICE
        }
        choicesList[120].click() //question 25 choice 1

        waitFor (3,1){
            $(questionList[25]).text().trim()=="26. Tingling (pins and needles) in your arm, shoulder or hand."
            $(choiceList[125]).text().trim() ==DASH_NONE_CHOICE
            $(choiceList[126]).text().trim() ==DASH_MILD_CHOICE
            $(choiceList[127]).text().trim() ==DASH_MODERATE_CHOICE
            $(choiceList[128]).text().trim() ==DASH_SEVERE_CHOICE
            $(choiceList[129]).text().trim() ==DASH_EXTREME_CHOICE
        }
        choicesList[126].click() //question 26 choice 2

        waitFor (3,1){
            $(questionList[26]).text().trim()=="27. Weakness in your arm, shoulder or hand."
            $(choiceList[130]).text().trim() ==DASH_NONE_CHOICE
            $(choiceList[131]).text().trim() ==DASH_MILD_CHOICE
            $(choiceList[132]).text().trim() ==DASH_MODERATE_CHOICE
            $(choiceList[133]).text().trim() ==DASH_SEVERE_CHOICE
            $(choiceList[134]).text().trim() ==DASH_EXTREME_CHOICE
        }
        choicesList[132].click() //question 27 choice 3

        waitFor (3,1){
            $(questionList[27]).text().trim()=="28. Stiffness in your arm, shoulder or hand."
            $(choiceList[135]).text().trim() ==DASH_NONE_CHOICE
            $(choiceList[136]).text().trim() ==DASH_MILD_CHOICE
            $(choiceList[137]).text().trim() ==DASH_MODERATE_CHOICE
            $(choiceList[138]).text().trim() ==DASH_SEVERE_CHOICE
            $(choiceList[139]).text().trim() ==DASH_EXTREME_CHOICE
        }
        choicesList[138].click() //question 28 choice 4

        waitFor (3,1){
            $(questionList[28]).text().trim()=="29. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand?"
            $(choiceList[140]).text().trim() ==DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[141]).text().trim() ==DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[142]).text().trim() ==DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[143]).text().trim() ==DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[144]).text().trim() =="So much I can\'t sleep"
        }
        choicesList[144].click() //question 29 choice 5

        waitFor (3,1){
            $(questionList[29]).text().trim()=="30. I feel less capable, less confident or less useful because of my arm, shoulder or hand problem."
            $(choiceList[145]).text().trim() =="Strongly disagree"
            $(choiceList[146]).text().trim() =="Disagree"
            $(choiceList[147]).text().trim() =="Neither agree nor disagree"
            $(choiceList[148]).text().trim() =="Agree"
            $(choiceList[149]).text().trim() =="Strongly agree"
        }
        choicesList[148].click() //question 30 choice 1

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

//        @Ignore
    def "switch from dash complete page back to gmail"() {
        when: "At DashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 50.83"
        }

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

//        @Ignore
    def "switch from check dash complete page back to gmail"() {
        when: "At DashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 50.83"
        }

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
        def choiceList = answerList.find('.text')
        waitFor {choiceList.displayed}

        waitFor (3,1){
            $(questionList[0]).text().trim()=="Section 1: Pain Intensity"
            $(choiceList[0]).text().trim() =="I have no pain at the moment."
            $(choiceList[1]).text().trim() =="The pain is very mild at the moment."
            $(choiceList[2]).text().trim() =="The pain is moderate at the moment."
            $(choiceList[3]).text().trim() =="The pain is fairly severe at the moment."
            $(choiceList[4]).text().trim() =="The pain is very severe at the moment."
            $(choiceList[5]).text().trim() =="The pain is the worst imaginable at the moment."
        }
        choicesList[0].click()  //question 1 choice 1

        waitFor (3,1){
            $(questionList[1]).text().trim()=="Section 2: Personal Care (Washing, Dressing, etc.)"
            $(choiceList[6]).text().trim() =="I can look after myself normally without causing extra pain."
            $(choiceList[7]).text().trim() =="I can look after myself normally but it causes extra pain."
            $(choiceList[8]).text().trim() =="It is painful to look after myself and I am slow and careful."
            $(choiceList[9]).text().trim() =="I need some help but manage most of my personal care."
            $(choiceList[10]).text().trim() =="I need help everyday in most aspects of self care."
            $(choiceList[11]).text().trim() =="I do not get dressed, I wash with difficulty and stay in bed."
        }
        choicesList[7].click()  //question 2 choice 2

        waitFor (3,1){
            $(questionList[2]).text().trim()=="Section 3: Lifting"
            $(choiceList[12]).text().trim() =="I can lift heavy weights without extra pain."
            $(choiceList[13]).text().trim() =="I can lift heavy weights but it gives extra pain."
            $(choiceList[14]).text().trim() =="Pain prevents me from lifting heavy weights off the floor, but I can manage if they are conveniently positioned, for example on a table."
            $(choiceList[15]).text().trim() =="Pain prevents me from lifting heavy weights, but I can manage light to medium weights if they are conveniently positioned."
            $(choiceList[16]).text().trim() =="I can lift very light weights."
            $(choiceList[17]).text().trim() =="I cannot lift or carry anything at all."
        }
        choicesList[14].click() //question 3 choice 3

        waitFor (3,1){
            $(questionList[3]).text().trim()=="Section 4: Reading"
            $(choiceList[18]).text().trim() =="I can read as much as I want to with no pain in my neck."
            $(choiceList[19]).text().trim() =="I can read as much as I want to with slight pain in my neck."
            $(choiceList[20]).text().trim() =="I can read as much as I want to with moderate pain in my neck."
            $(choiceList[21]).text().trim() =="I cannot read as much as I want because of moderate pain in my neck."
            $(choiceList[22]).text().trim() =="I can hardly read at all because of severe pain in my neck."
            $(choiceList[23]).text().trim() =="I cannot read at all."
        }
        choicesList[21].click() //question 4 choice 4

        waitFor (3,1){
            $(questionList[4]).text().trim()=="Section 5: Headaches"
            $(choiceList[24]).text().trim() =="I have no headaches at all."
            $(choiceList[25]).text().trim() =="I have slight headaches that come infrequently."
            $(choiceList[26]).text().trim() =="I have moderate headaches which come infrequently."
            $(choiceList[27]).text().trim() =="I have moderate headaches which come frequently."
            $(choiceList[28]).text().trim() =="I have severe headaches which come frequently."
            $(choiceList[29]).text().trim() =="I have headaches almost all the time."
        }
        choicesList[28].click() //question 5 choice 5

        waitFor (3,1){
            $(questionList[5]).text().trim()=="Section 6: Concentration"
            $(choiceList[30]).text().trim() =="I can concentrate fully when I want to with no difficulty."
            $(choiceList[31]).text().trim() =="I can concentrate fully when I want to with slight difficulty."
            $(choiceList[32]).text().trim() =="I have a fair degree of difficulty in concentrating when I want to."
            $(choiceList[33]).text().trim() =="I have a lot of difficulty in concentrating when I want to."
            $(choiceList[34]).text().trim() =="I have a great deal of difficulty in concentrating when I want to."
            $(choiceList[35]).text().trim() =="I cannot concentrate at all."
        }
        choicesList[35].click() //question 6 choice 6

        waitFor (3,1){
            $(questionList[6]).text().trim()=="Section 7: Work"
            $(choiceList[36]).text().trim() =="I can do as much work as I want to."
            $(choiceList[37]).text().trim() =="I can do my usual work, but no more."
            $(choiceList[38]).text().trim() =="I can do most of my usual work, but no more."
            $(choiceList[39]).text().trim() =="I cannot do my usual work."
            $(choiceList[40]).text().trim() =="I can hardly do any work at all."
            $(choiceList[41]).text().trim() =="I cannot do any work at all."
        }
        choicesList[36].click() //question 7 choice 1

        waitFor (3,1){
            $(questionList[7]).text().trim()=="Section 8: Driving"
            $(choiceList[42]).text().trim() =="I can drive my car without any neck pain."
            $(choiceList[43]).text().trim() =="I can drive my car as long as I want with slight pain in my neck."
            $(choiceList[44]).text().trim() =="I can drive my car as long as I want with moderate pain in my neck."
            $(choiceList[45]).text().trim() =="I cannot drive my car as long as I want because of moderate pain in my neck."
            $(choiceList[46]).text().trim() =="I can hardly drive at all because of severe pain in my neck."
            $(choiceList[47]).text().trim() =="I cannot drive my car at all."
        }
        choicesList[43].click() //question 8 choice 2

        waitFor (3,1){
            $(questionList[8]).text().trim()=="Section 9: Sleeping"
            $(choiceList[48]).text().trim() =="I have no trouble sleeping."
            $(choiceList[49]).text().trim() =="My sleep is slightly disturbed (less than 1 hour sleepless)."
            $(choiceList[50]).text().trim() =="My sleep is mildly disturbed ( 1-2 hours sleepless)."
            $(choiceList[51]).text().trim() =="My sleep is moderately disturbed ( 2-3 hours sleepless)."
            $(choiceList[52]).text().trim() =="My sleep is greatly disturbed ( 3-5 hours sleepless)."
            $(choiceList[53]).text().trim() =="My sleep is completely disturbed ( 5-7 hours sleepless)."
        }
        choicesList[50].click() //question 9 choice 3

        waitFor (3,1){
            $(questionList[9]).text().trim()=="Section 10: Recreation"
            $(choiceList[54]).text().trim() =="I am able to engage in all my recreation activities with no neck pain at all."
            $(choiceList[55]).text().trim() =="I am able to engage in all my recreation activities, with some pain in my neck."
            $(choiceList[56]).text().trim() =="I am able to engage in most, but not all, of my usual recreation activities because of pain in my neck."
            $(choiceList[57]).text().trim() =="I am able to engage in a few of my usual recreation activities because of pain in my neck."
            $(choiceList[58]).text().trim() =="I can hardly do any recreation activities because of pain in my neck."
            $(choiceList[59]).text().trim() =="I cannot do any recreation activities at all."
        }
        choicesList[57].click() //question 10 choice 4

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

//        @Ignore
    def "switch from ndi complete page back to gmail"() {
        when: "At NDICompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 42.0"
        }

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

//        @Ignore
    def "switch from check ndi complete page back to gmail"() {
        when: "At NDICompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 42.0"
        }

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
        def choiceList = answerList.find('.text')
        waitFor {choiceList.displayed}

        waitFor (3,1){
            $(questionList[0]).text().trim()=="1. Open a tight or new jar."
            $(choiceList[0]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[1]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[2]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[3]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[4]).text().trim() ==QUICK_DASH_UNABLE_CHOICE

        }
        choicesList[0].click()  //question 1 choice 1

        waitFor (3,1){
            $(questionList[1]).text().trim()=="2. Do heavy household chores (e.g., wash walls, floors)."
            $(choiceList[5]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[6]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[7]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[8]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[9]).text().trim() ==QUICK_DASH_UNABLE_CHOICE
        }
        choicesList[6].click()  //question 2 choice 2

        waitFor (3,1){
            $(questionList[2]).text().trim()=="3. Carry a shopping bag or briefcase."
            $(choiceList[10]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[11]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[12]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[13]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[14]).text().trim() ==QUICK_DASH_UNABLE_CHOICE
        }
        choicesList[12].click() //question 3 choice 3

        waitFor (3,1){
            $(questionList[3]).text().trim()=="4. Wash your back."
            $(choiceList[15]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[16]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[17]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[18]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[19]).text().trim() ==QUICK_DASH_UNABLE_CHOICE
        }
        choicesList[18].click() //question 4 choice 4

        waitFor (3,1){
            $(questionList[4]).text().trim()=="5. Use a knife to cut food."
            $(choiceList[20]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[21]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[22]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[23]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[24]).text().trim() ==QUICK_DASH_UNABLE_CHOICE
        }
        choicesList[24].click() //question 5 choice 5

        waitFor (3,1){
            $(questionList[5]).text().trim()=="6. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
            $(choiceList[25]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[26]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[27]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[28]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[29]).text().trim() ==QUICK_DASH_UNABLE_CHOICE
        }
        choicesList[28].click() //question 6 choice 4

        waitFor (3,1){
            $(questionList[6]).text().trim()=="7. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
            $(choiceList[30]).text().trim() =="NOT AT ALL"
            $(choiceList[31]).text().trim() =="SLIGHTLY"
            $(choiceList[32]).text().trim() =="MODERATELY"
            $(choiceList[33]).text().trim() =="QUITE A BIT"
            $(choiceList[34]).text().trim() =="EXTREMELY"
        }
        choicesList[32].click() //question 7 choice 3

        waitFor (3,1){
            $(questionList[7]).text().trim()=="8. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
            $(choiceList[35]).text().trim() =="NOT LIMITED AT ALL"
            $(choiceList[36]).text().trim() =="SLIGHTLY LIMITED"
            $(choiceList[37]).text().trim() =="MODERATELY LIMITED"
            $(choiceList[38]).text().trim() =="VERY LIMITED"
            $(choiceList[39]).text().trim() =="UNABLE"
        }
        choicesList[36].click() //question 8 choice 2

        waitFor (3,1){
            $(questionList[8]).text().trim()=="9. Arm, shoulder or hand pain."
            $(choiceList[40]).text().trim() ==QUICK_DASH_NONE_CHOICE
            $(choiceList[41]).text().trim() ==QUICK_DASH_MILD_CHOICE
            $(choiceList[42]).text().trim() ==QUICK_DASH_MODERATE_CHOICE
            $(choiceList[43]).text().trim() ==QUICK_DASH_SEVERE_CHOICE
            $(choiceList[44]).text().trim() ==QUICK_DASH_EXTREME_CHOICE
        }
        choicesList[40].click() //question 9 choice 1

        waitFor (3,1){
            $(questionList[9]).text().trim()=="10. Tingling (pins and needles) in your arm, shoulder or hand."
            $(choiceList[45]).text().trim() ==QUICK_DASH_NONE_CHOICE
            $(choiceList[46]).text().trim() ==QUICK_DASH_MILD_CHOICE
            $(choiceList[47]).text().trim() ==QUICK_DASH_MODERATE_CHOICE
            $(choiceList[48]).text().trim() ==QUICK_DASH_SEVERE_CHOICE
            $(choiceList[49]).text().trim() ==QUICK_DASH_EXTREME_CHOICE
        }
        choicesList[46].click() //question 10 choice 2

        waitFor (3,1){
            $(questionList[10]).text().trim()=="11. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand?"
            $(choiceList[50]).text().trim() ==QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[51]).text().trim() ==QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[52]).text().trim() ==QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[53]).text().trim() ==QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
//            $(choiceList[54]).text().trim() =="SO MUCH DIFFICULTY THAT I CAN'T SLEEP"
        }
        choicesList[52].click() //question 11 choice 3

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

//        @Ignore
    def "switch from quickDash complete page back to gmail"() {
        when: "At QuickDashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 43.18"
        }

        waitFor (30,1){
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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

//        @Ignore
    def "switch from check quickDash complete page back to gmail"() {
        when: "At QuickDashCompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 43.18"
        }

        waitFor (30,1){
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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
        waitFor (3,1){
            $(questionList[0]).text().trim()=='On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your back pain right now?'
        }
        choicesList[5].click()  //question 1 choice 5

        waitFor (3,1){
            $(questionList[1]).text().trim()=='On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your leg pain right now?'
        }
        choicesList[16].click() //question 2 choice 5

        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
        }
    }

//        @Ignore
    def "switch from nrs back complete page back to gmail"() {
        when: "At NRS-Back CompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Back Score: 5"
            $(scores[1]).text().trim() == "Leg Score: 5"
        }

        waitFor (30,1){
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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

//        @Ignore
    def "switch from check nrs back complete page back to gmail"() {
        when: "At NRS-Back CompletePage"
        at TaskCompletePage

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Back Score: 5"
            $(scores[1]).text().trim() == "Leg Score: 5"
        }

        waitFor (30,1){
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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
        waitFor (30,1){
            phoneNumberInput << LAST_4_NUMBER
            startButton.click()
        }

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
        waitFor (3,1){
            $(questionList[0]).text().trim()=='On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your neck pain right now?'
        }
        choicesList[5].click()  //question 1 choice 5

        waitFor (3,1){
            $(questionList[1]).text().trim()=='On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your arm pain right now?'
        }
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
        waitFor (3,1){
            $(scores[0]).text().trim() == "Neck Score: 5"
            $(scores[1]).text().trim() == "Arm Score: 5"
        }

        waitFor(30,1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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
        waitFor (3,1){
            $(scores[0]).text().trim() == "Neck Score: 5"
            $(scores[1]).text().trim() == "Arm Score: 5"
        }

        waitFor (30,1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

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
        def choiceList = answerList.find('.text')
        waitFor {choiceList.displayed}

        waitFor (3,1){
            $(questionList[0]).text().trim()=="Section 1: Pain Intensity"
            $(choiceList[0]).text().trim() =="I have no pain at the moment"
            $(choiceList[1]).text().trim() =="The pain is very mild at the moment"
            $(choiceList[2]).text().trim() =="The pain is moderate at the moment"
            $(choiceList[3]).text().trim() =="The pain is fairly severe at the moment"
            $(choiceList[4]).text().trim() =="The pain is very severe at the moment"
            $(choiceList[5]).text().trim() =="The pain is the worst imaginable at the moment"
        }
        choicesList[0].click()   //question 1 choice 1

        waitFor (3,1){
            $(questionList[1]).text().trim()=="Section 2: Personal Care (washing, dressing, etc.…)"
            $(choiceList[6]).text().trim() =="I can look after myself normally but it is very painful"
            $(choiceList[7]).text().trim() =="It is painful to look after myself and I am slow and careful"
            $(choiceList[8]).text().trim() =="I need some help but manage most of my personal care"
            $(choiceList[9]).text().trim() =="I need help everyday in most aspects of my personal care"
            $(choiceList[10]).text().trim() =="I need help everyday in most aspects of self-care"
            $(choiceList[11]).text().trim() =="I do not get dressed, wash with difficulty and stay in bed"
        }
        choicesList[7].click()   //question 2 choice 2

        waitFor (3,1){
            $(questionList[2]).text().trim()=="Section 3: Lifting"
            $(choiceList[12]).text().trim() =="I can lift heavy weights without extra pain"
            $(choiceList[13]).text().trim() =="I can lift heavy weights but it gives extra pain"
            $(choiceList[14]).text().trim() =="Pain prevents me from lifting heavy weights off the floor, but I can manage if they are conveniently positioned (i.e. on a table)"
            $(choiceList[15]).text().trim() =="Pain prevents me from lifting heavy weights, but I can manage light to medium weights if they are conveniently positioned"
            $(choiceList[16]).text().trim() =="I can lift only very light weights"
            $(choiceList[17]).text().trim() =="I cannot lift or carry anything at all"
        }
        choicesList[14].click()  //question 3 choice 3

        waitFor (3,1){
            $(questionList[3]).text().trim()=="Section 4: Walking"
            $(choiceList[18]).text().trim() =="Pain does not prevent me walking any distance"
            $(choiceList[19]).text().trim() =="Pain prevents me walking more than 1 mile"
            $(choiceList[20]).text().trim() =="Pain prevents me walking more than ¼ of a mile"
            $(choiceList[21]).text().trim() =="Pain prevents me walking more than 100 yards"
            $(choiceList[22]).text().trim() =="I can only walk using a stick or crutches"
            $(choiceList[23]).text().trim() =="I am in bed most of the time and have to crawl to the toilet"
        }
        choicesList[21].click()  //question 4 choice 4

        waitFor (3,1){
            $(questionList[4]).text().trim()=="Section 5: Sitting"
            $(choiceList[24]).text().trim() =="I can sit in any chair as long as I like"
            $(choiceList[25]).text().trim() =="I can sit in my favorite chair as long as I like"
            $(choiceList[26]).text().trim() =="Pain prevents me from sitting for more than 1 hour"
            $(choiceList[27]).text().trim() =="Pain prevents me from sitting for more than ½ hour"
            $(choiceList[28]).text().trim() =="Pain prevents me from sitting for more than 10 minutes"
            $(choiceList[29]).text().trim() =="Pain prevents me from sitting at all"
        }
        choicesList[28].click()  //question 5 choice 5

        waitFor (3,1){
            $(questionList[5]).text().trim()=="Section 6: Standing"
            $(choiceList[30]).text().trim() =="I can stand as long as I want without extra pain"
            $(choiceList[31]).text().trim() =="I can stand as long as I want but it gives me extra pain"
            $(choiceList[32]).text().trim() =="Pain prevents me from standing more than 1 hour"
            $(choiceList[33]).text().trim() =="Pain prevents me from standing for more than ½ an hour"
            $(choiceList[34]).text().trim() =="Pain prevents me from standing for more than 10 minutes"
            $(choiceList[35]).text().trim() =="Pain prevents me from standing at all"
        }
        choicesList[35].click()  //question 6 choice 6

        waitFor (3,1){
            $(questionList[6]).text().trim()=="Section 7: Sleeping"
            $(choiceList[36]).text().trim() =="My sleep is never disturbed by pain"
            $(choiceList[37]).text().trim() =="My sleep is occasionally disturbed by pain"
            $(choiceList[38]).text().trim() =="Because of pain, I have less than 6 hours sleep"
            $(choiceList[39]).text().trim() =="Because of pain, I have less than 4 hours sleep"
            $(choiceList[40]).text().trim() =="Because of pain, I have less than 2 hours sleep"
            $(choiceList[41]).text().trim() =="Pain prevents me from sleeping at all"
        }
        choicesList[36].click()  //question 7 choice 1

        waitFor (3,1){
            $(questionList[7]).text().trim()=="Section 8: Sex Life (if applicable)"
            $(choiceList[42]).text().trim() =="My sex life is normal and causes no extra pain"
            $(choiceList[43]).text().trim() =="My sex life is normal but causes some extra pain"
            $(choiceList[44]).text().trim() =="My sex life is nearly normal but is very painful"
            $(choiceList[45]).text().trim() =="My sex life is severely restricted by pain"
            $(choiceList[46]).text().trim() =="My sex life is nearly absent because of pain"
            $(choiceList[47]).text().trim() =="Pain prevents any sex life at all"
        }
        choicesList[43].click()  //question 8 choice 2

        waitFor (3,1){
            $(questionList[8]).text().trim()=="Section 9: Social Life"
            $(choiceList[48]).text().trim() =="My social life is normal and causes me no extra pain"
            $(choiceList[49]).text().trim() =="My social life is normal but increases the degree of pain"
            $(choiceList[50]).text().trim() =="Pain has no significant effect on my social life apart from limiting my more energetic interests (i.e. sports)"
            $(choiceList[51]).text().trim() =="Pain has restricted my social life and I do not go out as often"
            $(choiceList[52]).text().trim() =="Pain has restricted social life to my home"
            $(choiceList[53]).text().trim() =="I have no social life because of the pain"
        }
        choicesList[50].click()  //question 9 choice 3

        waitFor (3,1){
            $(questionList[9]).text().trim()=="Section 10: Traveling"
            $(choiceList[54]).text().trim() =="I can travel anywhere without pain"
            $(choiceList[55]).text().trim() =="I can travel anywhere but it gives me extra pain"
            $(choiceList[56]).text().trim() =="Pain is bad but I manage journeys over 2 hours"
            $(choiceList[57]).text().trim() =="Pain restricts me to journeys of less than 1 hour"
            $(choiceList[58]).text().trim() =="Pain restricts me to short necessary journeys less than 30 minutes"
            $(choiceList[59]).text().trim() =="Pain prevents me from traveling except to the doctor or hospital"
        }
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
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 42.0"
        }

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

        and: "Close window and back to gmail"
        waitFor (3,1){
            $(scores[0]).text().trim() == "Score: 42.0"
        }
    }

    def "should login with the activate account created by client successfully"() {
        browser.setBaseUrl(getClientUrl())
        when: "At login page"
        to LoginPage

        and: "Wait for email input to displayed"
        waitFor(30, 1) { emailInput.displayed }

        and: "Type in provider email and password"
        emailInput.value('')
        emailInput << PROVIDER_EMAIL
        passwordInput << PROVIDER_PASSWORD

        and: "Click login button"
        loginButton.click()

        then: "Direct to patients page"
        waitFor(30, 1) {
            at PatientsPage
        }
    }

    def "archive this treatment of patient"() {
        when: "Click first line of table"
        firstLine.click()

        then: "Direct to account detail page"
        waitFor(30, 1) {
            at PatientDetailPage
        }

        Thread.sleep(3000 as long)

        when: "Click to archived treatment"
        waitFor(30, 1) {
            archivedButton.displayed
        }
        archivedButton.click()

        then: "Archived model display"
        waitFor(5, 1) {
            archivedModel.displayed
        }

        when: "Click Archieve to agree"
        waitFor(10, 1) { archivedModel.agreeButton.displayed }

        archivedModel.agreeButton.click()

        and: "Wait for archive treatment dialog disappear"
        waitFor(30, 1) { !archivedModel.displayed }

        then: "Check archived treatment title"
        waitFor(50, 1) {
            at PatientDetailPage
        }
        waitFor(100, 1) {
            archivedTreatmentTitle.displayed
        }
    }

    //@Ignore
    def "switch back to gmail"() {
        when: "At PatientDetailPage"
        at PatientDetailPage

        waitFor(30, 1) {
            driver.close()
            switchToWindow(GMAIL_WINDOW)
        }

        then: "At GmailAppPage now"
        at GmailAppPage
    }

//    @Ignore
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
