package pages.client
import geb.Page


class InClinicPage extends Page {
    static url = "/in-clinic"
    static at = { title.startsWith("Patient") }

    static content = {
        codeInput { $(".treatment-code") }
        goButton { $(".task-go-panel .task-go-btn") }
        portalName { $(".portal-name") }
    }

    def typeCode(String code){
        when:"Type in code in codeInput"
        waitFor(30,1) {
            codeInput.displayed
        }
        and:
        codeInput.value('')
        and:
        codeInput << code
    }
    def goToInClinicTaskPage(){
        when:
        goButton.click()

        then: "Direct to question page"
        waitFor(30,1){
            browser.at InClinicTaskPage
        }
    }
}
