package pages.client
import geb.Page

/**
 * Created by thomas on 10/27/15.
 */
class InClinicTaskPage extends Page {

    static at = { title.endsWith("Portal") }

    static content = {
        taskStartButton { $(".task-start-btn") }
    }
}
