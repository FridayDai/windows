package pages.client

import geb.Page
import modules.client.NewPatientModelModule
import modules.client.NoEmailWarningModule
import modules.client.PatientIdModelModule

class PatientsPage extends Page {

    static url = '/patients'

    static at = { title.startsWith("Patients") }

    static content = {
        accountTab { $(".icon-account") }
        groupTab {$(".icon-group")}

        addPatientButton { $("#add-patient") }
        patientIdModel { module PatientIdModelModule, $(".ui-dialog").has("#patient-id-form") }
        newPatientModel { module NewPatientModelModule, $(".ui-dialog").has("#table-form") }
        noEmailWarningModel { module NoEmailWarningModule, $(".ui-dialog").has("#ui-id-19") }

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

        datepicker { $("#ui-datepicker-div") }
        nextMonthButton { datepicker.find("a.ui-datepicker-next", 0) }
//        datepickerDate { datepicker.find("a.ui-state-highlight", 0) }
        datepickerDate { datepicker.find("a.ui-state-default", 0) }
    }


}
