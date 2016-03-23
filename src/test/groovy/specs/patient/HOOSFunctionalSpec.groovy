package specs.patient


import pages.patient.HoosTaskPage
import pages.patient.KoosTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class HOOSFunctionalSpec extends RatchetFunctionalSpec {

	def "complete HOOS immediate task"() {
		when:
		def hoosTaskPage = new HoosTaskPage()
		at hoosTaskPage

		then:
		hoosTaskPage.checkAndDoHOOSTasks()

		then: "Direct to complete page"
		waitFor(30, 1) {
			browser.at KoosTaskPage
		}

	}

}
