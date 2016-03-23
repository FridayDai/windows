package pages.client
import geb.Page
import pages.patient.TaskIntroPage

/**
 * Created by thomas on 10/27/15.
 */
class InClinicTaskPage extends Page {

    static at = { title.endsWith("Portal") }

    static content = {
        taskStartButton { $(".task-start-btn") }
    }

    def taskStartClick(){
        when:
        waitFor(30,1){
            taskStartButton.displayed
        }
        and:
        taskStartButton.click()

    }
    def clickStartButton(){
        when:
        taskStartButton.click()
        then:
        waitFor(30,1) {
            browser.at TaskIntroPage
        }
    }

}
