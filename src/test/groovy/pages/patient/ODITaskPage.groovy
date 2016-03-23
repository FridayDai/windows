package pages.patient

import geb.Page

/**
 * Created by daiyi on 16/3/23.
 */
class ODITaskPage extends Page{
    static at = { title.startsWith("ODI")}

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def checkAndDoODITasks(){
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
    }
}
