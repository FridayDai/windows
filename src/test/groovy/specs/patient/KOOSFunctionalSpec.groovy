package specs.patient

import pages.patient.KoosTaskPage
import pages.patient.ODITaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class KOOSFunctionalSpec extends RatchetFunctionalSpec {

	def "complete KOOS immediate task"() {
		when:
		def koosTaskPage = new KoosTaskPage()
		at koosTaskPage

		then:
		koosTaskPage.checkAndDoKOOSTasks()

		then: "Direct to complete page"
		waitFor(30, 1) {
			browser.at ODITaskPage
		}
	}
}
