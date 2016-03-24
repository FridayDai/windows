package pages.patient

import geb.Page
import utils.ModelHelper

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

    def DoHarrisTasks(optionNum){
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
}
