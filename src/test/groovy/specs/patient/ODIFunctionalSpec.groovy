package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
import pages.patient.EnterEmailPage
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class ODIFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

	def setupSpec() {
		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

/*	def "start ODI immediate task successfully" () {
		when:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/ODI/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

//    @Ignore
	def "check ODI phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		then: "Type last 4 number and start to complete tasks"

		repeatActionWaitFor(60, 1, {
			phoneNumberInput.value(LAST_4_NUMBER)
			startButton.click()
		}, {
			at TaskIntroPage
		})
	}*/

//    @Ignore
	def "complete ODI immediate task"() {
		when: "At ODI task page"
		at TaskIntroPage

		and: "Complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "Section 1: Pain Intensity"
			$(choiceList[0]).text().trim() == "I have no pain at the moment."
			$(choiceList[1]).text().trim() == "The pain is very mild at the moment."
			$(choiceList[2]).text().trim() == "The pain is moderate at the moment."
			$(choiceList[3]).text().trim() == "The pain is fairly severe at the moment."
			$(choiceList[4]).text().trim() == "The pain is very severe at the moment."
			$(choiceList[5]).text().trim() == "The pain is the worst imaginable at the moment."
		}
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[0].click()   //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "Section 2: Personal Care (washing, dressing, etc.)"
			$(choiceList[6]).text().trim() == "I can look after myself normally without causing additional pain."
			$(choiceList[7]).text().trim() == "I can look after myself normally but it is very painful."
			$(choiceList[8]).text().trim() == "It is painful to look after myself and I am slow and careful."
			$(choiceList[9]).text().trim() == "I need some help but manage most of my personal care."
			$(choiceList[10]).text().trim() == "I need help every day in most aspects of my personal care."
			$(choiceList[11]).text().trim() == "I do not get dressed, I wash with difficulty and stay in bed."
		}
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[7].click()   //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "Section 3: Lifting"
			$(choiceList[12]).text().trim() == "I can lift heavy weights without additional pain."
			$(choiceList[13]).text().trim() == "I can lift heavy weights but it gives me additional pain."
			$(choiceList[14]).text().trim() == "Pain prevents me from lifting heavy weights off the floor but I can manage if they are conveniently positioned, e.g. on a table."
			$(choiceList[15]).text().trim() == "Pain prevents me from lifting heavy weights but I can manage light to medium weights if they are conveniently positioned."
			$(choiceList[16]).text().trim() == "I can only lift very light weights."
			$(choiceList[17]).text().trim() == "I cannot lift or carry anything at all."
		}
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[14].click()  //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "Section 4: Walking"
			$(choiceList[18]).text().trim() == "Pain does not prevent me from walking any distance."
			$(choiceList[19]).text().trim() == "Pain prevents me from walking more than one mile."
			$(choiceList[20]).text().trim() == "Pain prevents me from walking more than a quarter of a mile."
			$(choiceList[21]).text().trim() == "Pain prevents me from walking more than 100 yards."
			$(choiceList[22]).text().trim() == "I can only walk using a cane or crutches."
			$(choiceList[23]).text().trim() == "I am in bed most of the time and have to crawl to the toilet."
		}
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[21].click()  //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "Section 5: Sitting"
			$(choiceList[24]).text().trim() == "I can sit in any chair as long as I like."
			$(choiceList[25]).text().trim() == "I can sit in my favorite chair as long as I like."
			$(choiceList[26]).text().trim() == "Pain prevents me from sitting for more than 1 hour."
			$(choiceList[27]).text().trim() == "Pain prevents me from sitting for more than half an hour."
			$(choiceList[28]).text().trim() == "Pain prevents me from sitting for more than 10 minutes."
			$(choiceList[29]).text().trim() == "Pain prevents me from sitting at all."
		}
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[28].click()  //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "Section 6: Standing"
			$(choiceList[30]).text().trim() == "I can stand as long as I want without additional pain."
			$(choiceList[31]).text().trim() == "I can stand as long as I want but it gives me additional pain."
			$(choiceList[32]).text().trim() == "Pain prevents me from standing for more than 1 hour."
			$(choiceList[33]).text().trim() == "Pain prevents me from standing for more than half an hour."
			$(choiceList[34]).text().trim() == "Pain prevents me from standing for more than 10 minutes."
			$(choiceList[35]).text().trim() == "Pain prevents me from standing at all."
		}
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[35].click()  //question 6 choice 6

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "Section 7: Sleeping"
			$(choiceList[36]).text().trim() == "My sleep is never interrupted by pain."
			$(choiceList[37]).text().trim() == "My sleep is occasionally interrupted by pain."
			$(choiceList[38]).text().trim() == "Because of pain I have less than 6 hours sleep."
			$(choiceList[39]).text().trim() == "Because of pain I have less than 4 hours sleep."
			$(choiceList[40]).text().trim() == "Because of pain I have less than 2 hours sleep."
			$(choiceList[41]).text().trim() == "Pain prevents me from sleeping at all."
		}
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[36].click()  //question 7 choice 1

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "Section 8: Sex Life (if applicable)"
			$(choiceList[42]).text().trim() == "My sex life is normal and causes no additional pain."
			$(choiceList[43]).text().trim() == "My sex life is normal but causes some additional pain."
			$(choiceList[44]).text().trim() == "My sex life is nearly normal but is very painful."
			$(choiceList[45]).text().trim() == "My sex life is severely restricted by pain."
			$(choiceList[46]).text().trim() == "My sex life is nearly non existent because of pain."
			$(choiceList[47]).text().trim() == "Pain prevents me from having any sex life at all."
		}
        js.exec("document.getElementsByClassName('answer')[43].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[43].click()  //question 8 choice 2

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "Section 9: Social Life"
			$(choiceList[48]).text().trim() == "My social life is normal and causes me no additional pain."
			$(choiceList[49]).text().trim() == "My social life is normal but increases the degree of pain."
			$(choiceList[50]).text().trim() == "Pain has no significant effect on my social life apart from limiting my more energetic interests, e.g. sport, etc."
			$(choiceList[51]).text().trim() == "Pain has restricted my social life and I do not go out as often."
			$(choiceList[52]).text().trim() == "Pain has restricted my social life to home."
			$(choiceList[53]).text().trim() == "I have no social life because of pain."
		}
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[50].click()  //question 9 choice 3

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "Section 10: Traveling"
			$(choiceList[54]).text().trim() == "I can travel anywhere without pain."
			$(choiceList[55]).text().trim() == "I can travel anywhere but it gives me additional pain."
			$(choiceList[56]).text().trim() == "Pain is bad but I am able to manage trips over two hours."
			$(choiceList[57]).text().trim() == "Pain restricts me to trips of less than one hour."
			$(choiceList[58]).text().trim() == "Pain restricts me to short necessary trips of under 30 minutes."
			$(choiceList[59]).text().trim() == "Pain prevents me from traveling except to receive treatment."
		}
        js.exec("document.getElementsByClassName('answer')[57].scrollIntoView(false)")

		Thread.sleep(500 as long)
		choicesList[57].click()  //question 10 choice 4

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
            at EnterEmailPage
//			at TaskCompletePage
//            at TaskIntroPage
		}
	}
    @Ignore
	def "check ODI immediate task email link again should direct to taskCompletePage after completing ODI tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/ODI/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}
    @Ignore
	def "should login with the activate account created by client successfully"() {
		browser.setBaseUrl(getClientUrl())
		when: "At login page"
		to LoginPage

		and: "Wait for email input to displayed"
		waitFor(30, 1) { emailInput.displayed }

		and: "Type in provider email and password"
		emailInput.value('')
		emailInput << PROVIDER_EMAIL
		passwordInput << PROVIDER_PASSWORD

		and: "Click login button"
		loginButton.click()

		then: "Direct to patients page"
		waitFor(30, 1) {
			at PatientsPage
		}
	}
    @Ignore
	def "check ODI score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			ODICompleteTaskbox.find('.score').text() == '42.0\nTotal Result'
		}
	}
}
