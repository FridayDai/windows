package specs.patient

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import pages.patient.EmailConfirmationPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class GeneralBeforeFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PATIENT_FIRST_NAME
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared CAREGIVER_FIRST_NAME
	@Shared TASK_LINKS

	static RAT_COM_PATIENT_IDENTIFY = "ratchethealth.com/patient"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PATIENT_FIRST_NAME = "FN+pat${IDENTIFY}"
		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"

		CAREGIVER_FIRST_NAME = "FN+car${IDENTIFY}"
	}

	def "receive and confirm patient confirmation email successfully" () {
		given:
		def link
		waitFor(500, 1) {
			(link = getConfirmLink("${PATIENT_FIRST_NAME} ${RAT_COM_PATIENT_IDENTIFY}")).length() >= 1
		}

		when:
		go link;

		then:
		waitFor(30, 1) {
			at EmailConfirmationPage
		}

	}

	def "receive and confirm emergency contact confirmation email successfully"() {
		given:
		def link
		waitFor(500, 1) {
			(link = getConfirmLink(CAREGIVER_FIRST_NAME)).length() >= 1
		}

		when:
		go link;

		then:
		waitFor(30, 1) {
			at EmailConfirmationPage
		}
	}

	def "receive 6 kinds immediate task email successfully and start DASH immediate task"() {
		when:
		waitFor(300, 1) {
			(TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")).size() >= 9
		}

		and: "Save task links into src/resources/var.json"
		def APP_VAR_PATH = "src/test/resources/var.json"

		new File(APP_VAR_PATH).write(
			new JsonBuilder([
				"IDENTIFY": IDENTIFY,
				"TASK_LINKS": TASK_LINKS
			]).toPrettyString()
		)

		then:
		TASK_LINKS
	}
}
