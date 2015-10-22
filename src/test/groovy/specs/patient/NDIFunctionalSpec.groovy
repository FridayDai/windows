package specs.patient

import groovy.json.JsonSlurper
import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class NDIFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

	def "start NDI immediate task"() {
		given:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/NDI/")

		when:
		go link;

		then:
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

	def "start NDI immediate task successfully" () {
		when:
		def link = findFormList(TASK_LINKS, "/NDI/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}

	}

//    @Ignore
	def "check NDI phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		and: "Type last 4 number and start to complete tasks"
		phoneNumberInput << LAST_4_NUMBER
		startButton.click()

		then: "Direct to ndi task page"
		waitFor(30, 1) {
			at TaskIntroPage
		}
	}

//    @Ignore
	def "complete NDI immediate task"() {
		when: "At NDI task page"
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
		js.exec("jQuery('.answer').get(0).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click()  //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "Section 2: Personal Care (Washing, Dressing, etc.)"
			$(choiceList[6]).text().trim() == "I can look after myself normally without causing extra pain."
			$(choiceList[7]).text().trim() == "I can look after myself normally but it causes extra pain."
			$(choiceList[8]).text().trim() == "It is painful to look after myself and I am slow and careful."
			$(choiceList[9]).text().trim() == "I need some help but manage most of my personal care."
			$(choiceList[10]).text().trim() == "I need help everyday in most aspects of self care."
			$(choiceList[11]).text().trim() == "I do not get dressed, I wash with difficulty and stay in bed."
		}
		js.exec("jQuery('.answer').get(7).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[7].click()  //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "Section 3: Lifting"
			$(choiceList[12]).text().trim() == "I can lift heavy weights without extra pain."
			$(choiceList[13]).text().trim() == "I can lift heavy weights but it gives extra pain."
			$(choiceList[14]).text().trim() == "Pain prevents me from lifting heavy weights off the floor, but I can manage if they are conveniently positioned, for example on a table."
			$(choiceList[15]).text().trim() == "Pain prevents me from lifting heavy weights, but I can manage light to medium weights if they are conveniently positioned."
			$(choiceList[16]).text().trim() == "I can lift very light weights."
			$(choiceList[17]).text().trim() == "I cannot lift or carry anything at all."
		}
		js.exec("jQuery('.answer').get(14).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[14].click() //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "Section 4: Reading"
			$(choiceList[18]).text().trim() == "I can read as much as I want to with no pain in my neck."
			$(choiceList[19]).text().trim() == "I can read as much as I want to with slight pain in my neck."
			$(choiceList[20]).text().trim() == "I can read as much as I want to with moderate pain in my neck."
			$(choiceList[21]).text().trim() == "I cannot read as much as I want because of moderate pain in my neck."
			$(choiceList[22]).text().trim() == "I can hardly read at all because of severe pain in my neck."
			$(choiceList[23]).text().trim() == "I cannot read at all."
		}
		js.exec("jQuery('.answer').get(21).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[21].click() //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "Section 5: Headaches"
			$(choiceList[24]).text().trim() == "I have no headaches at all."
			$(choiceList[25]).text().trim() == "I have slight headaches that come infrequently."
			$(choiceList[26]).text().trim() == "I have moderate headaches which come infrequently."
			$(choiceList[27]).text().trim() == "I have moderate headaches which come frequently."
			$(choiceList[28]).text().trim() == "I have severe headaches which come frequently."
			$(choiceList[29]).text().trim() == "I have headaches almost all the time."
		}
		js.exec("jQuery('.answer').get(28).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[28].click() //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "Section 6: Concentration"
			$(choiceList[30]).text().trim() == "I can concentrate fully when I want to with no difficulty."
			$(choiceList[31]).text().trim() == "I can concentrate fully when I want to with slight difficulty."
			$(choiceList[32]).text().trim() == "I have a fair degree of difficulty in concentrating when I want to."
			$(choiceList[33]).text().trim() == "I have a lot of difficulty in concentrating when I want to."
			$(choiceList[34]).text().trim() == "I have a great deal of difficulty in concentrating when I want to."
			$(choiceList[35]).text().trim() == "I cannot concentrate at all."
		}
		js.exec("jQuery('.answer').get(35).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[35].click() //question 6 choice 6

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "Section 7: Work"
			$(choiceList[36]).text().trim() == "I can do as much work as I want to."
			$(choiceList[37]).text().trim() == "I can do my usual work, but no more."
			$(choiceList[38]).text().trim() == "I can do most of my usual work, but no more."
			$(choiceList[39]).text().trim() == "I cannot do my usual work."
			$(choiceList[40]).text().trim() == "I can hardly do any work at all."
			$(choiceList[41]).text().trim() == "I cannot do any work at all."
		}
		js.exec("jQuery('.answer').get(36).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[36].click() //question 7 choice 1

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "Section 8: Driving"
			$(choiceList[42]).text().trim() == "I can drive my car without any neck pain."
			$(choiceList[43]).text().trim() == "I can drive my car as long as I want with slight pain in my neck."
			$(choiceList[44]).text().trim() == "I can drive my car as long as I want with moderate pain in my neck."
			$(choiceList[45]).text().trim() == "I cannot drive my car as long as I want because of moderate pain in my neck."
			$(choiceList[46]).text().trim() == "I can hardly drive at all because of severe pain in my neck."
			$(choiceList[47]).text().trim() == "I cannot drive my car at all."
		}
		js.exec("jQuery('.answer').get(43).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[43].click() //question 8 choice 2

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "Section 9: Sleeping"
			$(choiceList[48]).text().trim() == "I have no trouble sleeping."
			$(choiceList[49]).text().trim() == "My sleep is slightly disturbed (less than 1 hour sleepless)."
			$(choiceList[50]).text().trim() == "My sleep is mildly disturbed ( 1-2 hours sleepless)."
			$(choiceList[51]).text().trim() == "My sleep is moderately disturbed ( 2-3 hours sleepless)."
			$(choiceList[52]).text().trim() == "My sleep is greatly disturbed ( 3-5 hours sleepless)."
			$(choiceList[53]).text().trim() == "My sleep is completely disturbed ( 5-7 hours sleepless)."
		}
		js.exec("jQuery('.answer').get(50).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[50].click() //question 9 choice 3

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "Section 10: Recreation"
			$(choiceList[54]).text().trim() == "I am able to engage in all my recreation activities with no neck pain at all."
			$(choiceList[55]).text().trim() == "I am able to engage in all my recreation activities, with some pain in my neck."
			$(choiceList[56]).text().trim() == "I am able to engage in most, but not all, of my usual recreation activities because of pain in my neck."
			$(choiceList[57]).text().trim() == "I am able to engage in a few of my usual recreation activities because of pain in my neck."
			$(choiceList[58]).text().trim() == "I can hardly do any recreation activities because of pain in my neck."
			$(choiceList[59]).text().trim() == "I cannot do any recreation activities at all."
		}
		js.exec("jQuery('.answer').get(57).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[57].click() //question 10 choice 4

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

//        @Ignore
	def "check NDI complete score successfully"() {
		when: "At NDICompletePage"
		at TaskCompletePage

		then: "Close window and back to gmail"
		waitFor(3, 1) {
			$(scores[0]).text().trim() == "Score: 42.0"
		}
	}

	def "click NDI task email link again should direct to taskCompletePage after completing dash tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/NDI/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at TaskCompletePage
		}

		waitFor(3, 1) {
			$(scores[0]).text().trim() == "Score: 42.0"
		}
	}
}
