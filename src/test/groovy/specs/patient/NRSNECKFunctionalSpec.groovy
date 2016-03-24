package specs.patient

import pages.patient.NRSNECKTaskPage
import pages.patient.QuickDASHTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class NRSNECKFunctionalSpec extends RatchetFunctionalSpec {

	def "complete NRS-NECK immediate task"() {
		when:
		def nrsneckTaskPage = new NRSNECKTaskPage()
		at nrsneckTaskPage

		then:
		nrsneckTaskPage.DoNRSNECKTasks(optionNum)

		where:
		optionNum << [5,16]
	}

	def "Click the DoneButton"() {
		when:
		def nrsneckTaskPage = new NRSNECKTaskPage()
		at nrsneckTaskPage

		and:
		nrsneckTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at QuickDASHTaskPage
		}
	}
}
