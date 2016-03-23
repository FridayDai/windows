package pages.patient

import geb.Page
import utils.ModelHelper

/**
 * Created by daiyi on 16/3/23.
 */
class NRSNECKTaskPage extends Page{
    static at = {title.startsWith("NRS-NECK")}

    static content = {
        questionList { $(".question") }
        answerList { $(".answer") }
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
        choiceList { $(".answer").find('span') }
    }

    def DoNRSNECKTasks(optionNum){
        js.exec(ModelHelper.scroll("answer", optionNum))

        Thread.sleep(500 as long)
        choicesList[optionNum].click()
    }

    def clickDone(){
        js.exec('window.scrollBy(0, 500)')
        and:
        waitFor(30, 1){
            doneButton.displayed
        }
        and:
        doneButton.click()
    }
    //questionList[0].text().trim() == 'On a scale from 0 to 10, with 0 being "no pain" and 10 being the "most severe pain", what number would you give your neck pain right now?'
}
