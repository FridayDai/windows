package specs.patient

import pages.patient.HarrisTaskPage
import pages.patient.HoosTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class HarrisHipScoreFunctionalSpec extends RatchetFunctionalSpec {

	def "complete Harris Hip Score immediate task"() {
		when:
		def harrisTaskPage = new HarrisTaskPage()
		at harrisTaskPage

		and:
		harrisTaskPage.checkAndDoHarrisTasks()

		then:
		waitFor(30, 1) {
			browser.at HoosTaskPage
		}
	}

}
