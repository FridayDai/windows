package specs.patient

import pages.patient.HarrisTaskPage
import pages.patient.HoosTaskPage
import pages.patient.KoosTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class HarrisHipScoreFunctionalSpec extends RatchetFunctionalSpec {

	def "complete Harris Hip Score immediate task"() {
		when:
		def harrisTaskPage = new HarrisTaskPage()
		at harrisTaskPage

		then:
		harrisTaskPage.DoHarrisTasks(optionNum)

		where:
		optionNum << [0,7,14,20,22,24,29,32]
	}

	def "Click the DoneButton"(){
		when:
		def harrisTaskPage = new HarrisTaskPage()
		at harrisTaskPage

		and:
		harrisTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at HoosTaskPage
		}
	}

}
