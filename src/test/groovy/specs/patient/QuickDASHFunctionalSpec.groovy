package specs.patient

import pages.patient.QuickDASHTaskPage
import pages.patient.TaskCompletePage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class QuickDASHFunctionalSpec extends RatchetFunctionalSpec {

	def "complete QuickDASH immediate task"() {
		when:
		def quickDashTaskPage = new QuickDASHTaskPage()
		at quickDashTaskPage

		and:
		quickDashTaskPage.DoQuickDASHTasks(optionNum)

		where:
		optionNum << [0,6,12,18,24,28,32,36,40,46,52]
	}

	def "Click the DoneButton"() {
		when:
		def quickDashTaskPage = new QuickDASHTaskPage()
		at quickDashTaskPage

		and:
		quickDashTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at TaskCompletePage
		}
	}

}
