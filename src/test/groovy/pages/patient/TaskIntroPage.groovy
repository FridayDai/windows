package pages.patient

import geb.Page

class TaskIntroPage extends Page {

    static at = { $(".task-done-btn").value() == "I'm Done" }

    static content = {
        choicesList { $(".rc-radio") }
        doneButton { $("input", type: "submit") }
    }
}
