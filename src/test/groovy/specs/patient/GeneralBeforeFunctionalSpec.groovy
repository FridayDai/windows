package specs.patient

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import pages.client.InClinicTaskPage
import pages.patient.EmailConfirmationPage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class GeneralBeforeFunctionalSpec extends RatchetFunctionalSpec {

	@Shared TASK_LINKS

	static RAT_COM_PATIENT_IDENTIFY = "ratchethealth.com/patient"

//    @Ignore
	def "receive and confirm patient confirmation email successfully" () {
		given:
		def link
		(link = getConfirmLink("${patient.firstName} ${RAT_COM_PATIENT_IDENTIFY}")).length() >= 1

		when:
		go link

		then:
		waitFor(30, 1) {
			at EmailConfirmationPage
		}
	}

	def "receive and confirm emergency contact confirmation email successfully"() {
		given:
		def link
		waitFor(500, 1) {
			(link = getConfirmLink(patient.emergencyFirstName)).length() >= 1
		}

		when:
		go link;

		then:
		waitFor(30, 1) {
			at EmailConfirmationPage
		}
	}

	def "receive immediate task email successfully and start DASH immediate task"() {
		when:

		def TASK_LINKS = getConfirmLink("5 Tasks from AST${identify}CN")

		Thread.sleep(2000)
		and:
		go TASK_LINKS

		then:
		waitFor(30,1){
			at InClinicTaskPage
		}

	}

    def "Go to the email task page and click the task start button "() {
        when:
		def inClinicTaskPage = new InClinicTaskPage()
		at inClinicTaskPage

		and:
		inClinicTaskPage.taskStartClick()

        then: "go to the question page"
		waitFor(30,1){
			at TaskIntroPage
		}
    }

}
