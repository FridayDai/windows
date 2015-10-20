package specs.patient

import groovy.json.JsonSlurper
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class NRSNECKFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

	def "start NRS-NECK immediate task successfully" () {
		when:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/NRS-NECK/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

//    @Ignore
	def "check NRS-NECK phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		then: "Type last 4 number and start to complete tasks"
		waitFor(30, 1) {
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
		waitFor(3, 1) {
			$(questionList[0]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your neck pain right now?'
		}
		choicesList[5].click()  //question 1 choice 5

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your arm pain right now?'
		}
		js.exec("jQuery('.answer').get(16).scrollIntoView(false)")
		choicesList[16].click() //question 2 choice 5

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

	//    @Ignore
	def "check NRS-NECK complete score successfully"() {
		when: "At NRS-Neck CompletePage"
		at TaskCompletePage

		then: "Close window and back to gmail"
		waitFor(3, 1) {
			$(scores[0]).text().trim() == "Neck Score: 5"
			$(scores[1]).text().trim() == "Arm Score: 5"
		}
	}

	def "check NRS-NECK immediate task email link again should direct to taskCompletePage after completing NRS-NECK tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/NRS-NECK/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at TaskCompletePage
		}

		waitFor(3, 1) {
			$(scores[0]).text().trim() == "Neck Score: 5"
			$(scores[1]).text().trim() == "Arm Score: 5"
		}
	}
}
