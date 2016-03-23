package pages.patient

import geb.Page

/**
 * Created by daiyi on 16/3/23.
 */
class HarrisTaskPage extends Page{
    static at = { title.startsWith("Ha")}

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def checkAndDoHarrisTasks(){

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
    }
}
