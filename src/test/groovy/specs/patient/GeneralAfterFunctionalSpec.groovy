package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class GeneralAfterFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
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
			moreButton.displayed
		}
		moreButton.click()

		waitFor(10, 1) {
			archivedButton.displayed
		}
		archivedButton.click()

		then: "Archived model display"
		waitFor(5, 1) {
			archivedModel.displayed
		}

		when: "Click Archieve to agree"
		waitFor(10, 1) { archivedModel.agreeButton.displayed }

		Thread.sleep(2000 as long)
		archivedModel.agreeButton.click()

		then: "Check archived treatment title"
		waitFor(50, 1) {
			at PatientDetailPage
		}
		waitFor(100, 1) {
			archivedTreatmentTitle.displayed
		}
	}

	def "archived patient and caregiver emails successfully"() {
		when:
		archivedQueryEmails(PATIENT_FIRST_NAME)
		//input PATIENT_FIRST_NAME, patient, caregiver and all task will be find.

		then:
		getAllLinks("${PATIENT_FIRST_NAME} label:inbox").size() < 1

	}
}
