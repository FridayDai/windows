package specs.patient

import pages.patient.FairleyTaskPage
import pages.patient.HarrisTaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class FairleyNasalSymptomFunctionalSpec extends RatchetFunctionalSpec {
	def "complete Fairley Nasal Symptom immediate task"() {
		when:
		def fairleyTaskPage = new FairleyTaskPage()
		at fairleyTaskPage

		then:
		fairleyTaskPage.DoFairleyTasks(optionNum)

		where:
		optionNum << [0,7,14,21,28,35,40,45,50,55,60,67,74]
	}

	def "Click the DoneButton"(){
		when:
		def fairleyTaskPage = new FairleyTaskPage()
		at fairleyTaskPage

		and:
		fairleyTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at HarrisTaskPage
		}
	}

}
