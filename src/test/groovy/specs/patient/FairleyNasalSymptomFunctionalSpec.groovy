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

		and:
		fairleyTaskPage.checkAndDoFairleyTasks()

		then:
		waitFor(30, 1) {
			browser.at HarrisTaskPage
		}
	}

}
