package pages.patient

import geb.Page

class TaskCompletePage extends Page {

//    static at = {$(".para1").text() == "COMPLETED!"}
    static at = { title.endsWith("Portal") }

    static content = {
        scores { $(".score-header") }
        scoresItem { scores.find(".capitalize") }
    }

}
