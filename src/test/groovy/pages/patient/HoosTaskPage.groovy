package pages.patient

import geb.Page

/**
 * Created by daiyi on 16/3/23.
 */
class HoosTaskPage extends Page{
    static at = { title.startsWith("H")}

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def checkAndDoHOOSTasks(){
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
    }
}
