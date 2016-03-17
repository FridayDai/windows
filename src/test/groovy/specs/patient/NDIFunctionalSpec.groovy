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
class NDIFunctionalSpec extends RatchetFunctionalSpec {
//	@Shared IDENTIFY
//	@Shared PROVIDER_EMAIL
//	@Shared PROVIDER_PASSWORD
//	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

//	static LAST_4_NUMBER = "7777"

//	def setupSpec() {
//
//		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
//
//		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
//		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
//
//		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
//	}

//    @Ignore
	def "complete NDI immediate task"() {
		when: "At NDI task page"
		def taskIntroPage = new TaskIntroPage()
		at taskIntroPage

		then:
		taskIntroPage.checkAndClickNDITasks()

	}
    @Ignore
	def "click NDI task email link again should direct to taskCompletePage after completing dash tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/NDI/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}
    @Ignore
	def "should login with the activate account created by client successfully"() {
		browser.setBaseUrl(getClientUrl())
		when:
		def loginPage = new LoginPage()
		to loginPage

		and:
		loginPage.login(account.email,account.password)

		then:
		loginPage.goToPatientsPage()
	}

	def "direct to patient detail Page"(){
		when:
		def patientsPage = new PatientsPage()
		at patientsPage

		then:
		patientsPage.goToPatientDetailPage()

	}

    @Ignore
	def "check NDI score in patientDetail after finish it"() {
		when:
		def patientDetailPage = new PatientDetailPage()
		at patientDetailPage

		then:
		patientDetailPage.checkNDIScore()

	}
}
