package specs.patient

import pages.patient.DASHTaskPage
import pages.patient.NDITaskPage
import pages.patient.NRSBACKTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class NDIFunctionalSpec extends RatchetFunctionalSpec {
	def "complete NDI immediate task"() {
		when:
		def ndiTaskPage = new NDITaskPage()
		at ndiTaskPage

		then:
		ndiTaskPage.DoNDITasks(optionNum)

		where:
		optionNum << [0,7,14,21,28,35,36,43,50,57]
	}

	def "Click the DoneButton"(){
		when:
		def ndiTaskPage = new NDITaskPage()
		at ndiTaskPage

		and:
		ndiTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at NRSBACKTaskPage
		}
	}

}
