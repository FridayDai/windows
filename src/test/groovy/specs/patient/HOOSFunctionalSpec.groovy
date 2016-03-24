package specs.patient


import pages.patient.HoosTaskPage
import pages.patient.KoosTaskPage
import pages.patient.ODITaskPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class HOOSFunctionalSpec extends RatchetFunctionalSpec {

	def "complete HOOS immediate task"() {
		when:
		def hoosTaskPage = new HoosTaskPage()
		at hoosTaskPage

		then:
		hoosTaskPage.DoHoosTasks(optionNum)

		where:
		optionNum << [0,6,12,18,24,28,32,36,40,46,52,58,64,68,72,76,80,86,92,98,104,108,112,116,120,126,132,138,144,148,152,156,160,166,172,178,184,188,192,196]
	}

	def "Click the DoneButton"(){
		when:
		def hoosTaskPage = new HoosTaskPage()
		at hoosTaskPage

		and:
		hoosTaskPage.clickDone()

		then:
		waitFor(30, 1) {
			browser.at KoosTaskPage
		}
	}

}
