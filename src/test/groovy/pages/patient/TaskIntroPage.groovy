package pages.patient

import geb.Page

class TaskIntroPage extends Page {

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

    static QUICK_DASH_NO_DIFFICULTY_CHOICE = "NO DIFFICULTY"
    static QUICK_DASH_MID_DIFFICULTY_CHOICE = "MILD DIFFICULTY"
    static QUICK_DASH_MODERATE_DIFFICULTY_CHOICE = "MODERATE DIFFICULTY"
    static QUICK_DASH_SEVERE_DIFFICULTY_CHOICE = "SEVERE DIFFICULTY"
    static QUICK_DASH_UNABLE_CHOICE = "UNABLE"

    static QUICK_DASH_NONE_CHOICE = "NONE"
    static QUICK_DASH_MILD_CHOICE = "MILD"
    static QUICK_DASH_MODERATE_CHOICE = "MODERATE"
    static QUICK_DASH_SEVERE_CHOICE = "SEVERE"
    static QUICK_DASH_EXTREME_CHOICE = "EXTREME"

    static FNS_NO_PROBLEM_CHOICE = "No problem"
    static FNS_VERY_MILD_PROBLEM_CHOICE = "Very mild problem"
    static FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE = "Mild or slight problem"
    static FNS_MODERATE_PROBLEM_CHOICE = "Moderate problem"
    static FNS_SEVERE_PROBLEM_CHOICE = "Severe problem"
    static FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE = "Problem bad as it can be"

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

    static KOOS_NONE_CHOICE = "None"
    static KOOS_NEVER_CHOICE = "Never"
    static KOOS_RARELY_CHOICE = "Rarely"
    static KOOS_MILD_CHOICE = "Mild"
    static KOOS_MILDLY_CHOICE = "Mildly"
    static KOOS_SOMETIMES_CHOICE = "Sometimes"
    static KOOS_MODERATE_CHOICE = "Moderate"
    static KOOS_MODERATELY_CHOICE = "Moderately"
    static KOOS_OFTEN_CHOICE = "Often"
    static KOOS_SEVERE_CHOICE = "Severe"
    static KOOS_SEVERELY_CHOICE = "Severely"
    static KOOS_ALWAYS_CHOICE = "Always"
    static KOOS_EXTREME_CHOICE = "Extreme"
    static KOOS_EXTREMELY_CHOICE = "Extremely"
    static KOOS_MONTHLY_CHOICE = "Monthly"
    static KOOS_WEEKLY_CHOICE = "Weekly"
    static KOOS_DAILY_CHOICE = "Daily"
    static KOOS_CONSTANTLY_CHOICE = "Constantly"
    static KOOS_NOT_AT_ALL_CHOICE = "Not at all"
    static KOOS_TOTALLY_CHOICE = "Totally"


    static at = { $(".task-done-btn").value() == "I'm Done" }

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
    }

    def checkAndClickDASHTasks(){
        def choiceList = answerList.find('.text')
        when:
        waitFor(30,1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "1. Open a tight or new jar."
            $(choiceList[0]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[1]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[2]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[3]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[4]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click() //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "2. Write."
            $(choiceList[5]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[6]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[7]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[8]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[9]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[6].click() //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "3. Turn a key."
            $(choiceList[10]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[11]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[12]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[13]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[14]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "4. Prepare a meal."
            $(choiceList[15]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[16]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[17]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[18]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[19]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "5. Push open a heavy door."
            $(choiceList[20]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[21]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[22]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[23]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[24]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "6. Place an object on a shelf above your head."
            $(choiceList[25]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[26]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[27]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[28]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[29]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 6 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "7. Do heavy household chores (e.g., wash walls, wash floors)."
            $(choiceList[30]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[31]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[32]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[33]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[34]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click() //question 7 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "8. Garden or do yard work."
            $(choiceList[35]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[36]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[37]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[38]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[39]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 8 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "9. Make a bed."
            $(choiceList[40]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[41]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[42]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[43]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[44]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 9 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "10. Carry a shopping bag or briefcase."
            $(choiceList[45]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[46]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[47]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[48]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[49]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[10]).text().trim() == "11. Carry a heavy object (over 10 lbs)."
            $(choiceList[50]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[51]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[52]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[53]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[54]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[52].click() //question 11 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[11]).text().trim() == "12. Change a lightbulb overhead."
            $(choiceList[55]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[56]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[57]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[58]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[59]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[58].click() //question 12 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[12]).text().trim() == "13. Wash or blow dry your hair."
            $(choiceList[60]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[61]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[62]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[63]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[64]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[64].click() //question 13 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[13]).text().trim() == "14. Wash your back."
            $(choiceList[65]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[66]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[67]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[68]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[69]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[68].click() //question 14 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[14]).text().trim() == "15. Put on a pullover sweater."
            $(choiceList[70]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[71]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[72]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[73]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[74]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[72].click() //question 15 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[15]).text().trim() == "16. Use a knife to cut food."
            $(choiceList[75]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[76]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[77]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[78]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[79]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[76].click() //question 16 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[16]).text().trim() == "17. Recreational activities which require little effort (e.g., cardplaying, knitting, etc.)."
            $(choiceList[80]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[81]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[82]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[83]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[84]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[80].click() //question 17 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[17]).text().trim() == "18. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
            $(choiceList[85]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[86]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[87]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[88]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[89]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[86].click() //question 18 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[18]).text().trim() == "19. Recreational activities in which you move your arm freely (e.g., playing frisbee, badminton, etc.)."
            $(choiceList[90]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[91]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[92]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[93]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[94]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[92].click() //question 19 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[19]).text().trim() == "20. Manage transportation needs (getting from one place to another)."
            $(choiceList[95]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[96]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[97]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[98]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[99]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[98].click() //question 20 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[20]).text().trim() == "21. Sexual activities."
            $(choiceList[100]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[101]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[102]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[103]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[104]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[104].click() //question 21 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[21]).text().trim() == "22. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
            $(choiceList[105]).text().trim() == "Not at all"
            $(choiceList[106]).text().trim() == "Slightly"
            $(choiceList[107]).text().trim() == "Moderately"
            $(choiceList[108]).text().trim() == "Quite a bit"
            $(choiceList[109]).text().trim() == "Extremely"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[108].click() //question 22 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[22]).text().trim() == "23. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
            $(choiceList[110]).text().trim() == "Not limited at all"
            $(choiceList[111]).text().trim() == "Slightly limited"
            $(choiceList[112]).text().trim() == "Moderately limited"
            $(choiceList[113]).text().trim() == "Very limited"
            $(choiceList[114]).text().trim() == DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[112].click() //question 23 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[23]).text().trim() == "24. Arm, shoulder or hand pain."
            $(choiceList[115]).text().trim() == DASH_NONE_CHOICE
            $(choiceList[116]).text().trim() == DASH_MILD_CHOICE
            $(choiceList[117]).text().trim() == DASH_MODERATE_CHOICE
            $(choiceList[118]).text().trim() == DASH_SEVERE_CHOICE
            $(choiceList[119]).text().trim() == DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[116].click() //question 24 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[24]).text().trim() == "25. Arm, shoulder or hand pain when you performed any specific activity."
            $(choiceList[120]).text().trim() == DASH_NONE_CHOICE
            $(choiceList[121]).text().trim() == DASH_MILD_CHOICE
            $(choiceList[122]).text().trim() == DASH_MODERATE_CHOICE
            $(choiceList[123]).text().trim() == DASH_SEVERE_CHOICE
            $(choiceList[124]).text().trim() == DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[120].click() //question 25 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[25]).text().trim() == "26. Tingling (pins and needles) in your arm, shoulder or hand."
            $(choiceList[125]).text().trim() == DASH_NONE_CHOICE
            $(choiceList[126]).text().trim() == DASH_MILD_CHOICE
            $(choiceList[127]).text().trim() == DASH_MODERATE_CHOICE
            $(choiceList[128]).text().trim() == DASH_SEVERE_CHOICE
            $(choiceList[129]).text().trim() == DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[126].click() //question 26 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[26]).text().trim() == "27. Weakness in your arm, shoulder or hand."
            $(choiceList[130]).text().trim() == DASH_NONE_CHOICE
            $(choiceList[131]).text().trim() == DASH_MILD_CHOICE
            $(choiceList[132]).text().trim() == DASH_MODERATE_CHOICE
            $(choiceList[133]).text().trim() == DASH_SEVERE_CHOICE
            $(choiceList[134]).text().trim() == DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[132].click() //question 27 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[27]).text().trim() == "28. Stiffness in your arm, shoulder or hand."
            $(choiceList[135]).text().trim() == DASH_NONE_CHOICE
            $(choiceList[136]).text().trim() == DASH_MILD_CHOICE
            $(choiceList[137]).text().trim() == DASH_MODERATE_CHOICE
            $(choiceList[138]).text().trim() == DASH_SEVERE_CHOICE
            $(choiceList[139]).text().trim() == DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[138].click() //question 28 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[28]).text().trim() == "29. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand?"
            $(choiceList[140]).text().trim() == DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[141]).text().trim() == DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[142]).text().trim() == DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[143]).text().trim() == DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[144]).text().trim() == "So much I can\'t sleep"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[144].click() //question 29 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[29]).text().trim() == "30. I feel less capable, less confident or less useful because of my arm, shoulder or hand problem."
            $(choiceList[145]).text().trim() == "Strongly disagree"
            $(choiceList[146]).text().trim() == "Disagree"
            $(choiceList[147]).text().trim() == "Neither agree nor disagree"
            $(choiceList[148]).text().trim() == "Agree"
            $(choiceList[149]).text().trim() == "Strongly agree"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[148].click() //question 30 choice 1

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            //browser.at TaskIntroPage
        }
    }

    def checkAndClickNDITasks(){

        def choiceList = answerList.find('.text')
        when:
        waitFor(30,1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "Section 1: Pain Intensity"
            $(choiceList[0]).text().trim() == "I have no pain at the moment."
            $(choiceList[1]).text().trim() == "The pain is very mild at the moment."
            $(choiceList[2]).text().trim() == "The pain is moderate at the moment."
            $(choiceList[3]).text().trim() == "The pain is fairly severe at the moment."
            $(choiceList[4]).text().trim() == "The pain is very severe at the moment."
            $(choiceList[5]).text().trim() == "The pain is the worst imaginable at the moment."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "Section 2: Personal Care (Washing, Dressing, etc.)"
            $(choiceList[6]).text().trim() == "I can look after myself normally without causing extra pain."
            $(choiceList[7]).text().trim() == "I can look after myself normally but it causes extra pain."
            $(choiceList[8]).text().trim() == "It is painful to look after myself and I am slow and careful."
            $(choiceList[9]).text().trim() == "I need some help but manage most of my personal care."
            $(choiceList[10]).text().trim() == "I need help everyday in most aspects of self care."
            $(choiceList[11]).text().trim() == "I do not get dressed, I wash with difficulty and stay in bed."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "Section 3: Lifting"
            $(choiceList[12]).text().trim() == "I can lift heavy weights without extra pain."
            $(choiceList[13]).text().trim() == "I can lift heavy weights but it gives extra pain."
            $(choiceList[14]).text().trim() == "Pain prevents me from lifting heavy weights off the floor, but I can manage if they are conveniently positioned, for example on a table."
            $(choiceList[15]).text().trim() == "Pain prevents me from lifting heavy weights, but I can manage light to medium weights if they are conveniently positioned."
            $(choiceList[16]).text().trim() == "I can lift very light weights."
            $(choiceList[17]).text().trim() == "I cannot lift or carry anything at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "Section 4: Reading"
            $(choiceList[18]).text().trim() == "I can read as much as I want to with no pain in my neck."
            $(choiceList[19]).text().trim() == "I can read as much as I want to with slight pain in my neck."
            $(choiceList[20]).text().trim() == "I can read as much as I want to with moderate pain in my neck."
            $(choiceList[21]).text().trim() == "I cannot read as much as I want because of moderate pain in my neck."
            $(choiceList[22]).text().trim() == "I can hardly read at all because of severe pain in my neck."
            $(choiceList[23]).text().trim() == "I cannot read at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[21].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "Section 5: Headaches"
            $(choiceList[24]).text().trim() == "I have no headaches at all."
            $(choiceList[25]).text().trim() == "I have slight headaches that come infrequently."
            $(choiceList[26]).text().trim() == "I have moderate headaches which come infrequently."
            $(choiceList[27]).text().trim() == "I have moderate headaches which come frequently."
            $(choiceList[28]).text().trim() == "I have severe headaches which come frequently."
            $(choiceList[29]).text().trim() == "I have headaches almost all the time."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "Section 6: Concentration"
            $(choiceList[30]).text().trim() == "I can concentrate fully when I want to with no difficulty."
            $(choiceList[31]).text().trim() == "I can concentrate fully when I want to with slight difficulty."
            $(choiceList[32]).text().trim() == "I have a fair degree of difficulty in concentrating when I want to."
            $(choiceList[33]).text().trim() == "I have a lot of difficulty in concentrating when I want to."
            $(choiceList[34]).text().trim() == "I have a great deal of difficulty in concentrating when I want to."
            $(choiceList[35]).text().trim() == "I cannot concentrate at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[35].click() //question 6 choice 6

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "Section 7: Work"
            $(choiceList[36]).text().trim() == "I can do as much work as I want to."
            $(choiceList[37]).text().trim() == "I can do my usual work, but no more."
            $(choiceList[38]).text().trim() == "I can do most of my usual work, but no more."
            $(choiceList[39]).text().trim() == "I cannot do my usual work."
            $(choiceList[40]).text().trim() == "I can hardly do any work at all."
            $(choiceList[41]).text().trim() == "I cannot do any work at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 7 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "Section 8: Driving"
            $(choiceList[42]).text().trim() == "I can drive my car without any neck pain."
            $(choiceList[43]).text().trim() == "I can drive my car as long as I want with slight pain in my neck."
            $(choiceList[44]).text().trim() == "I can drive my car as long as I want with moderate pain in my neck."
            $(choiceList[45]).text().trim() == "I cannot drive my car as long as I want because of moderate pain in my neck."
            $(choiceList[46]).text().trim() == "I can hardly drive at all because of severe pain in my neck."
            $(choiceList[47]).text().trim() == "I cannot drive my car at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[43].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[43].click() //question 8 choice 2

        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "Section 9: Sleeping"
            $(choiceList[48]).text().trim() == "I have no trouble sleeping."
            $(choiceList[49]).text().trim() == "My sleep is slightly disturbed (less than 1 hour sleepless)."
            $(choiceList[50]).text().trim() == "My sleep is mildly disturbed ( 1-2 hours sleepless)."
            $(choiceList[51]).text().trim() == "My sleep is moderately disturbed ( 2-3 hours sleepless)."
            $(choiceList[52]).text().trim() == "My sleep is greatly disturbed ( 3-5 hours sleepless)."
            $(choiceList[53]).text().trim() == "My sleep is completely disturbed ( 5-7 hours sleepless)."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[50].click() //question 9 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "Section 10: Recreation"
            $(choiceList[54]).text().trim() == "I am able to engage in all my recreation activities with no neck pain at all."
            $(choiceList[55]).text().trim() == "I am able to engage in all my recreation activities, with some pain in my neck."
            $(choiceList[56]).text().trim() == "I am able to engage in most, but not all, of my usual recreation activities because of pain in my neck."
            $(choiceList[57]).text().trim() == "I am able to engage in a few of my usual recreation activities because of pain in my neck."
            $(choiceList[58]).text().trim() == "I can hardly do any recreation activities because of pain in my neck."
            $(choiceList[59]).text().trim() == "I cannot do any recreation activities at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[57].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[57].click() //question 10 choice 4

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            //browser.at TaskIntroPage
        }

    }

    def checkAndClickNRSBACKTasks(){
        when:
        waitFor(30, 1) {
            $(questionList[0]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your back pain right now?'
        }

        and:
        js.exec("document.getElementsByClassName('answer')[5].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[5].click()  //question 1 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your leg pain right now?'
        }
        and:
        js.exec("document.getElementsByClassName('answer')[16].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[16].click() //question 2 choice 5

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            // at TaskIntroPage
        }
    }

    def checkAndClickNRSNECKTasks(){
        when:
        waitFor(30, 1) {
            $(questionList[0]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your neck pain right now?'
        }
        and:
        js.exec("document.getElementsByClassName('answer')[5].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[5].click()  //question 1 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your arm pain right now?'
        }
        and:
        js.exec("document.getElementsByClassName('answer')[16].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[16].click() //question 2 choice 5

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            //at TaskIntroPage
        }
    }

    def checkAndClickQuickDASHTasks(){
        def choiceList = answerList.find('.text')
        waitFor(30,1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "1. Open a tight or new jar."
            $(choiceList[0]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[1]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[2]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[3]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[4]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "2. Do heavy household chores (e.g., wash walls, floors)."
            $(choiceList[5]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[6]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[7]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[8]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[9]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[6].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "3. Carry a shopping bag or briefcase."
            $(choiceList[10]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[11]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[12]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[13]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[14]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "4. Wash your back."
            $(choiceList[15]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[16]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[17]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[18]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[19]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click() //question 4 choice 4

        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "5. Use a knife to cut food."
            $(choiceList[20]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[21]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[22]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[23]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[24]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "6. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."
            $(choiceList[25]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[26]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[27]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[28]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[29]).text().trim() == QUICK_DASH_UNABLE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 6 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "7. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"
            $(choiceList[30]).text().trim() == "NOT AT ALL"
            $(choiceList[31]).text().trim() == "SLIGHTLY"
            $(choiceList[32]).text().trim() == "MODERATELY"
            $(choiceList[33]).text().trim() == "QUITE A BIT"
            $(choiceList[34]).text().trim() == "EXTREMELY"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click() //question 7 choice 3

        and:
        waitFor(3, 1) {
            $(questionList[7]).text().trim() == "8. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
            $(choiceList[35]).text().trim() == "NOT LIMITED AT ALL"
            $(choiceList[36]).text().trim() == "SLIGHTLY LIMITED"
            $(choiceList[37]).text().trim() == "MODERATELY LIMITED"
            $(choiceList[38]).text().trim() == "VERY LIMITED"
            $(choiceList[39]).text().trim() == "UNABLE"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 8 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "9. Arm, shoulder or hand pain."
            $(choiceList[40]).text().trim() == QUICK_DASH_NONE_CHOICE
            $(choiceList[41]).text().trim() == QUICK_DASH_MILD_CHOICE
            $(choiceList[42]).text().trim() == QUICK_DASH_MODERATE_CHOICE
            $(choiceList[43]).text().trim() == QUICK_DASH_SEVERE_CHOICE
            $(choiceList[44]).text().trim() == QUICK_DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 9 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "10. Tingling (pins and needles) in your arm, shoulder or hand."
            $(choiceList[45]).text().trim() == QUICK_DASH_NONE_CHOICE
            $(choiceList[46]).text().trim() == QUICK_DASH_MILD_CHOICE
            $(choiceList[47]).text().trim() == QUICK_DASH_MODERATE_CHOICE
            $(choiceList[48]).text().trim() == QUICK_DASH_SEVERE_CHOICE
            $(choiceList[49]).text().trim() == QUICK_DASH_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[10]).text().trim() == "11. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand? (circle number)"
            $(choiceList[50]).text().trim() == QUICK_DASH_NO_DIFFICULTY_CHOICE
            $(choiceList[51]).text().trim() == QUICK_DASH_MID_DIFFICULTY_CHOICE
            $(choiceList[52]).text().trim() == QUICK_DASH_MODERATE_DIFFICULTY_CHOICE
            $(choiceList[53]).text().trim() == QUICK_DASH_SEVERE_DIFFICULTY_CHOICE
            $(choiceList[54]).text().trim() =="SO MUCH DIFFICULTY THAT I CAN'T SLEEP"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[52].click() //question 11 choice 3

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
        }
    }

    def checkAndClickFairleyTasks(){
        when:
        def choiceList = answerList.find('.text')
        waitFor(30, 1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "1. Is your nose blocked?"
            $(choiceList[0]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[1]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[2]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[3]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[4]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[5]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click() //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "2. Do you feel dripping at the back of your nose?"
            $(choiceList[6]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[7]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[8]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[9]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[10]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[11]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click() //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "3. Does your nose run?"
            $(choiceList[12]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[13]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[14]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[15]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[16]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[17]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "4. Do you get headaches?"
            $(choiceList[18]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[19]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[20]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[21]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[22]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[23]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[21].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "5. Do you get pains in the face or eye?"
            $(choiceList[24]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[25]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[26]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[27]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[28]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[29]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "6. Is your sense of smell reduced?"
            $(choiceList[30]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[31]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[32]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[33]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[34]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[35]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[35].click() //question 6 choice 6

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "7. Do you suffer from cough?"
            $(choiceList[36]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[37]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[38]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[39]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[40]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[41]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 7 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "8. Do you get toothache?"
            $(choiceList[42]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[43]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[44]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[45]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[46]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[47]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[45].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[45].click() //question 8 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "9. Do you get nosebleeds?"
            $(choiceList[48]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[49]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[50]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[51]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[52]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[53]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[50].click() //question 9 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "10. Do you sneeze often?"
            $(choiceList[54]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[55]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[56]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[57]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[58]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[59]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[55].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[55].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[10]).text().trim() == "11. Do you get sore throats?"
            $(choiceList[60]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[61]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[62]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[63]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[64]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[65]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[60].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[60].click() //question 11 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[11]).text().trim() == "12. Do you feel generally unwell?"
            $(choiceList[66]).text().trim() == FNS_NO_PROBLEM_CHOICE
            $(choiceList[67]).text().trim() == FNS_VERY_MILD_PROBLEM_CHOICE
            $(choiceList[68]).text().trim() == FNS_MILD_OR_SLIGHT_PROBLEM_CHOICE
            $(choiceList[69]).text().trim() == FNS_MODERATE_PROBLEM_CHOICE
            $(choiceList[70]).text().trim() == FNS_SEVERE_PROBLEM_CHOICE
            $(choiceList[71]).text().trim() == FNS_PROBLEM_BAD_AS_IT_CAN_BE_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[67].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[67].click() //question 12 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[12]).text().trim() == "13. How many courses of antibiotics have you had in the last six months?"
            $(choiceList[72]).text().trim() == "0"
            $(choiceList[73]).text().trim() == "1"
            $(choiceList[74]).text().trim() == "2"
            $(choiceList[75]).text().trim() == "3"
            $(choiceList[76]).text().trim() == "4"
            $(choiceList[77]).text().trim() == "5"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[74].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[74].click() //question 13 choice 3

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            //at TaskIntroPage
        }
    }

    def checkAndClickHarrisTasks(){
        def choiceList = answerList.find('.text')
        when:
        waitFor(30, 1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "Pain"
            $(choiceList[0]).text().trim() == "None, or ignores it"
            $(choiceList[1]).text().trim() == "Slight, occasional, no compromise in activity"
            $(choiceList[2]).text().trim() == "Mild pain, no effect on average activities, rarely moderate pain with unusual activity, may take aspirin"
            $(choiceList[3]).text().trim() == "Moderate pain, tolerable but makes concessions to pain. Some limitations of ordinary activity or work. May require occasional pain medication stronger than aspirin"
            $(choiceList[4]).text().trim() == "Marked pain, serious limitation of activities"
            $(choiceList[5]).text().trim() == "Totally disabled, crippled, pain in bed, bedridden"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "Support"
            $(choiceList[6]).text().trim() == "None"
            $(choiceList[7]).text().trim() == "Cane/Walking stick for long walks"
            $(choiceList[8]).text().trim() == "Cane/Walking stick most of the time"
            $(choiceList[9]).text().trim() == "One crutch"
            $(choiceList[10]).text().trim() == "Two Canes/Walking sticks"
            $(choiceList[11]).text().trim() == "Two crutches or not able to walk"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "Distance walked"
            $(choiceList[12]).text().trim() == "Unlimited"
            $(choiceList[13]).text().trim() == "Six blocks (30 minutes)"
            $(choiceList[14]).text().trim() == "Two or three blocks (10 - 15 minutes)"
            $(choiceList[15]).text().trim() == "Indoors only"
            $(choiceList[16]).text().trim() == "Bed and chair only"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "Limp"
            $(choiceList[17]).text().trim() == "None"
            $(choiceList[18]).text().trim() == "Slight"
            $(choiceList[19]).text().trim() == "Moderate"
            $(choiceList[20]).text().trim() == "Severe or unable to walk"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[20].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[20].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "Activities - shoes, socks"
            $(choiceList[21]).text().trim() == "With ease"
            $(choiceList[22]).text().trim() == "With difficulty"
            $(choiceList[23]).text().trim() == "Unable to fit or tie"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[22].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[22].click() //question 5 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "Stairs"
            $(choiceList[24]).text().trim() == "Normally without using a railing"
            $(choiceList[25]).text().trim() == "Normally using a railing"
            $(choiceList[26]).text().trim() == "In any manner"
            $(choiceList[27]).text().trim() == "Unable to do stairs"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 6 choice 1

        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "Public transportation"
            $(choiceList[28]).text().trim() == "Able to use transportation (bus)"
            $(choiceList[29]).text().trim() == "Unable to use public transportation (bus)"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[29].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[29].click() //question 7 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "Sitting"
            $(choiceList[30]).text().trim() == "Comfortably, ordinary chair for one hour"
            $(choiceList[31]).text().trim() == "On a high chair for 30 minutes"
            $(choiceList[32]).text().trim() == "Unable to sit comfortably on any chair"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click() //question 8 choice 3

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            at TaskCompletePage
            // at TaskIntroPage
        }
    }

    def checkAndClickHOOSTasks(){
        when:
        def choiceList = answerList.find('.text')

        waitFor(30, 1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "1. Do you feel grinding, hear clicking or any other type of noise from your hip?"
            $(choiceList[0]).text().trim() == HOOS_NEVER_CHOICE
            $(choiceList[1]).text().trim() == HOOS_RARELY_CHOICE
            $(choiceList[2]).text().trim() == HOOS_SOMETIMES_CHOICE
            $(choiceList[3]).text().trim() == HOOS_OFTEN_CHOICE
            $(choiceList[4]).text().trim() == HOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "2. Difficulties spreading legs wide apart"
            $(choiceList[5]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[6]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[7]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[8]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[9]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[6].click()   //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "3. Difficulties to stride out when walking"
            $(choiceList[10]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[11]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[12]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[13]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[14]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click()  //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "4. How severe is your hip joint stiffness after first wakening in the morning?"
            $(choiceList[15]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[16]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[17]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[18]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[19]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click()  //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "5. How severe is your hip stiffness after sitting, lying or resting later in the day?"
            $(choiceList[20]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[21]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[22]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[23]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[24]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click()  //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "1. How often is your hip painful?"
            $(choiceList[25]).text().trim() == HOOS_NEVER_CHOICE
            $(choiceList[26]).text().trim() == HOOS_MONTHLY_CHOICE
            $(choiceList[27]).text().trim() == HOOS_WEEKLY_CHOICE
            $(choiceList[28]).text().trim() == HOOS_DAILY_CHOICE
            $(choiceList[29]).text().trim() == HOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 1 of pain choice 4

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "2. Straightening your hip fully"
            $(choiceList[30]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[31]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[32]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[33]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[34]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click()  //question 2 of pain choice 3

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "3. Bending your hip fully"
            $(choiceList[35]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[36]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[37]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[38]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[39]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 3 of pain choice 2

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "4. Walking on a flat surface"
            $(choiceList[40]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[41]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[42]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[43]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[44]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click()  //question 4 of pain choice 1

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "5. Going up or down stairs"
            $(choiceList[45]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[46]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[47]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[48]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[49]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click()  //question 5 of pain choice 2

        and:
        waitFor(30, 1) {
            $(questionList[10]).text().trim() == "6. At night while in bed"
            $(choiceList[50]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[51]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[52]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[53]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[54]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[52].click()  //question 6 of pain choice 3

        and:
        waitFor(30, 1) {
            $(questionList[11]).text().trim() == "7. Sitting or lying"
            $(choiceList[55]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[56]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[57]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[58]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[59]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[58].click()  //question 7 of pain choice 4

        and:
        waitFor(30, 1) {
            $(questionList[12]).text().trim() == "8. Standing upright"
            $(choiceList[60]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[61]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[62]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[63]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[64]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[64].click()  //question 8 of pain choice 5

        and:
        waitFor(30, 1) {
            $(questionList[13]).text().trim() == "9. Walking on a hard surface (asphalt, concrete, etc.)"
            $(choiceList[65]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[66]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[67]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[68]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[69]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[68].click()  //question 9 of pain choice 4

        and:
        waitFor(30, 1) {
            $(questionList[14]).text().trim() == "10. Walking on an uneven surface"
            $(choiceList[70]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[71]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[72]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[73]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[74]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[72].click()  //question 10 of pain choice 3

        and:
        waitFor(30, 1) {
            $(questionList[15]).text().trim() == "1. Descending stairs"
            $(choiceList[75]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[76]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[77]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[78]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[79]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[76].click()  //question 1 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[16]).text().trim() == "2. Ascending stairs"
            $(choiceList[80]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[81]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[82]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[83]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[84]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[80].click()  //question 2 of function choice 1

        and:
        waitFor(30, 1) {
            $(questionList[17]).text().trim() == "3. Rising from sitting"
            $(choiceList[85]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[86]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[87]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[88]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[89]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[86].click()  //question 3 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[18]).text().trim() == "4. Standing"
            $(choiceList[90]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[91]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[92]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[93]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[94]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[92].click()  //question 4 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[19]).text().trim() == "5. Bending to the floor/pick up an object"
            $(choiceList[95]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[96]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[97]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[98]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[99]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[98].click()  //question 5 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[20]).text().trim() == "6. Walking on a flat surface"
            $(choiceList[100]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[101]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[102]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[103]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[104]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[104].click()  //question 6 of function choice 5

        and:
        waitFor(30, 1) {
            $(questionList[21]).text().trim() == "7. Getting in/out of car"
            $(choiceList[105]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[106]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[107]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[108]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[109]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[108].click()  //question 7 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[22]).text().trim() == "8. Going shopping"
            $(choiceList[110]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[111]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[112]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[113]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[114]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[112].click()  //question 8 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[23]).text().trim() == "9. Putting on socks/stockings"
            $(choiceList[115]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[116]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[117]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[118]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[119]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[116].click()  //question 9 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[24]).text().trim() == "10. Rising from bed"
            $(choiceList[120]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[121]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[122]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[123]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[124]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[120].click()  //question 10 of function choice 1

        and:
        waitFor(30, 1) {
            $(questionList[25]).text().trim() == "11. Taking off socks/stockings"
            $(choiceList[125]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[126]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[127]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[128]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[129]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[126].click()  //question 11 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[26]).text().trim() == "12. Lying in bed (turning over, maintaining hip position)"
            $(choiceList[130]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[131]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[132]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[133]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[134]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[132].click()  //question 12 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[27]).text().trim() == "13. Getting in/out of bath"
            $(choiceList[135]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[136]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[137]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[138]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[139]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[138].click()  //question 13 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[28]).text().trim() == "14. Sitting"
            $(choiceList[140]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[141]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[142]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[143]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[144]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[144].click()  //question 14 of function choice 5

        and:
        waitFor(30, 1) {
            $(questionList[29]).text().trim() == "15. Getting on/off toilet"
            $(choiceList[145]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[146]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[147]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[148]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[149]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[148].click()  //question 15 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[30]).text().trim() == "16. Heavy domestic duties (moving heavy boxes, scrubbing floors, etc)"
            $(choiceList[150]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[151]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[152]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[153]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[154]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[152].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[152].click()  //question 16 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[31]).text().trim() == "17. Light domestic duties (cooking, dusting, etc)"
            $(choiceList[155]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[156]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[157]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[158]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[159]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[156].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[156].click()  //question 17 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[32]).text().trim() == "1. Squatting"
            $(choiceList[160]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[161]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[162]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[163]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[164]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[160].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[160].click()  //question 1 of function, sports choice 1

        and:
        waitFor(30, 1) {
            $(questionList[33]).text().trim() == "2. Running"
            $(choiceList[165]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[166]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[167]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[168]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[169]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[166].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[166].click()  //question 2 of function, sports choice 2

        and:
        waitFor(30, 1) {
            $(questionList[34]).text().trim() == "3. Twisting/pivoting on loaded leg"
            $(choiceList[170]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[171]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[172]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[173]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[174]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[172].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[172].click()  //question 3 of function, sports choice 3

        and:
        waitFor(30, 1) {
            $(questionList[35]).text().trim() == "4. Walking on uneven surface"
            $(choiceList[175]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[176]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[177]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[178]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[179]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[178].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[178].click()  //question 4 of function, sports choice 4

        and:
        waitFor(30, 1) {
            $(questionList[36]).text().trim() == "1. How often are you aware of your hip problem?"
            $(choiceList[180]).text().trim() == HOOS_NEVER_CHOICE
            $(choiceList[181]).text().trim() == HOOS_MONTHLY_CHOICE
            $(choiceList[182]).text().trim() == HOOS_WEEKLY_CHOICE
            $(choiceList[183]).text().trim() == HOOS_DAILY_CHOICE
            $(choiceList[184]).text().trim() == HOOS_CONSTANTLY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[184].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[184].click()  //question 1 of quality of life choice 5

        and:
        waitFor(30, 1) {
            $(questionList[37]).text().trim() == "2. Have you modified your life style to avoid activities potentially damaging to your hip?"
            $(choiceList[185]).text().trim() == HOOS_NOT_AT_ALL_CHOICE
            $(choiceList[186]).text().trim() == HOOS_MILDLY_CHOICE
            $(choiceList[187]).text().trim() == HOOS_MODERATELY_CHOICE
            $(choiceList[188]).text().trim() == HOOS_SEVERELY_CHOICE
            $(choiceList[189]).text().trim() == HOOS_TOTALLY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[188].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[188].click()  //question 2 of quality of life choice 4

        and:
        waitFor(30, 1) {
            $(questionList[38]).text().trim() == "3. How much are you troubled with lack of confidence in your hip?"
            $(choiceList[190]).text().trim() == HOOS_NOT_AT_ALL_CHOICE
            $(choiceList[191]).text().trim() == HOOS_MILDLY_CHOICE
            $(choiceList[192]).text().trim() == HOOS_MODERATELY_CHOICE
            $(choiceList[193]).text().trim() == HOOS_SEVERELY_CHOICE
            $(choiceList[194]).text().trim() == HOOS_EXTREMELY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[192].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[192].click()  //question 3 of quality of life choice 3

        and:
        waitFor(30, 1) {
            $(questionList[39]).text().trim() == "4. In general, how much difficulty do you have with your hip?"
            $(choiceList[195]).text().trim() == HOOS_NONE_CHOICE
            $(choiceList[196]).text().trim() == HOOS_MILD_CHOICE
            $(choiceList[197]).text().trim() == HOOS_MODERATE_CHOICE
            $(choiceList[198]).text().trim() == HOOS_SEVERE_CHOICE
            $(choiceList[199]).text().trim() == HOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[196].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[196].click()  //question 4 of quality of life choice 2

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
            // at TaskIntroPage
        }
    }

    def checkAndClickKOOSTasks(){
        def choiceList = answerList.find('.text')

        waitFor(30, 1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "1. Do you have swelling in your knee?"
            $(choiceList[0]).text().trim() == KOOS_NEVER_CHOICE
            $(choiceList[1]).text().trim() == KOOS_RARELY_CHOICE
            $(choiceList[2]).text().trim() == KOOS_SOMETIMES_CHOICE
            $(choiceList[3]).text().trim() == KOOS_OFTEN_CHOICE
            $(choiceList[4]).text().trim() == KOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 of symptoms choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "2. Do you feel grinding, hear clicking or any other type of noise when your knee moves?"
            $(choiceList[5]).text().trim() == KOOS_NEVER_CHOICE
            $(choiceList[6]).text().trim() == KOOS_RARELY_CHOICE
            $(choiceList[7]).text().trim() == KOOS_SOMETIMES_CHOICE
            $(choiceList[8]).text().trim() == KOOS_OFTEN_CHOICE
            $(choiceList[9]).text().trim() == KOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[6].click()   //question 2 of symptoms choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "3. Does your knee catch or hang up when moving?"
            $(choiceList[10]).text().trim() == KOOS_NEVER_CHOICE
            $(choiceList[11]).text().trim() == KOOS_RARELY_CHOICE
            $(choiceList[12]).text().trim() == KOOS_SOMETIMES_CHOICE
            $(choiceList[13]).text().trim() == KOOS_OFTEN_CHOICE
            $(choiceList[14]).text().trim() == KOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[12].click()  //question 3 of symptoms choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "4. Can you straighten your knee fully?"
            $(choiceList[15]).text().trim() == KOOS_ALWAYS_CHOICE
            $(choiceList[16]).text().trim() == KOOS_OFTEN_CHOICE
            $(choiceList[17]).text().trim() == KOOS_SOMETIMES_CHOICE
            $(choiceList[18]).text().trim() == KOOS_RARELY_CHOICE
            $(choiceList[19]).text().trim() == KOOS_NEVER_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[18].click()  //question 4 of symptoms choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "5. Can you bend your knee fully?"
            $(choiceList[20]).text().trim() == KOOS_ALWAYS_CHOICE
            $(choiceList[21]).text().trim() == KOOS_OFTEN_CHOICE
            $(choiceList[22]).text().trim() == KOOS_SOMETIMES_CHOICE
            $(choiceList[23]).text().trim() == KOOS_RARELY_CHOICE
            $(choiceList[24]).text().trim() == KOOS_NEVER_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[24].click()  //question 5 of symptoms choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "6. How severe is your knee joint stiffness after first wakening in the morning?"
            $(choiceList[25]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[26]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[27]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[28]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[29]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 6 of stiffness choice 4

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "7. How severe is your knee stiffness after sitting, lying or resting later in the day?"
            $(choiceList[30]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[31]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[32]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[33]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[34]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[32].click()  //question 7 of stiffness choice 3

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "1. How often do you experience knee pain?"
            $(choiceList[35]).text().trim() == KOOS_NEVER_CHOICE
            $(choiceList[36]).text().trim() == KOOS_MONTHLY_CHOICE
            $(choiceList[37]).text().trim() == KOOS_WEEKLY_CHOICE
            $(choiceList[38]).text().trim() == KOOS_DAILY_CHOICE
            $(choiceList[39]).text().trim() == KOOS_ALWAYS_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 1 of pain choice 2

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "2. Twisting/pivoting on your knee"
            $(choiceList[40]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[41]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[42]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[43]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[44]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[40].click()  //question 2 of pain choice 1

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "3. Straightening knee fully"
            $(choiceList[45]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[46]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[47]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[48]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[49]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[46].click()  //question 3 of pain choice 2

        and:
        waitFor(30, 1) {
            $(questionList[10]).text().trim() == "4. Bending knee fully"
            $(choiceList[50]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[51]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[52]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[53]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[54]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[52].click()  //question 4 of pain choice 3

        and:
        waitFor(30, 1) {
            $(questionList[11]).text().trim() == "5. Walking on flat surface"
            $(choiceList[55]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[56]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[57]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[58]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[59]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[58].click()  //question 5 of pain choice 4

        and:
        waitFor(30, 1) {
            $(questionList[12]).text().trim() == "6. Going up or down stairs"
            $(choiceList[60]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[61]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[62]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[63]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[64]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[64].click()  //question 6 of pain choice 5

        and:
        waitFor(30, 1) {
            $(questionList[13]).text().trim() == "7. At night while in bed"
            $(choiceList[65]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[66]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[67]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[68]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[69]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[68].click()  //question 7 of pain choice 4

        and:
        waitFor(3, 1) {
            $(questionList[14]).text().trim() == "8. Sitting or lying"
            $(choiceList[70]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[71]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[72]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[73]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[74]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[72].click()  //question 8 of pain choice 3

        and:
        waitFor(3, 1) {
            $(questionList[15]).text().trim() == "9. Standing upright"
            $(choiceList[75]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[76]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[77]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[78]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[79]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[76].click()  //question 9 of pain choice 2

        and:
        waitFor(30, 1) {
            $(questionList[16]).text().trim() == "1. Descending stairs"
            $(choiceList[80]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[81]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[82]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[83]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[84]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[80].click()  //question 1 of function choice 1

        and:
        waitFor(30, 1) {
            $(questionList[17]).text().trim() == "2. Ascending stairs"
            $(choiceList[85]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[86]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[87]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[88]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[89]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[86].click()  //question 2 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[18]).text().trim() == "3. Rising from sitting"
            $(choiceList[90]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[91]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[92]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[93]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[94]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[92].click()  //question 3 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[19]).text().trim() == "4. Standing"
            $(choiceList[95]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[96]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[97]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[98]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[99]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[98].click()  //question 4 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[20]).text().trim() == "5. Bending to floor/pick up an object"
            $(choiceList[100]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[101]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[102]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[103]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[104]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[104].click()  //question 5 of function choice 5

        and:
        waitFor(30, 1) {
            $(questionList[21]).text().trim() == "6. Walking on flat surface"
            $(choiceList[105]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[106]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[107]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[108]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[109]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[108].click()  //question 6 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[22]).text().trim() == "7. Getting in/out of car"
            $(choiceList[110]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[111]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[112]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[113]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[114]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[112].click()  //question 7 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[23]).text().trim() == "8. Going shopping"
            $(choiceList[115]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[116]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[117]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[118]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[119]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[116].click()  //question 8 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[24]).text().trim() == "9. Putting on socks/stockings"
            $(choiceList[120]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[121]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[122]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[123]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[124]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[120].click()  //question 9 of function choice 1

        and:
        waitFor(30, 1) {
            $(questionList[25]).text().trim() == "10. Rising from bed"
            $(choiceList[125]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[126]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[127]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[128]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[129]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[126].click()  //question 10 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[26]).text().trim() == "11. Taking off socks/stockings"
            $(choiceList[130]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[131]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[132]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[133]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[134]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[132].click()  //question 11 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[27]).text().trim() == "12. Lying in bed (turning over, maintaining knee position)"
            $(choiceList[135]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[136]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[137]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[138]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[139]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[138].click()  //question 12 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[28]).text().trim() == "13. Getting in/out of bath"
            $(choiceList[140]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[141]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[142]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[143]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[144]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[144].click()  //question 13 of function choice 5

        and:
        waitFor(30, 1) {
            $(questionList[29]).text().trim() == "14. Sitting"
            $(choiceList[145]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[146]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[147]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[148]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[149]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[148].click()  //question 14 of function choice 4

        and:
        waitFor(30, 1) {
            $(questionList[30]).text().trim() == "15. Getting on/off toilet"
            $(choiceList[150]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[151]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[152]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[153]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[154]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[152].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[152].click()  //question 15 of function choice 3

        and:
        waitFor(30, 1) {
            $(questionList[31]).text().trim() == "16. Heavy domestic duties (moving heavy boxes, scrubbing floors, etc)"
            $(choiceList[155]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[156]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[157]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[158]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[159]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[156].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[156].click()  //question 16 of function choice 2

        and:
        waitFor(30, 1) {
            $(questionList[32]).text().trim() == "17. Light domestic duties (cooking, dusting, etc)"
            $(choiceList[160]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[161]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[162]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[163]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[164]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[160].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[160].click()  //question 17 of function choice 1

        and:
        waitFor(30, 1) {
            $(questionList[33]).text().trim() == "1. Squatting"
            $(choiceList[165]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[166]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[167]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[168]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[169]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[166].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[166].click()  //question 1 of function, sports choice 2

        and:
        waitFor(30, 1) {
            $(questionList[34]).text().trim() == "2. Running"
            $(choiceList[170]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[171]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[172]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[173]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[174]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[172].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[172].click()  //question 2 of function, sports choice 3

        and:
        waitFor(30, 1) {
            $(questionList[35]).text().trim() == "3. Jumping"
            $(choiceList[175]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[176]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[177]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[178]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[179]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[178].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[178].click()  //question 3 of function, sports choice 4

        and:
        waitFor(30, 1) {
            $(questionList[36]).text().trim() == "4. Twisting/pivoting on your injured knee"
            $(choiceList[180]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[181]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[182]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[183]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[184]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[184].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[184].click()  //question 4 of function, sports choice 5

        and:
        waitFor(30, 1) {
            $(questionList[37]).text().trim() == "5. Kneeling"
            $(choiceList[185]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[186]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[187]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[188]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[189]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[188].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[188].click()  //question 5 of function, sports choice 4

        and:
        waitFor(30, 1) {
            $(questionList[38]).text().trim() == "1. How often are you aware of your knee problem?"
            $(choiceList[190]).text().trim() == KOOS_NEVER_CHOICE
            $(choiceList[191]).text().trim() == KOOS_MONTHLY_CHOICE
            $(choiceList[192]).text().trim() == KOOS_WEEKLY_CHOICE
            $(choiceList[193]).text().trim() == KOOS_DAILY_CHOICE
            $(choiceList[194]).text().trim() == KOOS_CONSTANTLY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[192].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[192].click()  //question 1 of quality of life choice 3

        and:
        waitFor(30, 1) {
            $(questionList[39]).text().trim() == "2. Have you modified your life style to avoid potentially damaging activities to your knee?"
            $(choiceList[195]).text().trim() == KOOS_NOT_AT_ALL_CHOICE
            $(choiceList[196]).text().trim() == KOOS_MILDLY_CHOICE
            $(choiceList[197]).text().trim() == KOOS_MODERATELY_CHOICE
            $(choiceList[198]).text().trim() == KOOS_SEVERELY_CHOICE
            $(choiceList[199]).text().trim() == KOOS_TOTALLY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[196].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[196].click()  //question 2 of quality of life choice 2

        and:
        waitFor(30, 1) {
            $(questionList[40]).text().trim() == "3. How much are you troubled with lack of confidence in your knee?"
            $(choiceList[200]).text().trim() == KOOS_NOT_AT_ALL_CHOICE
            $(choiceList[201]).text().trim() == KOOS_MILDLY_CHOICE
            $(choiceList[202]).text().trim() == KOOS_MODERATELY_CHOICE
            $(choiceList[203]).text().trim() == KOOS_SEVERELY_CHOICE
            $(choiceList[204]).text().trim() == KOOS_EXTREMELY_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[200].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[200].click()  //question 3 of quality of life choice 1

        and:
        waitFor(30, 1) {
            $(questionList[41]).text().trim() == "4. In general, how much difficulty do you have with your knee?"
            $(choiceList[205]).text().trim() == KOOS_NONE_CHOICE
            $(choiceList[206]).text().trim() == KOOS_MILD_CHOICE
            $(choiceList[207]).text().trim() == KOOS_MODERATE_CHOICE
            $(choiceList[208]).text().trim() == KOOS_SEVERE_CHOICE
            $(choiceList[209]).text().trim() == KOOS_EXTREME_CHOICE
        }
        and:
        js.exec("document.getElementsByClassName('answer')[206].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[206].click()  //question 2 of quality of life choice 2

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            browser.at TaskCompletePage
        }
    }

    def checkAndClickODITasks(){
        def choiceList = answerList.find('.text')
        waitFor(30, 1) {
            choiceList.displayed
            $(questionList[0]).text().trim() == "Section 1: Pain Intensity"
            $(choiceList[0]).text().trim() == "I have no pain at the moment."
            $(choiceList[1]).text().trim() == "The pain is very mild at the moment."
            $(choiceList[2]).text().trim() == "The pain is moderate at the moment."
            $(choiceList[3]).text().trim() == "The pain is fairly severe at the moment."
            $(choiceList[4]).text().trim() == "The pain is very severe at the moment."
            $(choiceList[5]).text().trim() == "The pain is the worst imaginable at the moment."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[1]).text().trim() == "Section 2: Personal Care (washing, dressing, etc.)"
            $(choiceList[6]).text().trim() == "I can look after myself normally without causing additional pain."
            $(choiceList[7]).text().trim() == "I can look after myself normally but it is very painful."
            $(choiceList[8]).text().trim() == "It is painful to look after myself and I am slow and careful."
            $(choiceList[9]).text().trim() == "I need some help but manage most of my personal care."
            $(choiceList[10]).text().trim() == "I need help every day in most aspects of my personal care."
            $(choiceList[11]).text().trim() == "I do not get dressed, I wash with difficulty and stay in bed."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[7].click()   //question 2 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[2]).text().trim() == "Section 3: Lifting"
            $(choiceList[12]).text().trim() == "I can lift heavy weights without additional pain."
            $(choiceList[13]).text().trim() == "I can lift heavy weights but it gives me additional pain."
            $(choiceList[14]).text().trim() == "Pain prevents me from lifting heavy weights off the floor but I can manage if they are conveniently positioned, e.g. on a table."
            $(choiceList[15]).text().trim() == "Pain prevents me from lifting heavy weights but I can manage light to medium weights if they are conveniently positioned."
            $(choiceList[16]).text().trim() == "I can only lift very light weights."
            $(choiceList[17]).text().trim() == "I cannot lift or carry anything at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[14].click()  //question 3 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[3]).text().trim() == "Section 4: Walking"
            $(choiceList[18]).text().trim() == "Pain does not prevent me from walking any distance."
            $(choiceList[19]).text().trim() == "Pain prevents me from walking more than one mile."
            $(choiceList[20]).text().trim() == "Pain prevents me from walking more than a quarter of a mile."
            $(choiceList[21]).text().trim() == "Pain prevents me from walking more than 100 yards."
            $(choiceList[22]).text().trim() == "I can only walk using a cane or crutches."
            $(choiceList[23]).text().trim() == "I am in bed most of the time and have to crawl to the toilet."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[21].click()  //question 4 choice 4

        and:
        waitFor(30, 1) {
            $(questionList[4]).text().trim() == "Section 5: Sitting"
            $(choiceList[24]).text().trim() == "I can sit in any chair as long as I like."
            $(choiceList[25]).text().trim() == "I can sit in my favorite chair as long as I like."
            $(choiceList[26]).text().trim() == "Pain prevents me from sitting for more than 1 hour."
            $(choiceList[27]).text().trim() == "Pain prevents me from sitting for more than half an hour."
            $(choiceList[28]).text().trim() == "Pain prevents me from sitting for more than 10 minutes."
            $(choiceList[29]).text().trim() == "Pain prevents me from sitting at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 5 choice 5

        and:
        waitFor(30, 1) {
            $(questionList[5]).text().trim() == "Section 6: Standing"
            $(choiceList[30]).text().trim() == "I can stand as long as I want without additional pain."
            $(choiceList[31]).text().trim() == "I can stand as long as I want but it gives me additional pain."
            $(choiceList[32]).text().trim() == "Pain prevents me from standing for more than 1 hour."
            $(choiceList[33]).text().trim() == "Pain prevents me from standing for more than half an hour."
            $(choiceList[34]).text().trim() == "Pain prevents me from standing for more than 10 minutes."
            $(choiceList[35]).text().trim() == "Pain prevents me from standing at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[35].click()  //question 6 choice 6

        and:
        waitFor(30, 1) {
            $(questionList[6]).text().trim() == "Section 7: Sleeping"
            $(choiceList[36]).text().trim() == "My sleep is never interrupted by pain."
            $(choiceList[37]).text().trim() == "My sleep is occasionally interrupted by pain."
            $(choiceList[38]).text().trim() == "Because of pain I have less than 6 hours sleep."
            $(choiceList[39]).text().trim() == "Because of pain I have less than 4 hours sleep."
            $(choiceList[40]).text().trim() == "Because of pain I have less than 2 hours sleep."
            $(choiceList[41]).text().trim() == "Pain prevents me from sleeping at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 7 choice 1

        and:
        waitFor(30, 1) {
            $(questionList[7]).text().trim() == "Section 8: Sex Life (if applicable) This question is optional."
            $(choiceList[42]).text().trim() == "My sex life is normal and causes no additional pain."
            $(choiceList[43]).text().trim() == "My sex life is normal but causes some additional pain."
            $(choiceList[44]).text().trim() == "My sex life is nearly normal but is very painful."
            $(choiceList[45]).text().trim() == "My sex life is severely restricted by pain."
            $(choiceList[46]).text().trim() == "My sex life is nearly non existent because of pain."
            $(choiceList[47]).text().trim() == "Pain prevents me from having any sex life at all."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[43].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[43].click()  //question 8 choice 2

        and:
        waitFor(30, 1) {
            $(questionList[8]).text().trim() == "Section 9: Social Life"
            $(choiceList[48]).text().trim() == "My social life is normal and causes me no additional pain."
            $(choiceList[49]).text().trim() == "My social life is normal but increases the degree of pain."
            $(choiceList[50]).text().trim() == "Pain has no significant effect on my social life apart from limiting my more energetic interests, e.g. sport, etc."
            $(choiceList[51]).text().trim() == "Pain has restricted my social life and I do not go out as often."
            $(choiceList[52]).text().trim() == "Pain has restricted my social life to home."
            $(choiceList[53]).text().trim() == "I have no social life because of pain."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[50].click()  //question 9 choice 3

        and:
        waitFor(30, 1) {
            $(questionList[9]).text().trim() == "Section 10: Traveling"
            $(choiceList[54]).text().trim() == "I can travel anywhere without pain."
            $(choiceList[55]).text().trim() == "I can travel anywhere but it gives me additional pain."
            $(choiceList[56]).text().trim() == "Pain is bad but I am able to manage trips over two hours."
            $(choiceList[57]).text().trim() == "Pain restricts me to trips of less than one hour."
            $(choiceList[58]).text().trim() == "Pain restricts me to short necessary trips of under 30 minutes."
            $(choiceList[59]).text().trim() == "Pain prevents me from traveling except to receive treatment."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[57].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[57].click()  //question 10 choice 4

        and:
        doneButton.click()

        then: "Direct to complete page"
        waitFor(30, 1) {
            //at EnterEmailPage
            browser.at TaskCompletePage
//            at TaskIntroPage
        }
    }


}
