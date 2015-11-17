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
class HOOSFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY
	@Shared PROVIDER_EMAIL
	@Shared PROVIDER_PASSWORD
	@Shared PATIENT_FIRST_NAME_TRANSITION
	@Shared TASK_LINKS

	static LAST_4_NUMBER = "7777"

	static HOOS_NONE_CHOICE = "None"
	static HOOS_NEVER_CHOICE = "Never"
	static HOOS_RARELY_CHOICE = "Rarely"
	static HOOS_MILD_CHOICE = "Mild"
	static HOOS_MILDLY_CHOICE = "Mildly"
	static HOOS_SOMETIMES_CHOICE = "Sometimes"
	static HOOS_MODERATE_CHOICE = "Moderate"
	static HOOS_MODERATELY_CHOICE = "Moderately"
	static HOOS_OFTEN_CHOICE = "Often"
	static HOOS_SEVERE_CHOICE = "Severe"
	static HOOS_SEVERELY_CHOICE = "Severely"
	static HOOS_ALWAYS_CHOICE = "Always"
	static HOOS_EXTREME_CHOICE = "Extreme"
	static HOOS_EXTREMELY_CHOICE = "Extremely"
	static HOOS_MONTHLY_CHOICE = "Monthly"
	static HOOS_WEEKLY_CHOICE = "Weekly"
	static HOOS_DAILY_CHOICE = "Daily"
	static HOOS_CONSTANTLY_CHOICE = "Constantly"
	static HOOS_NOT_AT_ALL_CHOICE = "Not at all"
	static HOOS_TOTALLY_CHOICE = "Totally"

	def setupSpec() {
		def APP_VAR_PATH = "src/test/resources/var.json"

		IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

		PROVIDER_EMAIL = "ratchet.testing+pro${IDENTIFY}@gmail.com"
		PROVIDER_PASSWORD = "K(mRseYHZ>v23zGt78987"

		PATIENT_FIRST_NAME_TRANSITION = "FN%2Bpat${IDENTIFY}"
	}

/*	def "start HOOS immediate task successfully" () {
		when:
		TASK_LINKS = getAllLinks("${PATIENT_FIRST_NAME_TRANSITION}/tasks/")
		def link = findFormList(TASK_LINKS, "/HOOS/")
		go link

		then: "Direct to phone number check page"
		waitFor(30, 1) {
			at PhoneNumberCheckPage
		}
	}

//    @Ignore
	def "check HOOS phone number successfully"() {
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
	def "complete HOOS immediate task"() {
		when: "At HOOS task page"
		at TaskIntroPage

		and: "Complete tasks and click done button"
		def choiceList = answerList.find('.text')
		waitFor { choiceList.displayed }

		waitFor(3, 1) {
			$(questionList[0]).text().trim() == "1. Do you feel grinding, hear clicking or any other type of noise from your hip?"
			$(choiceList[0]).text().trim() == HOOS_NEVER_CHOICE
			$(choiceList[1]).text().trim() == HOOS_RARELY_CHOICE
			$(choiceList[2]).text().trim() == HOOS_SOMETIMES_CHOICE
			$(choiceList[3]).text().trim() == HOOS_OFTEN_CHOICE
			$(choiceList[4]).text().trim() == HOOS_ALWAYS_CHOICE
		}
		js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[0].click()   //question 1 choice 1

		waitFor(3, 1) {
			$(questionList[1]).text().trim() == "2. Difficulties spreading legs wide apart"
			$(choiceList[5]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[6]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[7]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[8]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[9]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[6].click()   //question 2 choice 2

		waitFor(3, 1) {
			$(questionList[2]).text().trim() == "3. Difficulties to stride out when walking"
			$(choiceList[10]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[11]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[12]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[13]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[14]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[12].click()  //question 3 choice 3

		waitFor(3, 1) {
			$(questionList[3]).text().trim() == "4. How severe is your hip joint stiffness after first wakening in the morning?"
			$(choiceList[15]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[16]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[17]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[18]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[19]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[18].click()  //question 4 choice 4

		waitFor(3, 1) {
			$(questionList[4]).text().trim() == "5. How severe is your hip stiffness after sitting, lying or resting later in the day?"
			$(choiceList[20]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[21]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[22]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[23]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[24]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[24].click()  //question 5 choice 5

		waitFor(3, 1) {
			$(questionList[5]).text().trim() == "1. How often is your hip painful?"
			$(choiceList[25]).text().trim() == HOOS_NEVER_CHOICE
			$(choiceList[26]).text().trim() == HOOS_MONTHLY_CHOICE
			$(choiceList[27]).text().trim() == HOOS_WEEKLY_CHOICE
			$(choiceList[28]).text().trim() == HOOS_DAILY_CHOICE
			$(choiceList[29]).text().trim() == HOOS_ALWAYS_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[28].click()  //question 1 of pain choice 4

		waitFor(3, 1) {
			$(questionList[6]).text().trim() == "2. Straightening your hip fully"
			$(choiceList[30]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[31]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[32]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[33]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[34]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[32].click()  //question 2 of pain choice 3

		waitFor(3, 1) {
			$(questionList[7]).text().trim() == "3. Bending your hip fully"
			$(choiceList[35]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[36]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[37]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[38]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[39]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[36].click()  //question 3 of pain choice 2

		waitFor(3, 1) {
			$(questionList[8]).text().trim() == "4. Walking on a flat surface"
			$(choiceList[40]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[41]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[42]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[43]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[44]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[40].click()  //question 4 of pain choice 1

		waitFor(3, 1) {
			$(questionList[9]).text().trim() == "5. Going up or down stairs"
			$(choiceList[45]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[46]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[47]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[48]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[49]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[46].click()  //question 5 of pain choice 2

		waitFor(3, 1) {
			$(questionList[10]).text().trim() == "6. At night while in bed"
			$(choiceList[50]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[51]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[52]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[53]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[54]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[52].click()  //question 6 of pain choice 3

		waitFor(3, 1) {
			$(questionList[11]).text().trim() == "7. Sitting or lying"
			$(choiceList[55]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[56]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[57]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[58]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[59]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[58].click()  //question 7 of pain choice 4

		waitFor(3, 1) {
			$(questionList[12]).text().trim() == "8. Standing upright"
			$(choiceList[60]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[61]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[62]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[63]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[64]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[64].click()  //question 8 of pain choice 5

		waitFor(3, 1) {
			$(questionList[13]).text().trim() == "9. Walking on a hard surface (asphalt, concrete, etc.)"
			$(choiceList[65]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[66]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[67]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[68]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[69]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[68].click()  //question 9 of pain choice 4

		waitFor(3, 1) {
			$(questionList[14]).text().trim() == "10. Walking on an uneven surface"
			$(choiceList[70]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[71]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[72]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[73]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[74]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[72].click()  //question 10 of pain choice 3

		waitFor(3, 1) {
			$(questionList[15]).text().trim() == "1. Descending stairs"
			$(choiceList[75]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[76]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[77]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[78]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[79]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")

        Thread.sleep(500 as long)
		choicesList[76].click()  //question 1 of function choice 2

		waitFor(3, 1) {
			$(questionList[16]).text().trim() == "2. Ascending stairs"
			$(choiceList[80]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[81]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[82]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[83]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[84]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[80].click()  //question 2 of function choice 1

		waitFor(3, 1) {
			$(questionList[17]).text().trim() == "3. Rising from sitting"
			$(choiceList[85]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[86]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[87]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[88]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[89]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[86].click()  //question 3 of function choice 2

		waitFor(3, 1) {
			$(questionList[18]).text().trim() == "4. Standing"
			$(choiceList[90]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[91]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[92]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[93]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[94]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[92].click()  //question 4 of function choice 3

		waitFor(3, 1) {
			$(questionList[19]).text().trim() == "5. Bending to the floor/pick up an object"
			$(choiceList[95]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[96]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[97]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[98]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[99]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[98].click()  //question 5 of function choice 4

		waitFor(3, 1) {
			$(questionList[20]).text().trim() == "6. Walking on a flat surface"
			$(choiceList[100]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[101]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[102]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[103]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[104]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[104].click()  //question 6 of function choice 5

		waitFor(3, 1) {
			$(questionList[21]).text().trim() == "7. Getting in/out of car"
			$(choiceList[105]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[106]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[107]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[108]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[109]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[108].click()  //question 7 of function choice 4

		waitFor(3, 1) {
			$(questionList[22]).text().trim() == "8. Going shopping"
			$(choiceList[110]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[111]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[112]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[113]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[114]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[112].click()  //question 8 of function choice 3

		waitFor(3, 1) {
			$(questionList[23]).text().trim() == "9. Putting on socks/stockings"
			$(choiceList[115]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[116]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[117]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[118]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[119]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[116].click()  //question 9 of function choice 2

		waitFor(3, 1) {
			$(questionList[24]).text().trim() == "10. Rising from bed"
			$(choiceList[120]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[121]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[122]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[123]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[124]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[120].click()  //question 10 of function choice 1

		waitFor(3, 1) {
			$(questionList[25]).text().trim() == "11. Taking off socks/stockings"
			$(choiceList[125]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[126]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[127]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[128]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[129]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[126].click()  //question 11 of function choice 2

		waitFor(3, 1) {
			$(questionList[26]).text().trim() == "12. Lying in bed (turning over, maintaining hip position)"
			$(choiceList[130]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[131]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[132]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[133]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[134]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[132].click()  //question 12 of function choice 3

		waitFor(3, 1) {
			$(questionList[27]).text().trim() == "13. Getting in/out of bath"
			$(choiceList[135]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[136]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[137]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[138]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[139]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[138].click()  //question 13 of function choice 4

		waitFor(3, 1) {
			$(questionList[28]).text().trim() == "14. Sitting"
			$(choiceList[140]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[141]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[142]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[143]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[144]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[144].click()  //question 14 of function choice 5

		waitFor(3, 1) {
			$(questionList[29]).text().trim() == "15. Getting on/off toilet"
			$(choiceList[145]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[146]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[147]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[148]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[149]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[148].click()  //question 15 of function choice 4

		waitFor(3, 1) {
			$(questionList[30]).text().trim() == "16. Heavy domestic duties (moving heavy boxes, scrubbing floors, etc)"
			$(choiceList[150]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[151]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[152]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[153]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[154]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[152].scrollIntoView(false)")
		Thread.sleep(500 as long)
		choicesList[152].click()  //question 16 of function choice 3

		waitFor(3, 1) {
			$(questionList[31]).text().trim() == "17. Light domestic duties (cooking, dusting, etc)"
			$(choiceList[155]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[156]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[157]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[158]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[159]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[156].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[156].click()  //question 17 of function choice 2

		waitFor(3, 1) {
			$(questionList[32]).text().trim() == "1. Squatting"
			$(choiceList[160]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[161]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[162]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[163]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[164]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[160].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[160].click()  //question 1 of function, sports choice 1

		waitFor(3, 1) {
			$(questionList[33]).text().trim() == "2. Running"
			$(choiceList[165]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[166]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[167]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[168]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[169]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[166].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[166].click()  //question 2 of function, sports choice 2

		waitFor(3, 1) {
			$(questionList[34]).text().trim() == "3. Twisting/pivoting on loaded leg"
			$(choiceList[170]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[171]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[172]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[173]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[174]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[172].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[172].click()  //question 3 of function, sports choice 3

		waitFor(3, 1) {
			$(questionList[35]).text().trim() == "4. Walking on uneven surface"
			$(choiceList[175]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[176]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[177]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[178]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[179]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[178].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[178].click()  //question 4 of function, sports choice 4

		waitFor(3, 1) {
			$(questionList[36]).text().trim() == "1. How often are you aware of your hip problem?"
			$(choiceList[180]).text().trim() == HOOS_NEVER_CHOICE
			$(choiceList[181]).text().trim() == HOOS_MONTHLY_CHOICE
			$(choiceList[182]).text().trim() == HOOS_WEEKLY_CHOICE
			$(choiceList[183]).text().trim() == HOOS_DAILY_CHOICE
			$(choiceList[184]).text().trim() == HOOS_CONSTANTLY_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[184].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[184].click()  //question 1 of quality of life choice 5

		waitFor(3, 1) {
			$(questionList[37]).text().trim() == "2. Have you modified your life style to avoid activities potentially damaging to your hip?"
			$(choiceList[185]).text().trim() == HOOS_NOT_AT_ALL_CHOICE
			$(choiceList[186]).text().trim() == HOOS_MILDLY_CHOICE
			$(choiceList[187]).text().trim() == HOOS_MODERATELY_CHOICE
			$(choiceList[188]).text().trim() == HOOS_SEVERELY_CHOICE
			$(choiceList[189]).text().trim() == HOOS_TOTALLY_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[188].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[188].click()  //question 2 of quality of life choice 4

		waitFor(3, 1) {
			$(questionList[38]).text().trim() == "3. How much are you troubled with lack of confidence in your hip?"
			$(choiceList[190]).text().trim() == HOOS_NOT_AT_ALL_CHOICE
			$(choiceList[191]).text().trim() == HOOS_MILDLY_CHOICE
			$(choiceList[192]).text().trim() == HOOS_MODERATELY_CHOICE
			$(choiceList[193]).text().trim() == HOOS_SEVERELY_CHOICE
			$(choiceList[194]).text().trim() == HOOS_EXTREMELY_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[192].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[192].click()  //question 3 of quality of life choice 3

		waitFor(3, 1) {
			$(questionList[39]).text().trim() == "4. In general, how much difficulty do you have with your hip?"
			$(choiceList[195]).text().trim() == HOOS_NONE_CHOICE
			$(choiceList[196]).text().trim() == HOOS_MILD_CHOICE
			$(choiceList[197]).text().trim() == HOOS_MODERATE_CHOICE
			$(choiceList[198]).text().trim() == HOOS_SEVERE_CHOICE
			$(choiceList[199]).text().trim() == HOOS_EXTREME_CHOICE
		}
        js.exec("document.getElementsByClassName('answer')[196].scrollIntoView(false)")
        Thread.sleep(500 as long)
		choicesList[196].click()  //question 4 of quality of life choice 2

		doneButton.click()

		then: "Direct to complete page"
		waitFor(30, 1) {
//			at TaskCompletePage
            at TaskIntroPage
		}
	}
    @Ignore
	def "check HOOS immediate task email link again should direct to taskCompletePage after completing HOOS tasks"() {
		when:
		def link = findFormList(TASK_LINKS, "/HOOS/")
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
	def "check HOOS score in patientDetail after finish it"() {
		when: "Click first line of table"
		firstLine.click()

		then: "Direct to account detail page"
		waitFor(30, 1) {
			at PatientDetailPage
		}

		waitFor(30, 1) {
			HOOSCompleteTaskbox.find('.score')*.text() == [
															'50\nSymptoms',
															'48\nPain',
															'51\nADL',
															'63\nSport/Rec',
															'38\nQOL'
														]
		}
	}
}
