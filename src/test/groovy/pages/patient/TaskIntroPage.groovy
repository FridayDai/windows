package pages.patient

import geb.Page

class TaskIntroPage extends Page {

    static at = { $(".task-done-btn").value() == "I'm Done" }

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def checkAndClickDASHTasks(){
        when:
        waitFor(30,1) {
            questionList[0].text().trim() == "1. Open a tight or new jar."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click() //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "2. Write."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[6].click() //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "3. Turn a key."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "4. Prepare a meal."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "5. Push open a heavy door."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "6. Place an object on a shelf above your head."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 6 choice 4

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "7. Do heavy household chores (e.g., wash walls, wash floors)."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click() //question 7 choice 3

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "8. Garden or do yard work."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 8 choice 2

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "9. Make a bed."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 9 choice 1

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "10. Carry a shopping bag or briefcase."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            questionList[10].text().trim() == "11. Carry a heavy object (over 10 lbs)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[52].click() //question 11 choice 3

        and:
        waitFor(30, 1) {
            questionList[11].text().trim() == "12. Change a lightbulb overhead."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[58].click() //question 12 choice 4

        and:
        waitFor(30, 1) {
            questionList[12].text().trim() == "13. Wash or blow dry your hair."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[64].click() //question 13 choice 5

        and:
        waitFor(30, 1) {
            questionList[13].text().trim() == "14. Wash your back."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[68].click() //question 14 choice 4

        and:
        waitFor(30, 1) {
            questionList[14].text().trim() == "15. Put on a pullover sweater."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[72].click() //question 15 choice 3

        and:
        waitFor(30, 1) {
            questionList[15].text().trim() == "16. Use a knife to cut food."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[76].click() //question 16 choice 2

        and:
        waitFor(30, 1) {
            questionList[16].text().trim() == "17. Recreational activities which require little effort (e.g., cardplaying, knitting, etc.)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[80].click() //question 17 choice 1

        and:
        waitFor(30, 1) {
            questionList[17].text().trim() == "18. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[86].click() //question 18 choice 2

        and:
        waitFor(30, 1) {
            questionList[18].text().trim() == "19. Recreational activities in which you move your arm freely (e.g., playing frisbee, badminton, etc.)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[92].click() //question 19 choice 3

        and:
        waitFor(30, 1) {
            questionList[19].text().trim() == "20. Manage transportation needs (getting from one place to another)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[98].click() //question 20 choice 4

        and:
        waitFor(30, 1) {
            questionList[20].text().trim() == "21. Sexual activities."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[104].click() //question 21 choice 5

        and:
        waitFor(30, 1) {
            questionList[21].text().trim() == "22. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[108].click() //question 22 choice 4

        and:
        waitFor(30, 1) {
            questionList[22].text().trim() == "23. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[112].click() //question 23 choice 3

        and:
        waitFor(30, 1) {
            questionList[23].text().trim() == "24. Arm, shoulder or hand pain."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[116].click() //question 24 choice 2

        and:
        waitFor(30, 1) {
            questionList[24].text().trim() == "25. Arm, shoulder or hand pain when you performed any specific activity."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[120].click() //question 25 choice 1

        and:
        waitFor(30, 1) {
            questionList[25].text().trim() == "26. Tingling (pins and needles) in your arm, shoulder or hand."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[126].click() //question 26 choice 2

        and:
        waitFor(30, 1) {
            questionList[26].text().trim() == "27. Weakness in your arm, shoulder or hand."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[132].click() //question 27 choice 3

        and:
        waitFor(30, 1) {
            questionList[27].text().trim() == "28. Stiffness in your arm, shoulder or hand."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[138].click() //question 28 choice 4

        and:
        waitFor(30, 1) {
            questionList[28].text().trim() == "29. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[144].click() //question 29 choice 5

        and:
        waitFor(30, 1) {
            questionList[29].text().trim() == "30. I feel less capable, less confident or less useful because of my arm, shoulder or hand problem."

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
           // browser.at TaskCompletePage
            browser.at TaskIntroPage
        }
    }

    def checkAndClickNDITasks(){

        when:
        waitFor(30,1) {
            questionList[0].text().trim() == "Section 1: Pain Intensity"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "Section 2: Personal Care (Washing, Dressing, etc.)"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "Section 3: Lifting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "Section 4: Reading"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[21].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "Section 5: Headaches"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "Section 6: Concentration"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[35].click() //question 6 choice 6

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "Section 7: Work"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 7 choice 1

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "Section 8: Driving"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[43].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[43].click() //question 8 choice 2

        waitFor(30, 1) {
            questionList[8].text().trim() == "Section 9: Sleeping"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[50].click() //question 9 choice 3

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "Section 10: Recreation"

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
            //browser.at TaskCompletePage
            browser.at TaskIntroPage
        }

    }

    def checkAndClickNRSBACKTasks(){
        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your back pain right now?'
        }

        and:
        js.exec("document.getElementsByClassName('answer')[5].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[5].click()  //question 1 choice 5

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your leg pain right now?'
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
            //browser.at TaskCompletePage
             browser.at TaskIntroPage
        }
    }

    def checkAndClickNRSNECKTasks(){
        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your neck pain right now?'
        }
        and:
        js.exec("document.getElementsByClassName('answer')[5].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[5].click()  //question 1 choice 5

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your arm pain right now?'
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
            //browser.at TaskCompletePage
            browser.at TaskIntroPage
        }
    }

    def checkAndClickQuickDASHTasks(){

        waitFor(30,1) {
            questionList[0].text().trim() == "1. Open a tight or new jar."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "2. Do heavy household chores (e.g., wash walls, floors)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[6].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "3. Carry a shopping bag or briefcase."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "4. Wash your back."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click() //question 4 choice 4

        waitFor(30, 1) {
            questionList[4].text().trim() == "5. Use a knife to cut food."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "6. Recreational activities in which you take some force or impact through your arm, shoulder or hand (e.g., golf, hammering, tennis, etc.)."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 6 choice 4

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "7. During the past week, to what extent has your arm, shoulder or hand problem interfered with your normal social activities with family, friends, neighbours or groups?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click() //question 7 choice 3

        and:
        waitFor(3, 1) {
            questionList[7].text().trim() == "8. During the past week, were you limited in your work or other regular daily activities as a result of your arm, shoulder or hand problem?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click() //question 8 choice 2

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "9. Arm, shoulder or hand pain."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 9 choice 1

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "10. Tingling (pins and needles) in your arm, shoulder or hand."

        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            questionList[10].text().trim() == "11. During the past week, how much difficulty have you had sleeping because of the pain in your arm, shoulder or hand? (circle number)"

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
        waitFor(30, 1) {
            questionList[0].text().trim() == "1. Is your nose blocked?"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click() //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "2. Do you feel dripping at the back of your nose?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click() //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "3. Does your nose run?"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "4. Do you get headaches?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[21].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "5. Do you get pains in the face or eye?"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click() //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "6. Is your sense of smell reduced?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[35].click() //question 6 choice 6

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "7. Do you suffer from cough?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click() //question 7 choice 5

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "8. Do you get toothache?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[45].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[45].click() //question 8 choice 4

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "9. Do you get nosebleeds?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[50].click() //question 9 choice 3

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "10. Do you sneeze often?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[55].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[55].click() //question 10 choice 2

        and:
        waitFor(30, 1) {
            questionList[10].text().trim() == "11. Do you get sore throats?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[60].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[60].click() //question 11 choice 1

        and:
        waitFor(30, 1) {
            questionList[11].text().trim() == "12. Do you feel generally unwell?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[67].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[67].click() //question 12 choice 2

        and:
        waitFor(30, 1) {
            questionList[12].text().trim() == "13. How many courses of antibiotics have you had in the last six months?"

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
            //browser.at TaskCompletePage
            browser.at TaskIntroPage
        }
    }

    def checkAndClickHarrisTasks(){

        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == "Pain"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()  //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "Support"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[7].click()  //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "Distance walked"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[14].click() //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "Limp"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[20].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[20].click() //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "Activities - shoes, socks"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[22].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[22].click() //question 5 choice 2

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "Stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click() //question 6 choice 1

        waitFor(30, 1) {
            questionList[6].text().trim() == "Public transportation"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[29].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[29].click() //question 7 choice 2

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "Sitting"

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
            //at TaskCompletePage
            browser.at TaskIntroPage
        }
    }

    def checkAndClickHOOSTasks(){
        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == "1. Do you feel grinding, hear clicking or any other type of noise from your hip?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "2. Difficulties spreading legs wide apart"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[6].click()   //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "3. Difficulties to stride out when walking"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[12].click()  //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "4. How severe is your hip joint stiffness after first wakening in the morning?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[18].click()  //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "5. How severe is your hip stiffness after sitting, lying or resting later in the day?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[24].click()  //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "1. How often is your hip painful?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 1 of pain choice 4

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "2. Straightening your hip fully"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[32].click()  //question 2 of pain choice 3

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "3. Bending your hip fully"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 3 of pain choice 2

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "4. Walking on a flat surface"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[40].click()  //question 4 of pain choice 1

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "5. Going up or down stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[46].click()  //question 5 of pain choice 2

        and:
        waitFor(30, 1) {
            questionList[10].text().trim() == "6. At night while in bed"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[52].click()  //question 6 of pain choice 3

        and:
        waitFor(30, 1) {
            questionList[11].text().trim() == "7. Sitting or lying"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[58].click()  //question 7 of pain choice 4

        and:
        waitFor(30, 1) {
            questionList[12].text().trim() == "8. Standing upright"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[64].click()  //question 8 of pain choice 5

        and:
        waitFor(30, 1) {
            questionList[13].text().trim() == "9. Walking on a hard surface (asphalt, concrete, etc.)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[68].click()  //question 9 of pain choice 4

        and:
        waitFor(30, 1) {
            questionList[14].text().trim() == "10. Walking on an uneven surface"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[72].click()  //question 10 of pain choice 3

        and:
        waitFor(30, 1) {
            questionList[15].text().trim() == "1. Descending stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[76].click()  //question 1 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[16].text().trim() == "2. Ascending stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[80].click()  //question 2 of function choice 1

        and:
        waitFor(30, 1) {
            questionList[17].text().trim() == "3. Rising from sitting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[86].click()  //question 3 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[18].text().trim() == "4. Standing"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[92].click()  //question 4 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[19].text().trim() == "5. Bending to the floor/pick up an object"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[98].click()  //question 5 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[20].text().trim() == "6. Walking on a flat surface"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[104].click()  //question 6 of function choice 5

        and:
        waitFor(30, 1) {
            questionList[21].text().trim() == "7. Getting in/out of car"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[108].click()  //question 7 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[22].text().trim() == "8. Going shopping"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[112].click()  //question 8 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[23].text().trim() == "9. Putting on socks/stockings"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[116].click()  //question 9 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[24].text().trim() == "10. Rising from bed"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[120].click()  //question 10 of function choice 1

        and:
        waitFor(30, 1) {
            questionList[25].text().trim() == "11. Taking off socks/stockings"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[126].click()  //question 11 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[26].text().trim() == "12. Lying in bed (turning over, maintaining hip position)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[132].click()  //question 12 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[27].text().trim() == "13. Getting in/out of bath"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[138].click()  //question 13 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[28].text().trim() == "14. Sitting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[144].click()  //question 14 of function choice 5

        and:
        waitFor(30, 1) {
            questionList[29].text().trim() == "15. Getting on/off toilet"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[148].click()  //question 15 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[30].text().trim() == "16. Heavy domestic duties (moving heavy boxes, scrubbing floors, etc)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[152].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[152].click()  //question 16 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[31].text().trim() == "17. Light domestic duties (cooking, dusting, etc)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[156].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[156].click()  //question 17 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[32].text().trim() == "1. Squatting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[160].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[160].click()  //question 1 of function, sports choice 1

        and:
        waitFor(30, 1) {
            questionList[33].text().trim() == "2. Running"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[166].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[166].click()  //question 2 of function, sports choice 2

        and:
        waitFor(30, 1) {
            questionList[34].text().trim() == "3. Twisting/pivoting on loaded leg"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[172].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[172].click()  //question 3 of function, sports choice 3

        and:
        waitFor(30, 1) {
            questionList[35].text().trim() == "4. Walking on uneven surface"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[178].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[178].click()  //question 4 of function, sports choice 4

        and:
        waitFor(30, 1) {
            questionList[36].text().trim() == "1. How often are you aware of your hip problem?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[184].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[184].click()  //question 1 of quality of life choice 5

        and:
        waitFor(30, 1) {
            questionList[37].text().trim() == "2. Have you modified your life style to avoid activities potentially damaging to your hip?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[188].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[188].click()  //question 2 of quality of life choice 4

        and:
        waitFor(30, 1) {
            questionList[38].text().trim() == "3. How much are you troubled with lack of confidence in your hip?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[192].scrollIntoView(false)")
        and:
        Thread.sleep(500 as long)
        choicesList[192].click()  //question 3 of quality of life choice 3

        and:
        waitFor(30, 1) {
            questionList[39].text().trim() == "4. In general, how much difficulty do you have with your hip?"

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
            browser.at TaskIntroPage
            // at TaskIntroPage
        }
    }

    def checkAndClickKOOSTasks(){

        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == "1. Do you have swelling in your knee?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 of symptoms choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "2. Do you feel grinding, hear clicking or any other type of noise when your knee moves?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[6].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[6].click()   //question 2 of symptoms choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "3. Does your knee catch or hang up when moving?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[12].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[12].click()  //question 3 of symptoms choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "4. Can you straighten your knee fully?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[18].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[18].click()  //question 4 of symptoms choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "5. Can you bend your knee fully?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[24].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[24].click()  //question 5 of symptoms choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "6. How severe is your knee joint stiffness after first wakening in the morning?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 6 of stiffness choice 4

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "7. How severe is your knee stiffness after sitting, lying or resting later in the day?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[32].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[32].click()  //question 7 of stiffness choice 3

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "1. How often do you experience knee pain?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 1 of pain choice 2

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "2. Twisting/pivoting on your knee"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[40].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[40].click()  //question 2 of pain choice 1

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "3. Straightening knee fully"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[46].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[46].click()  //question 3 of pain choice 2

        and:
        waitFor(30, 1) {
            questionList[10].text().trim() == "4. Bending knee fully"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[52].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[52].click()  //question 4 of pain choice 3

        and:
        waitFor(30, 1) {
            questionList[11].text().trim() == "5. Walking on flat surface"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[58].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[58].click()  //question 5 of pain choice 4

        and:
        waitFor(30, 1) {
            questionList[12].text().trim() == "6. Going up or down stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[64].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[64].click()  //question 6 of pain choice 5

        and:
        waitFor(30, 1) {
            questionList[13].text().trim() == "7. At night while in bed"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[68].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[68].click()  //question 7 of pain choice 4

        and:
        waitFor(3, 1) {
            questionList[14].text().trim() == "8. Sitting or lying"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[72].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[72].click()  //question 8 of pain choice 3

        and:
        waitFor(3, 1) {
            questionList[15].text().trim() == "9. Standing upright"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[76].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[76].click()  //question 9 of pain choice 2

        and:
        waitFor(30, 1) {
            questionList[16].text().trim() == "1. Descending stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[80].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[80].click()  //question 1 of function choice 1

        and:
        waitFor(30, 1) {
            questionList[17].text().trim() == "2. Ascending stairs"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[86].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[86].click()  //question 2 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[18].text().trim() == "3. Rising from sitting"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[92].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[92].click()  //question 3 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[19].text().trim() == "4. Standing"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[98].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[98].click()  //question 4 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[20].text().trim() == "5. Bending to floor/pick up an object"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[104].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[104].click()  //question 5 of function choice 5

        and:
        waitFor(30, 1) {
            questionList[21].text().trim() == "6. Walking on flat surface"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[108].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[108].click()  //question 6 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[22].text().trim() == "7. Getting in/out of car"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[112].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[112].click()  //question 7 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[23].text().trim() == "8. Going shopping"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[116].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[116].click()  //question 8 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[24].text().trim() == "9. Putting on socks/stockings"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[120].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[120].click()  //question 9 of function choice 1

        and:
        waitFor(30, 1) {
            questionList[25].text().trim() == "10. Rising from bed"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[126].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[126].click()  //question 10 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[26].text().trim() == "11. Taking off socks/stockings"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[132].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[132].click()  //question 11 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[27].text().trim() == "12. Lying in bed (turning over, maintaining knee position)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[138].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[138].click()  //question 12 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[28].text().trim() == "13. Getting in/out of bath"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[144].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[144].click()  //question 13 of function choice 5

        and:
        waitFor(30, 1) {
            questionList[29].text().trim() == "14. Sitting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[148].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[148].click()  //question 14 of function choice 4

        and:
        waitFor(30, 1) {
            questionList[30].text().trim() == "15. Getting on/off toilet"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[152].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[152].click()  //question 15 of function choice 3

        and:
        waitFor(30, 1) {
            questionList[31].text().trim() == "16. Heavy domestic duties (moving heavy boxes, scrubbing floors, etc)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[156].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[156].click()  //question 16 of function choice 2

        and:
        waitFor(30, 1) {
            questionList[32].text().trim() == "17. Light domestic duties (cooking, dusting, etc)"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[160].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[160].click()  //question 17 of function choice 1

        and:
        waitFor(30, 1) {
            questionList[33].text().trim() == "1. Squatting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[166].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[166].click()  //question 1 of function, sports choice 2

        and:
        waitFor(30, 1) {
            questionList[34].text().trim() == "2. Running"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[172].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[172].click()  //question 2 of function, sports choice 3

        and:
        waitFor(30, 1) {
            questionList[35].text().trim() == "3. Jumping"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[178].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[178].click()  //question 3 of function, sports choice 4

        and:
        waitFor(30, 1) {
            questionList[36].text().trim() == "4. Twisting/pivoting on your injured knee"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[184].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[184].click()  //question 4 of function, sports choice 5

        and:
        waitFor(30, 1) {
            questionList[37].text().trim() == "5. Kneeling"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[188].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[188].click()  //question 5 of function, sports choice 4

        and:
        waitFor(30, 1) {
            questionList[38].text().trim() == "1. How often are you aware of your knee problem?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[192].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[192].click()  //question 1 of quality of life choice 3

        and:
        waitFor(30, 1) {
            questionList[39].text().trim() == "2. Have you modified your life style to avoid potentially damaging activities to your knee?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[196].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[196].click()  //question 2 of quality of life choice 2

        and:
        waitFor(30, 1) {
            questionList[40].text().trim() == "3. How much are you troubled with lack of confidence in your knee?"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[200].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[200].click()  //question 3 of quality of life choice 1

        and:
        waitFor(30, 1) {
            questionList[41].text().trim() == "4. In general, how much difficulty do you have with your knee?"

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
            browser.at TaskIntroPage
        }
    }

    def checkAndClickODITasks(){
        when:
        waitFor(30, 1) {
            questionList[0].text().trim() == "Section 1: Pain Intensity"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[0].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[0].click()   //question 1 choice 1

        and:
        waitFor(30, 1) {
            questionList[1].text().trim() == "Section 2: Personal Care (washing, dressing, etc.)"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[7].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[7].click()   //question 2 choice 2

        and:
        waitFor(30, 1) {
            questionList[2].text().trim() == "Section 3: Lifting"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[14].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[14].click()  //question 3 choice 3

        and:
        waitFor(30, 1) {
            questionList[3].text().trim() == "Section 4: Walking"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[21].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[21].click()  //question 4 choice 4

        and:
        waitFor(30, 1) {
            questionList[4].text().trim() == "Section 5: Sitting"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[28].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[28].click()  //question 5 choice 5

        and:
        waitFor(30, 1) {
            questionList[5].text().trim() == "Section 6: Standing"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[35].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[35].click()  //question 6 choice 6

        and:
        waitFor(30, 1) {
            questionList[6].text().trim() == "Section 7: Sleeping"

        }
        and:
        js.exec("document.getElementsByClassName('answer')[36].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[36].click()  //question 7 choice 1

        and:
        waitFor(30, 1) {
            questionList[7].text().trim() == "Section 8: Sex Life (if applicable) This question is optional."
        }
        and:
        js.exec("document.getElementsByClassName('answer')[43].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[43].click()  //question 8 choice 2

        and:
        waitFor(30, 1) {
            questionList[8].text().trim() == "Section 9: Social Life"
        }
        and:
        js.exec("document.getElementsByClassName('answer')[50].scrollIntoView(false)")

        and:
        Thread.sleep(500 as long)
        choicesList[50].click()  //question 9 choice 3

        and:
        waitFor(30, 1) {
            questionList[9].text().trim() == "Section 10: Traveling"
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
            browser.at TaskIntroPage
//            at TaskIntroPage
        }
    }


}
