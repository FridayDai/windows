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
class FairleyNasalSymptomFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"
	
	static FNS_NO_PROBLEM_CHOICE = "No problem"
	static FNS_VERY_MILD_PROBLEM_CHOICE = "Very mild problem"
	static FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE = "Mild or slight problem"
	static FNS_MODERATE_PROBLEM_CHOICE = "Moderate problem"
	static FNS_SEVERE_PROBLEM_CHOICE = "Severe problem"
	static FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE = "Problem bad as it can be"
	
	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

/*	def "start Fairley Nasal Symptom immediate task successfully" () {
		when:
		TASK_LINKS =getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/Fairley+Nasal+Symptom/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

	def "check Fairley Nasal Symptom phone number successfully"() {
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

	def "complete Fairley Nasal Symptom immediate task"() {
		when: "At Fairley Nasal Symptom task page"
		waitFor(5, 1){
			at TaskIntroPage
		}

		and: "Check every question and complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "1. Is your nose blocked?"
			$(choiceList[0]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[1]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[2]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[3]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[4]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[5]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click() //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "2. Do you feel dripping at the back of your nose?"
			$(choiceList[6]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[7]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[8]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[9]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[10]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[11]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[7].click() //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "3. Does your nose run?"
			$(choiceList[12]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[13]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[14]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[15]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[16]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[17]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[14].click() //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "4. Do you get headaches?"
			$(choiceList[18]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[19]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[20]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[21]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[22]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[23]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[21].click() //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "5. Do you get pains in the face or eye?"
			$(choiceList[24]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[25]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[26]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[27]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[28]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[29]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[28].click() //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "6. Is your sense of smell reduced?"
			$(choiceList[30]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[31]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[32]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[33]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[34]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[35]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[35].click() //question 6 choice 6

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "7. Do you suffer from cough?"
			$(choiceList[36]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[37]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[38]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[39]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[40]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[41]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[40].click() //question 7 choice 5

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "8. Do you get toothache?"
			$(choiceList[42]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[43]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[44]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[45]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[46]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[47]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[45].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[45].click() //question 8 choice 4

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "9. Do you get nosebleeds?"
			$(choiceList[48]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[49]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[50]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[51]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[52]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[53]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[50].click() //question 9 choice 3

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "10. Do you sneeze often?"
			$(choiceList[54]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[55]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[56]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[57]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[58]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[59]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[55].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[55].click() //question 10 choice 2

		waitFor(3, 1) {
			$(questionList[10]).text().trim() == "11. Do you get sore throats?"
			$(choiceList[60]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[61]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[62]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[63]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[64]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[65]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[60].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[60].click() //question 11 choice 1

		waitFor(3, 1) {
			$(questionList[11]).text().trim() == "12. Do you feel generally unwell?"
			$(choiceList[66]).text().trim() == FNS_NO_PROBLEM_CHOICE
			$(choiceList[67]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
			$(choiceList[68]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
			$(choiceList[69]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
			$(choiceList[70]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
			$(choiceList[71]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[67].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[67].click() //question 12 choice 2

		waitFor(3, 1) {
			$(questionList[12]).text().trim() == "13. How many courses of antibiotics have you had in the last six months?"
			$(choiceList[72]).text().trim() == "0"
			$(choiceList[73]).text().trim() == "1"
			$(choiceList[74]).text().trim() == "2"
			$(choiceList[75]).text().trim() == "3"
			$(choiceList[76]).text().trim() == "4"
			$(choiceList[77]).text().trim() == "5"
		}
		js.exec("document.getElementsByClassName('answer')[74].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[74].click() //question 13 choice 3

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
//			at TaskCompletePage
            at TaskIntroPage
		}
	}

    @Ignore
	def "click Fairley Nasal Symptom task email link again should direct to taskCompletePage after completing Fairley Nasal Symptom tasks"() {

		when:
		def link = findFormList(TASK_LINKS, "/Fairley+Nasal+Symptom/")
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
	def "check Fairley Nasal Symptom score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			FairleyNasalSymptomCompleteTaskbox.find('.score')*.text() == ['26\nTotal Result', '2\nAntibiotics']
		}
	}
}
