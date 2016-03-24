package specs.patient

import pages.patient.ODITaskPage
import pages.patient.TaskCompletePage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class ODIFunctionalSpec extends RatchetFunctionalSpec {
	def "complete ODI immediate task"() {
		when:
		def odiTaskPage = new ODITaskPage()
		at odiTaskPage

		then:
		odiTaskPage.DoODITasks(optionNum)

		where:
		optionNum << [0,7,14,21,28,35,36,43,50,57]
	}

	def "Click the DoneButton"(){
		when:
		def odiTaskPage = new ODITaskPage()
		at odiTaskPage

		and:
		odiTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at TaskCompletePage
		}
	}
}
