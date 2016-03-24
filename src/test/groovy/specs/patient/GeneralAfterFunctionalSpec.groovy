package specs.patient

import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import specs.RatchetFunctionalSpec
import spock.lang.Stepwise

@Stepwise
class GeneralAfterFunctionalSpec extends RatchetFunctionalSpec {
	def setupSpec(){
		browser.setBaseUrl(getClientUrl())
	}

	def "should login with the activate account created by client successfully"() {

		when:
		def loginPage = new LoginPage()
		to loginPage

		and:
		loginPage.login(account.email,account.password)

		and:
		loginPage.goToPatientsPage()

		then:
		waitFor(30, 1) {
			browser.at PatientsPage
		}
	}

	def "direct to patient detail Page"(){
		when:
		def patientsPage = new PatientsPage()
		at patientsPage

		and:
		patientsPage.goToPatientDetailPage()

		then:
		waitFor(30,1){
			browser.at PatientDetailPage
		}

	}

	def "check DASH/NDI/NRSB/NRSN/QUICK score in (First Treatment) patientDetail after finish it"() {
		when:
		def patientDetailPage = new PatientDetailPage()
		at patientDetailPage

		and:
		patientDetailPage.checkDashScore()

		and:
		patientDetailPage.checkNDIScore()

		and:
		patientDetailPage.checkNRSBACKScore()

		and:
		patientDetailPage.checkNRSNECKScore()

		and:
		patientDetailPage.checkQuickDASHScore()

		then:
		waitFor(50, 1) {
			browser.at PatientDetailPage
		}
	}

//	def "check HOOS/KOOS/Harris/Fairley/ODI score in (Second Treatment) patientDetail after finish it"() {
//		when:
//		def patientDetailPage = new PatientDetailPage()
//		at patientDetailPage
//
//		and:
//		patientDetailPage.checkFairleyScore()
//
//		and:
//		patientDetailPage.checkHarrisScore()
//
//		and:
//		patientDetailPage.checkHOOSScore()
//
//		and:
//		patientDetailPage.checkKOOSScore()
//
//		and:
//		patientDetailPage.checkODIScore()
//
//		then:
//		waitFor(50, 1) {
//			browser.at PatientDetailPage
//		}
//	}


	def "archive this treatment of patient"() {
		when:
		def patientDetailPage = new PatientDetailPage()
		at patientDetailPage

		and:
		patientDetailPage.archiveFile()

		then:
		waitFor(50, 1) {
			browser.at PatientDetailPage
		}
	}

	def "archived patient and caregiver emails successfully"() {
		when:
		archivedQueryEmails(patient.firstName)
		//input PATIENT_FIRST_NAME, patient, caregiver and all task will be find.

		then:
		true
	}
}
