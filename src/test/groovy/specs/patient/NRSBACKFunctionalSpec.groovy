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
class NRSBACKFunctionalSpec extends RatchetFunctionalSpec {
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

	def "start NRS-BACK immediate task successfully" () {
		when:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/NRS-BACK/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

//    @Ignore
	def "check NRS-BACK phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		Thread.sleep(2000 as long)

		and: "Type last 4 number and start to complete tasks"
		phoneNumberInput << LAST_4_NUMBER
		startButton.click()

		then: "Direct to HOOS task page"
		waitFor(30, 1) {
			at TaskIntroPage
		}
	}

//    @Ignore
	def "complete NRS-BACK immediate task"() {
		when: "At NRS-BACK task page"
		at TaskIntroPage

		and: "Complete tasks and click done button"
		waitFor(3, 1) {
			$(questionList[0]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your back pain right now?'
		}
		js.exec("jQuery('.answer').get(5).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[5].click()  //question 1 choice 5

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your leg pain right now?'
		}
		js.exec("jQuery('.answer').get(16).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[16].click() //question 2 choice 5

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

	def "check NRS-BACK immediate task email link again should direct to taskCompletePage after completing NRS-BACK tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/NRS-BACK/")
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

	def "check NRS-BACK score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			NRSBackCompleteTaskbox.find('.score')*.text() == ['5\nBack Result',	'5\nLeg Result']
		}
	}
}
