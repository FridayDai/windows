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

		and:
		odiTaskPage.checkAndDoODITasks()

		then:
		waitFor(30, 1) {
			browser.at TaskCompletePage
		}
	}
}
