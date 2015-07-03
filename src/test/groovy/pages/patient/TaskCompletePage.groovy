package pages.patient

import geb.Page

class TaskCompletePage extends Page {

    static at = {$(".para1").text() == "COMPLETED!"}

    static content = {
        scores { $(".score-header") }
    }

}
