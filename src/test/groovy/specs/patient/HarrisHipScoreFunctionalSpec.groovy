package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class HarrisHipScoreFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

/*	def "start Harris Hip Score immediate task"() {
		given:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/Harris+Hip+Score/")

		when:
		go link;

		then:
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

	//    @Ignore
	def "check Harris Hip Score phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		then: "Type last 4 number and start to complete tasks"

		repeatActionWaitFor(60, 1, {
			phoneNumberInput.value(LAST_4_NUMBER)
			startButton.click()
		}, {
			at TaskIntroPage
		})
	}*/

	def "complete Harris Hip Score immediate task"() {
		when: "At Harris Hip Score task page"
		at TaskIntroPage

		and: "Complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "Pain"
			$(choiceList[0]).text().trim() == "None, or ignores it"
			$(choiceList[1]).text().trim() == "Slight, occasional, no compromise in activity"
			$(choiceList[2]).text().trim() == "Mild pain, no effect on average activities, rarely moderate pain with unusual activity, may take aspirin"
			$(choiceList[3]).text().trim() == "Moderate pain, tolerable but makes concessions to pain. Some limitations of ordinary activity or work. May require occasional pain medication stronger than aspirin"
			$(choiceList[4]).text().trim() == "Marked pain, serious limitation of activities"
			$(choiceList[5]).text().trim() == "Totally disabled, crippled, pain in bed, bedridden"
		}
		js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click()  //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "Support"
			$(choiceList[6]).text().trim() == "None"
			$(choiceList[7]).text().trim() == "Cane/Walking stick for long walks"
			$(choiceList[8]).text().trim() == "Cane/Walking stick most of the time"
			$(choiceList[9]).text().trim() == "One crutch"
			$(choiceList[10]).text().trim() == "Two Canes/Walking sticks"
			$(choiceList[11]).text().trim() == "Two crutches or not able to walk"
		}
		js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[7].click()  //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "Distance walked"
			$(choiceList[12]).text().trim() == "Unlimited"
			$(choiceList[13]).text().trim() == "Six blocks (30 minutes)"
			$(choiceList[14]).text().trim() == "Two or three blocks (10 - 15 minutes)"
			$(choiceList[15]).text().trim() == "Indoors only"
			$(choiceList[16]).text().trim() == "Bed and chair only"
		}
		js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[14].click() //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "Limp"
			$(choiceList[17]).text().trim() == "None"
			$(choiceList[18]).text().trim() == "Slight"
			$(choiceList[19]).text().trim() == "Moderate"
			$(choiceList[20]).text().trim() == "Severe or unable to walk"
		}
		js.exec("document.getElementsByClassName('answer')[20].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[20].click() //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "Activities - shoes, socks"
			$(choiceList[21]).text().trim() == "With ease"
			$(choiceList[22]).text().trim() == "With difficulty"
			$(choiceList[23]).text().trim() == "Unable to fit or tie"
		}
		js.exec("document.getElementsByClassName('answer')[22].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[22].click() //question 5 choice 2

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "Stairs"
			$(choiceList[24]).text().trim() == "Normally without using a railing"
			$(choiceList[25]).text().trim() == "Normally using a railing"
			$(choiceList[26]).text().trim() == "In any manner"
			$(choiceList[27]).text().trim() == "Unable to do stairs"
		}
		js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[24].click() //question 6 choice 1

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "Public transportation"
			$(choiceList[28]).text().trim() == "Able to use transportation (bus)"
			$(choiceList[29]).text().trim() == "Unable to use public transportation (bus)"
		}
		js.exec("document.getElementsByClassName('answer')[29].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[29].click() //question 7 choice 2

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "Sitting"
			$(choiceList[30]).text().trim() == "Comfortably, ordinary chair for one hour"
			$(choiceList[31]).text().trim() == "On a high chair for 30 minutes"
			$(choiceList[32]).text().trim() == "Unable to sit comfortably on any chair"
		}
		js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[32].click() //question 8 choice 3


		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
//			at TaskCompletePage
            at TaskIntroPage
		}
	}
    @Ignore
	def "click Harris Hip Score task email link again should direct to taskCompletePage after completing Harris Hip Score tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/Harris+Hip+Score/")
		go link

		then:
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

    @Ignore
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

    @Ignore
	def "check Harris Hip Score score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			HarrisHipScoreCompleteTaskbox.find('.score').text() == '62.0\nTotal Result'
		}
	}
}
