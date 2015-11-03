package specs.patient

import groovy.json.JsonSlurper
import pages.client.LoginPage
import pages.client.PatientDetailPage
import pages.client.PatientsPage
//import pages.patient.PhoneNumberCheckPage
import pages.patient.TaskCompletePage
import pages.patient.TaskIntroPage
import specs.RatchetFunctionalSpec
//import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class DASHFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS


	static LAST_4_NUMBER = "7777"

	static DASH_NO_DIFFICULTY_CHOICE = "No difficulty"
	static DASH_MID_DIFFICULTY_CHOICE = "Mild difficulty"
	static DASH_MODERATE_DIFFICULTY_CHOICE = "Moderate difficulty"
	static DASH_SEVERE_DIFFICULTY_CHOICE = "Severe difficulty"
	static DASH_UNABLE_CHOICE = "Unable"
	static DASH_NONE_CHOICE = "None"
	static DASH_MILD_CHOICE = "Mild"
	static DASH_MODERATE_CHOICE = "Moderate"
	static DASH_SEVERE_CHOICE = "Severe"
	static DASH_EXTREME_CHOICE = "Extreme"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

//    @Ignore
/*	def "start DASH immediate task"() {
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

//    @Ignore
	def "check DASH phone number successfully"() {
		when: "At phone number check page"
		at PhoneNumberCheckPage

		then: "Type last 4 number and start to complete tasks"

		repeatActionWaitFor(60, 1, {
			phoneNumberInput.value(LAST_4_NUMBER)
			startButton.click()
		}, {
			at TaskIntroPage
//		})*/
//	}

//    @Ignore
	def "complete DASH immediate task"() {
		when: "At DASH task page"
		waitFor(5, 1){
			at TaskIntroPage
		}

		and: "Check every question and complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "1. Open a tight or new jar."
			$(choiceList[0]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[1]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[2]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[3]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[4]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(0).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click() //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "2. Write."
			$(choiceList[5]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[6]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[7]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[8]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[9]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(6).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[6].click() //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "3. Turn a key."
			$(choiceList[10]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[11]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[12]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[13]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[14]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(12).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[12].click() //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "4. Prepare a meal."
			$(choiceList[15]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[16]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[17]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[18]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[19]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(18).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[18].click() //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "5. Push open a heavy door."
			$(choiceList[20]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[21]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[22]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[23]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[24]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(24).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[24].click() //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "6. Place an object on a shelf above your head."
			$(choiceList[25]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[26]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[27]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[28]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[29]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(28).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[28].click() //question 6 choice 4

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "7. Do heavy household chores (e.g., wash walls, wash floors)."
			$(choiceList[30]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[31]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[32]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[33]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[34]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(32).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[32].click() //question 7 choice 3

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "8. Garden or do yard work."
			$(choiceList[35]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[36]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[37]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[38]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[39]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(36).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[36].click() //question 8 choice 2

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "9. Make a bed."
			$(choiceList[40]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[41]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[42]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[43]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[44]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(40).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[40].click() //question 9 choice 1

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "10. Carry a shopping bag or briefcase."
			$(choiceList[45]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[46]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[47]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[48]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[49]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(46).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[46].click() //question 10 choice 2

		waitFor(3, 1) {
			$(questionList[10]).text().trim() == "11. Carry a heavy object (over 10 lbs)."
			$(choiceList[50]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[51]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[52]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[53]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[54]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(52).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[52].click() //question 11 choice 3

		waitFor(3, 1) {
			$(questionList[11]).text().trim() == "12. Change a lightbulb overhead."
			$(choiceList[55]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[56]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[57]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[58]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[59]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(58).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[58].click() //question 12 choice 4

		waitFor(3, 1) {
			$(questionList[12]).text().trim() == "13. Wash or blow dry your hair."
			$(choiceList[60]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[61]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[62]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[63]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[64]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(64).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[64].click() //question 13 choice 5

		waitFor(3, 1) {
			$(questionList[13]).text().trim() == "14. Wash your back."
			$(choiceList[65]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[66]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[67]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[68]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[69]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(68).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[68].click() //question 14 choice 4

		waitFor(3, 1) {
			$(questionList[14]).text().trim() == "15. Put on a pullover sweater."
			$(choiceList[70]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[71]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[72]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[73]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[74]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(72).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[72].click() //question 15 choice 3

		waitFor(3, 1) {
			$(questionList[15]).text().trim() == "16. Use a knife to cut food."
			$(choiceList[75]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[76]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[77]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[78]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[79]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(76).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[76].click() //question 16 choice 2

		waitFor(3, 1) {
			$(questionList[16]).text().trim() == "17. Recreational activities which require little effort (e.g., cardplaying, knitting, etc.)."
			$(choiceList[80]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[81]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[82]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[83]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[84]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(80).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[80].click() //question 17 choice 1

		waitFor(3, 1) {
			$(questionList[17]).text().trim() == "18. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
			$(choiceList[85]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[86]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[87]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[88]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[89]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(86).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[86].click() //question 18 choice 2

		waitFor(3, 1) {
			$(questionList[18]).text().trim() == "19. Recreational activities in which you move your arm freely (e.g., playing frisbee, badminton, etc.)."
			$(choiceList[90]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[91]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[92]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[93]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[94]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(92).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[92].click() //question 19 choice 3

		waitFor(3, 1) {
			$(questionList[19]).text().trim() == "20. Manage transportation needs (getting from one place to another)."
			$(choiceList[95]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[96]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[97]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[98]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[99]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(98).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[98].click() //question 20 choice 4

		waitFor(3, 1) {
			$(questionList[20]).text().trim() == "21. Sexual activities."
			$(choiceList[100]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[101]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[102]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[103]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[104]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(104).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[104].click() //question 21 choice 5

		waitFor(3, 1) {
			$(questionList[21]).text().trim() == "22. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
			$(choiceList[105]).text().trim() == "Not at all"
			$(choiceList[106]).text().trim() == "Slightly"
			$(choiceList[107]).text().trim() == "Moderately"
			$(choiceList[108]).text().trim() == "Quite a bit"
			$(choiceList[109]).text().trim() == "Extremely"
		}
		js.exec("jQuery('.answer').get(108).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[108].click() //question 22 choice 4

		waitFor(3, 1) {
			$(questionList[22]).text().trim() == "23. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
			$(choiceList[110]).text().trim() == "Not limited at all"
			$(choiceList[111]).text().trim() == "Slightly limited"
			$(choiceList[112]).text().trim() == "Moderately limited"
			$(choiceList[113]).text().trim() == "Very limited"
			$(choiceList[114]).text().trim() == DASH_UNABLE_CHOICE
		}
		js.exec("jQuery('.answer').get(112).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[112].click() //question 23 choice 3

		waitFor(3, 1) {
			$(questionList[23]).text().trim() == "24. Arm, shoulder or hand pain."
			$(choiceList[115]).text().trim() == DASH_NONE_CHOICE
			$(choiceList[116]).text().trim() == DASH_MILD_CHOICE
			$(choiceList[117]).text().trim() == DASH_MODERATE_CHOICE
			$(choiceList[118]).text().trim() == DASH_SEVERE_CHOICE
			$(choiceList[119]).text().trim() == DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(116).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[116].click() //question 24 choice 2

		waitFor(3, 1) {
			$(questionList[24]).text().trim() == "25. Arm, shoulder or hand pain when you performed any specific activity."
			$(choiceList[120]).text().trim() == DASH_NONE_CHOICE
			$(choiceList[121]).text().trim() == DASH_MILD_CHOICE
			$(choiceList[122]).text().trim() == DASH_MODERATE_CHOICE
			$(choiceList[123]).text().trim() == DASH_SEVERE_CHOICE
			$(choiceList[124]).text().trim() == DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(120).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[120].click() //question 25 choice 1

		waitFor(3, 1) {
			$(questionList[25]).text().trim() == "26. Tingling (pins and needles) in your arm, shoulder or hand."
			$(choiceList[125]).text().trim() == DASH_NONE_CHOICE
			$(choiceList[126]).text().trim() == DASH_MILD_CHOICE
			$(choiceList[127]).text().trim() == DASH_MODERATE_CHOICE
			$(choiceList[128]).text().trim() == DASH_SEVERE_CHOICE
			$(choiceList[129]).text().trim() == DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(126).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[126].click() //question 26 choice 2

		waitFor(3, 1) {
			$(questionList[26]).text().trim() == "27. Weakness in your arm, shoulder or hand."
			$(choiceList[130]).text().trim() == DASH_NONE_CHOICE
			$(choiceList[131]).text().trim() == DASH_MILD_CHOICE
			$(choiceList[132]).text().trim() == DASH_MODERATE_CHOICE
			$(choiceList[133]).text().trim() == DASH_SEVERE_CHOICE
			$(choiceList[134]).text().trim() == DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(132).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[132].click() //question 27 choice 3

		waitFor(3, 1) {
			$(questionList[27]).text().trim() == "28. Stiffness in your arm, shoulder or hand."
			$(choiceList[135]).text().trim() == DASH_NONE_CHOICE
			$(choiceList[136]).text().trim() == DASH_MILD_CHOICE
			$(choiceList[137]).text().trim() == DASH_MODERATE_CHOICE
			$(choiceList[138]).text().trim() == DASH_SEVERE_CHOICE
			$(choiceList[139]).text().trim() == DASH_EXTREME_CHOICE
		}
		js.exec("jQuery('.answer').get(138).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[138].click() //question 28 choice 4

		waitFor(3, 1) {
			$(questionList[28]).text().trim() == "29. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand?"
			$(choiceList[140]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
			$(choiceList[141]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
			$(choiceList[142]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
			$(choiceList[143]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
			$(choiceList[144]).text().trim() == "So much I can\'t sleep"
		}
		js.exec("jQuery('.answer').get(144).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[144].click() //question 29 choice 5

		waitFor(3, 1) {
			$(questionList[29]).text().trim() == "30. I feel less capable, less confident or less useful because of my arm, shoulder or hand problem."
			$(choiceList[145]).text().trim() == "Strongly disagree"
			$(choiceList[146]).text().trim() == "Disagree"
			$(choiceList[147]).text().trim() == "Neither agree nor disagree"
			$(choiceList[148]).text().trim() == "Agree"
			$(choiceList[149]).text().trim() == "Strongly agree"
		}
		js.exec("jQuery('.answer').get(148).scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[148].click() //question 30 choice 1

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}


	//    @Ignore
	def "click DASH task email link again should direct to taskCompletePage after completing dash tasks"() {

		when:
		def link = findFormList(TASK_LINKS, "/DASH/")
		go link

		then:
		waitFor(30, 1) {
			at TaskCompletePage
		}
	}

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

	def "check DASH score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			DASHCompleteTaskbox.find('.score').text().trim() == '50.83\nTotal Result'
		}
	}
}
