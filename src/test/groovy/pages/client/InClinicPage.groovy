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
}
