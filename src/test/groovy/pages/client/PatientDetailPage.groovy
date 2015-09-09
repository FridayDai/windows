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
        taskActiveRow { $("#task-row-active") }
        taskScheduleRow { $("#task-row-schedule") }
        sentNoItem { taskScheduleRow.find(".no-item") }
        pendingTask { taskActiveRow.find(".pending").has(".numeral", text: "BASELINE") }
        scheduleTask { taskScheduleRow.find(".schedule").has(".numeral", text: "BASELINE") }

        moreButton { $("#menu .drop-down-toggle")}
        archivedButton { $("#menu .archived-active") }
        archivedModel { module WarningModelModule, $(".ui-dialog", 0)}

        archivedTreatmentTitle { $(".archived-treatment-title", 0) }
        logoutLink { $(".log-out") }
    }
}
