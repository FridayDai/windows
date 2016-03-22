package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class NRSBACKFunctionalSpec extends RatchetFunctionalSpec {

	def "complete NRS-BACK immediate task"() {
		when: "At NRS-BACK task page"
		def taskIntroPage = new TaskIntroPage()
		at taskIntroPage

		then:
		taskIntroPage.checkAndClickNRSBACKTasks()

	}
//    @Ignore
//	def "check NRS-BACK immediate task email link again should direct to taskCompletePage after completing NRS-BACK tasks"() {
//		when:
//		def link = findFormList(TASK_LINKS, "/NRS-BACK/")
//		go link
//
//		then:
//		waitFor(30, 1) {
//			at TaskCompletePage
//		}
//	}
//    @Ignore
//	def "should login with the activate account created by client successfully"() {
//		browser.setBaseUrl(getClientUrl())
//		when:
//		def loginPage = new LoginPage()
//		to loginPage
//
//		and:
//		loginPage.login(account.email,account.password)
//
//		then:
//		loginPage.goToPatientsPage()
//	}
//
//	def "direct to patient detail Page"(){
//		when:
//		def patientsPage = new PatientsPage()
//		at patientsPage
//
//		then:
//		patientsPage.goToPatientDetailPage()
//
//	}
//    @Ignore
//	def "check NRS-BACK score in patientDetail after finish it"() {
//		when:
//		def patientDetailPage = new PatientDetailPage()
//		at patientDetailPage
//
//		then:
//		patientDetailPage.checkNRSBACKScore()
//	}
}
