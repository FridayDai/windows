package pages.patient

import geb.Page

/**
 * Created by daiyi on 16/3/23.
 */
class FairleyTaskPage extends Page{
    static at = { title.startsWith("Fairley")}

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def checkAndDoFairleyTasks(){

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

    }
}
