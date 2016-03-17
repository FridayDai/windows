package pages.client

import geb.Browser
import geb.Page
import model.PatientModel
import modules.client.WarningModelModule
import modules.client.GenerateCodeModule
import spock.lang.Shared

class PatientDetailPage extends Page {
    @Shared WINDOW
    def CODE = ''

    //static at = { $(".patient-detail") }
    static at = { title.endsWith("Ratchet Health")}
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
        archivedModel { $(".ui-dialog", 0).module WarningModelModule}

        generateCodeButton { $("#generateCode") }
        generateCodeModel { $(".ui-dialog").has("#generate-code-dialog").module GenerateCodeModule }
        doneButton { $(".ui-button-text", text: 'Done') }

        codeLink { $(".link-to-patient") }
        patientName { $("#menu .client-name") }
        treatmentCode { $(".code") }
        patientButton { $("#menu .icon-patient") }
        treatmentBox { $("#task-row-active") }
        treatmentTitle { $(".archived-treatment-title") }

        patientTable { $("#ui-id-4") }
        archivedTreatmentTitle { $(".archived-treatment-title", 0) }
        logoutLink { $("#logout") }

    }

    def checkPatientDetail(PatientModel patient){
        when: "Check patient info"
        waitFor(15,1){
            patientTable.displayed
        }

        then:
        firstName.value() == patient.firstName
        lastName.value() == patient.lastName
        patientId.text() == patient.id
        email.text().trim() == patient.email
        phone.text().replaceAll("[^0-9]", "") == patient.phoneNumber

        waitFor(60, 1) {
            sentNoItem.displayed
        }
        then: "Check schedule task in schedule items"
        waitFor(60, 1) {
            pendingTask.size() >= 0
        }
    }

    def logout(){
        when: "Click logout link"
        logoutLink.click()

        then: "redirect to login page"
        waitFor(10, 1) {
            browser.at LoginPage
        }
    }

    def checkDashScore(){
        when:
        waitFor(30,1) { DASHCompleteTaskbox.displayed }
        then:
        waitFor(30, 1) {
            DASHCompleteTaskbox.find('.score').text().trim() == '50.83\nTotal Result'
        }
    }

    def checkNDIScore(){
        when:
        waitFor(30,1) { NDICompleteTaskbox.displayed }
        then:
        waitFor(30, 1) {
            NDICompleteTaskbox.find('.score').text() == '42.0\nTotal Result'
        }
    }
    def checkNRSBACKScore(){
        when:
        waitFor(30,1){
            NRSBackCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            NRSBackCompleteTaskbox.find('.score')*.text() == ['5\nBack Result',	'5\nLeg Result']
        }
    }
    def checkNRSNECKScore(){
        when:
        waitFor(30,1) {
            NRSNeckCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            NRSNeckCompleteTaskbox.find('.score')*.text() == ['5\nNeck Result', '5\nArm Result']
        }
    }
    def checkQuickDASHScore(){
        when:
        waitFor(30,1) {
            QuickDASHCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            QuickDASHCompleteTaskbox.find('.score').text() == '43.18\nTotal Result'
        }
    }

    def checkFairleyScore(){
        when:
        waitFor(30,1) {
            FairleyNasalSymptomCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            FairleyNasalSymptomCompleteTaskbox.find('.score')*.text() == ['26\nTotal Result', '2\nAntibiotics']
        }
    }

    def generateCode(){
        when: "Wait for generateCode button displayed"
        waitFor(10,1){
            generateCodeButton.displayed
        }

        Thread.sleep(2000)

        and:
        generateCodeButton.click()

        and:
        waitFor(30,1){
            generateCodeModel.displayed
        }

        CODE = treatmentCode.text()

        Thread.sleep(1000)
    }

    def clickURL(){
        when:
        waitFor(30,1) {
            codeLink.displayed
        }

        then:
        withNewWindow({ codeLink.click() },  wait: false, close: false){
            def WINDOW = Browser.getCurrentWindow()
            title == "Patient Portal"
        }
    }

    def checkHarrisScore(){
        when:
        waitFor(30, 1) {
            HarrisHipScoreCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            HarrisHipScoreCompleteTaskbox.find('.score').text() == '62.0\nTotal Result'
        }
    }
    def checkHOOSScore(){
        when:
        waitFor(30, 1) {
            HOOSCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            HOOSCompleteTaskbox.find('.score')*.text() == [
                    '50\nSymptoms',
                    '48\nPain',
                    '51\nADL',
                    '63\nSport/Rec',
                    '38\nQOL'
            ]
        }
    }

    def checkKOOSScore(){
        when:
        waitFor(30, 1) {
            KOOSCompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            KOOSCompleteTaskbox.find('.score')*.text() == [
                    '46\nSymptoms',
                    '53\nPain',
                    '53\nADL',
                    '35\nSport/Rec',
                    '75\nQOL'
            ]
        }
    }

    def checkODIScore(){
        when:
        waitFor(30, 1) {
            ODICompleteTaskbox.displayed
        }
        then:
        waitFor(30, 1) {
            ODICompleteTaskbox.find('.score').text() == '42.0\nTotal Result'
        }
    }

    def archiveFile(){
        when: "Click to archived treatment"

        waitFor(30, 1) {
            moreButton.displayed
        }
        moreButton.click()

        and:
        waitFor(10, 1) {
            archivedButton.displayed
        }
        archivedButton.click()

        and: "Archived model display"
        waitFor(15, 1) {
            archivedModel.displayed
        }

        and: "Click Archieve to agree"
        waitFor(10, 1) { archivedModel.agreeButton.displayed }

        Thread.sleep(2000 as long)
        archivedModel.agreeButton.click()

        then: "Check archived treatment title"
        waitFor(50, 1) {
            browser.at PatientDetailPage
        }

//        waitFor(100, 1) {
//            archivedTreatmentTitle.displayed
//        }
    }



}
