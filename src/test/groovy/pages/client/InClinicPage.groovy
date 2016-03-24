package pages.client
import geb.Page


class InClinicPage extends Page {
    static url = "/in-clinic"
    static at = { title.startsWith("Ratchet Health Patient Portal") }

    static content = {
        codeInput { $(".treatment-code") }
        goButton { $(".task-go-panel .task-go-btn") }
        portalName { $(".portal-name") }
    }

    def typeCode(code){
        and:"Type in code in codeInput"
        waitFor(30,1) {
            codeInput.displayed
        }
        and:
        codeInput.value('')
        Thread.sleep(1000)
        and:
        codeInput << code
    }
    def goToInClinicTaskPage(){
        and:
        waitFor(30 ,1) {
            goButton.displayed
        }

        and:
        goButton.click()
    }
}
