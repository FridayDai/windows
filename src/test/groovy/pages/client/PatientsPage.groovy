package pages.client

import geb.Page
import model.PatientModel
import modules.client.NewPatientModelModule
import modules.client.NoEmailWarningModule
import modules.client.PatientIdModelModule

import java.awt.Window

class PatientsPage extends Page {

    static url = '/patients'

    static at = { title.startsWith("Patients") }

    static content = {
        accountTab { $(".icon-account") }
        groupTab {$(".icon-group")}

        addPatientButton { $("#add-patient") }
        patientIdModel {  $(".ui-dialog").has("#patient-id-form").module PatientIdModelModule}
        newPatientModel { $(".ui-dialog").has("#table-form").module NewPatientModelModule }
        noEmailWarningModel { $(".ui-dialog").has("#ui-id-19").module NoEmailWarningModule }

        firstLine { $("tbody tr", 0) }
        searchInput { $("#search-input") }
        searchButton { $("#search-btn") }
        patientId { $(".source-id") }
        patientsTable { $("#patientsTable") }
        patientName { $("#patientsTable td").eq(1) }
        patientPhone { $("#patientsTable td").eq(3) }

        results { $(".ui-autocomplete").findAll { it.displayed } }
        groupFirstResult { results.find("li", 0) }
        providerFirstResult { results.find("li", 0) }
        treatmentFirstResult { results.find("li", 0) }
        treatmentSecondResult { results.find("li", 1) }
        relationshipFirstResult { results.find("li", 0) }

        //relationshipResults {$("#ui-id-24")}
        //groupResults{ $("#ui-id-23")}
        surgeryDateSelect{ $("#surgeryTime") }
        datepicker { $("#ui-datepicker-div") }
        nextMonthButton { datepicker.find(".ui-datepicker-next", 0) }
        //datepickerDate { datepicker.find("a.ui-state-highlight", 0) }
        datepickerDate { datepicker.find("a.ui-state-default", 0) }
        //datepickerDate { $(".ui-datepicker-calendar")}


    }

    def goToGroupsPage() {
        when: "Click group tab in navigation panel"
        groupTab.click()

        then: "Direct to groups page"
        waitFor(30, 1) {
            browser.at GroupsPage
        }
    }

    def addNewPatient(PatientModel patient) {
        when: "Wait add patient button to displayed"
        waitFor(30, 1) { addPatientButton.displayed }

        and: "Click add patient button"
        addPatientButton.click()

        and: "Wait for treatment model come up"
        waitFor(30, 1) { patientIdModel.displayed }

        and: "Type in patient id"
        patientIdModel.patientid << patient.id

        and: "Click patient id model create button"
        patientIdModel.createButton.click()

        and: "Wait for agent model disappear"
        waitFor(30, 1) { newPatientModel.displayed }

        and: "Type in patient basic information"
        Thread.sleep(1000 as long)
        newPatientModel.patientFirstName << patient.firstName

        Thread.sleep(1000 as long)
        newPatientModel.patientLastName << patient.lastName

        Thread.sleep(1000 as long)
        newPatientModel.phoneNumber << patient.phoneNumber

        Thread.sleep(1000 as long)
        newPatientModel.email << patient.email

        and: "Type in care giver basic information"
        Thread.sleep(1000 as long)
        newPatientModel.caregiverFirstName << patient.emergencyFirstName

        Thread.sleep(1000 as long)
        newPatientModel.caregiverLastName << patient.emergencyLastName

        Thread.sleep(1000 as long)
        newPatientModel.caregiverEmail << patient.emergencyEmail
        js.exec('window.scrollBy(0, 500)')

        Thread.sleep(2000)
        newPatientModel.relationshipSelect.next().click()

        waitFor(30, 1) { relationshipFirstResult.displayed }

        js.exec '$("#ui-id-8").find("li")[0].click()'
        //relationshipFirstResult.click()

        Thread.sleep(1000)
        newPatientModel.isPermission.value(true)

        and: "Choose group"
        newPatientModel.groupSelect.next().click()

        Thread.sleep(1000)
        waitFor(30, 1) { groupFirstResult.displayed }

        js.exec '$("#ui-id-9").find("li")[0].click()'
        //groupFirstResult.click()


        and: "Choose provider"
        newPatientModel.providerSelect.next().click()

        Thread.sleep(2000)
        waitFor(30, 1) { providerFirstResult.displayed }
        js.exec '$("#ui-id-10").find("li")[0].click()'
        //providerFirstResult.click()

        and: "Choose treatment"
        newPatientModel.treatmentSelect.next().click()

        Thread.sleep(2000)
        waitFor(30, 1) { treatmentFirstResult.displayed }
        js.exec '$("#ui-id-11").find("li")[0].click()'
        //treatmentFirstResult.click()

        and: "Choose surgery date"
        //newPatientModel.surgeryDateSelect.click()
        Thread.sleep(3000 as long)
        //这里一定要点两次才能成功,我也不知道为什么
        js.exec '$("#surgeryTime").focus().click()'
        js.exec '$("#surgeryTime").focus().click()'
        //surgeryDateSelect.click()

        Thread.sleep(2000)
        waitFor(30, 1) { datepicker.displayed }

        nextMonthButton.click()

        Thread.sleep(2000)
        datepickerDate.click()

        Thread.sleep(1000)
        and: "Click new patient create button"
        newPatientModel.createButton.click()

        then: "Treatment should created and displayed on page"
        waitFor(30, 1) {
            browser.at PatientDetailPage
        }
    }
    def goToPatientDetailPage(){
        when: "Click first line of table"
        firstLine.click()

        then:
        waitFor(30,1){
            browser.at PatientDetailPage
        }

    }

}
