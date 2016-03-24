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
}
