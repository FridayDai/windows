package modules.client

import geb.Module

class PatientIdModelModule extends Module{
    static content = {
        patientid { $("#new-patient-id") }
        createButton { $("#patient-id-form").next().find("button") }
    }

}
