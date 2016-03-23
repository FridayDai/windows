package specs.patient

import pages.patient.DASHTaskPage
import pages.patient.NDITaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class DASHFunctionalSpec extends RatchetFunctionalSpec {
	def "complete DASH immediate task"() {
		when:
		def dashTaskPage = new DASHTaskPage()
		at dashTaskPage

		then:
		dashTaskPage.DoDASHTasks(optionNum)

		where:
		optionNum << [0,6,12,18,24,28,32,36,40,46,52,58,64,68,72,76,80,86,92,98,104,108,112
					  ,116,120,126,132,138,144,148]

	}

	def "Click the DoneButton"(){
		when:
		def dashTaskPage = new DASHTaskPage()
		at dashTaskPage

		and:
		dashTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at NDITaskPage
		}
	}

}
