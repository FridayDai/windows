package pages.client

import geb.Page

class PatientDetailPage extends Page {

    static at = { $(".patient-detail") }

    static content = {

        firstName { $(".name .first-name") }
        lastName { $(".name .last-name") }
        patientId { $(".id-info span") }
        email { $("#patientEmail") }
        phone { $(".phone", 0) }
        taskSent{ $("#task-row-sent") }
        pendingTask {taskSent.find(".box-item.pending")}

        logoutLink { $(".log-out") }
    }
}
