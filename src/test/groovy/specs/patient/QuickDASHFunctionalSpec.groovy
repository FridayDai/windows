package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class QuickDASHFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

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
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

	def "start QuickDASH immediate task successfully" () {
		when:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/QuickDASH/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}

	}

//    @Ignore
	def "check QuickDASH phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		Thread.sleep(2000 as long)

		and: "Type last 4 number and start to complete tasks"
		phoneNumberInput << LAST_4_NUMBER
		startButton.click()

		then: "Direct to QuickDASH task page"
		waitFor(30, 1) {
			at TaskIntroPage
		}
	}

//    @Ignore
	def "complete QuickDASH immediate task"() {
		when: "At QuickDash task page"
		at TaskIntroPage

		and: "Complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "1. Open a tight or new jar."
			$(choiceList[0]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[1]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[2]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[3]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[4]).text().trim() == QUICK_DASH_UNABLE_CHOICE

		}
		js.exec("jQuery('.answer').get(0).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click()  //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "2. Do heavy household chores (e.g., wash walls, floors)."
			$(choiceList[5]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[6]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[7]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[8]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[9]).text().trim() == QUICK_DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(6).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[6].click()  //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "3. Carry a shopping bag or briefcase."
			$(choiceList[10]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[11]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[12]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[13]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[14]).text().trim() == QUICK_DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(12).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[12].click() //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "4. Wash your back."
			$(choiceList[15]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[16]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[17]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[18]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[19]).text().trim() == QUICK_DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(18).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[18].click() //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "5. Use a knife to cut food."
			$(choiceList[20]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[21]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[22]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[23]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[24]).text().trim() == QUICK_DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(24).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[24].click() //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "6. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
			$(choiceList[25]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[26]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[27]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[28]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[29]).text().trim() == QUICK_DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(28).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[28].click() //question 6 choice 4

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "7. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
			$(choiceList[30]).text().trim() == "NOT AT ALL"
			$(choiceList[31]).text().trim() == "SLIGHTLY"
			$(choiceList[32]).text().trim() == "MODERATELY"
			$(choiceList[33]).text().trim() == "QUITE A BIT"
			$(choiceList[34]).text().trim() == "EXTREMELY"
		}
		js.exec("jQuery('.answer').get(32).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[32].click() //question 7 choice 3

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "8. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
			$(choiceList[35]).text().trim() == "NOT LIMITED AT ALL"
			$(choiceList[36]).text().trim() == "SLIGHTLY LIMITED"
			$(choiceList[37]).text().trim() == "MODERATELY LIMITED"
			$(choiceList[38]).text().trim() == "VERY LIMITED"
			$(choiceList[39]).text().trim() == "UNABLE"
		}
		js.exec("jQuery('.answer').get(36).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[36].click() //question 8 choice 2

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "9. Arm, shoulder or hand pain."
			$(choiceList[40]).text().trim() == QUICK_DASH_NONE_CHOICE
			$(choiceList[41]).text().trim() == QUICK_DASH_MILD_CHOICE
			$(choiceList[42]).text().trim() == QUICK_DASH_MODERATE_CHOICE
			$(choiceList[43]).text().trim() == QUICK_DASH_SEVERE_CHOICE
			$(choiceList[44]).text().trim() == QUICK_DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(40).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[40].click() //question 9 choice 1

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "10. Tingling (pins and needles) in your arm, shoulder or hand."
			$(choiceList[45]).text().trim() == QUICK_DASH_NONE_CHOICE
			$(choiceList[46]).text().trim() == QUICK_DASH_MILD_CHOICE
			$(choiceList[47]).text().trim() == QUICK_DASH_MODERATE_CHOICE
			$(choiceList[48]).text().trim() == QUICK_DASH_SEVERE_CHOICE
			$(choiceList[49]).text().trim() == QUICK_DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(46).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[46].click() //question 10 choice 2

		waitFor(3, 1) {
			$(questionList[10]).text().trim() == "11. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand? (circle number)"
			$(choiceList[50]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[51]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[52]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[53]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[54]).text().trim() =="SO MUCH DIFFICULTY THAT I CAN'T SLEEP"
		}
		js.exec("jQuery('.answer').get(52).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[52].click() //question 11 choice 3

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

	def "click quickDash task email link again should direct to taskCompletePage after completing dash tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/QuickDASH/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at TaskCompletePage
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

	def "check QuickDASH score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			QuickDASHCompleteTaskbox.find('.score').text() == '43.18\nTotal Result'
		}
	}
}
