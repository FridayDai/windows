package specs.patient

import pages.patient.NDITaskPage
import pages.patient.NRSBACKTaskPage
import pages.patient.NRSNECKTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class NRSBACKFunctionalSpec extends RatchetFunctionalSpec {
	def "complete NRS-BACK immediate task"() {
		when:
		def nrsBackTaskPage = new NRSBACKTaskPage()
		at nrsBackTaskPage

		then:
		nrsBackTaskPage.DoNRSBACKTasks(optionNum)

		where:
		optionNum << [5, 16]
	}

	def "Click the DoneButton"() {
		when:
		def nrsBackTaskPage = new NRSBACKTaskPage()
		at nrsBackTaskPage

		and:
		nrsBackTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at NRSNECKTaskPage
		}
	}
}