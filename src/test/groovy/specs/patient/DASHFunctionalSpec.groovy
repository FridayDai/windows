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
class DASHFunctionalSpec extends RatchetFunctionalSpec {
//	@Shared IDENTIFY
//	@Shared PROVIDER_EMAIL
//	@Shared PROVIDER_PASSWORD
//	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${identify}"

//	def setupSpec() {
//		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
//
//		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
//		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"
//
//		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
//	}

    @Ignore
	def "start DASH immediate task"() {
		given:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/DASH/")

		when:
		go link;

		then:
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}

	}

    @Ignore
	def "check DASH phone number successfully"() {
		when: "At phone number check page"
		def phoneNumberCheckPage = new PhoneNumberCheckPage()
		at phoneNumberCheckPage

		then:
		phoneNumberCheckPage.startTask(patient)

	}

//    @Ignore
	def "complete DASH immediate task"() {
		when: "At DASH task page"
		def taskIntroPage = new TaskIntroPage()
		at taskIntroPage

		then: "Check every question and complete tasks and click done button"
		taskIntroPage.checkAndClickDASHTasks()

	}


    @Ignore
	def "click DASH task email link again should direct to taskCompletePage after completing dash tasks"() {

		when:
		def link = findFormList(TASK_LINKS, "/DASH/")
		go link

		then:
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
		//loginPage.login("875606747@qq.com","92623Daiyi")

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
	def "check DASH score in patientDetail after finish it"() {
		when:
		def patientDetailPage = new PatientDetailPage()
		at patientDetailPage

		then:
		patientDetailPage.checkDashScore()

	}
}
