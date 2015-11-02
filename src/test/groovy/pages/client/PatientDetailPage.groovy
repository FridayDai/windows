package pages.client

import geb.Page
import modules.client.WarningModelModule
import modules.client.GenerateCodeModule

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
        taskClosedRow { $("#task-row-closed") }
        sentNoItem { taskScheduleRow.find(".no-item") }
        pendingTask { taskActiveRow.find(".pending") }
        scheduleTask { taskScheduleRow.find(".schedule") }

        completeTaskBox { taskClosedRow.find('.complete') }
        DASHCompleteTaskbox { completeTaskBox.has('.item-title', text: 'DASH') }
        QuickDASHCompleteTaskbox { completeTaskBox.has('.item-title', text: 'QuickDASH') }
        ODICompleteTaskbox { completeTaskBox.has('.item-title', text: 'ODI') }
        NRSBackCompleteTaskbox { completeTaskBox.has('.item-title', text: 'NRS-BACK') }
        NDICompleteTaskbox { completeTaskBox.has('.item-title', text: 'NDI') }
        NRSNeckCompleteTaskbox { completeTaskBox.has('.item-title', text: 'NRS-NECK') }
        HOOSCompleteTaskbox { completeTaskBox.has('.item-title', text: 'HOOS') }
        KOOSCompleteTaskbox { completeTaskBox.has('.item-title', text: 'KOOS') }
        FairleyNasalSymptomCompleteTaskbox { completeTaskBox.has('.item-title', text: 'Fairley Nasal Symptom') }
        HarrisHipScoreCompleteTaskbox { completeTaskBox.has('.item-title', text: 'Harris Hip Score') }

        moreButton { $("#menu .drop-down-toggle")}
        archivedButton { $("#menu .archived-active") }
        archivedModel { module WarningModelModule, $(".ui-dialog", 0)}

        generateCodeButton { $("#generateCode") }
        generateCodeModel { module GenerateCodeModule, $(".ui-dialog").has("#generate-code-dialog") }
        codeLink { $(".link-to-patient") }
        patientName { $(".identify") }
        treatmentCode { $(".code") }


        archivedTreatmentTitle { $(".archived-treatment-title", 0) }
        logoutLink { $("#logout") }

    }
}
