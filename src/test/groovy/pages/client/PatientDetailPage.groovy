package pages.client

import geb.Page
import modules.client.WarningModelModule

class PatientDetailPage extends Page {

    static at = { $(".patient-detail") }

    static content = {

        firstName { $(".name .first-name") }
        lastName { $(".name .last-name") }
        patientId { $(".id-info span") }
        email { $("#patientEmail") }
        phone { $(".phone", 0) }
        taskSentRow { $("#task-row-sent") }
        taskScheduleRow { $("#task-row-schedule") }
        sentNoItem { taskSentRow.find(".no-item") }
        pendingTask { taskSentRow.find(".pending") }
        scheduleTask { taskScheduleRow.find(".schedule").has(".numeral", text: "BASELINE") }

        archivedButton { $("button.icon-archived") }
        archivedModel { module WarningModelModule, $(".ui-dialog", 0)}

        archivedTreatmentTitle { $(".archived-treatment-title", 0) }
        logoutLink { $(".log-out") }
    }
}
