package modules.client

import geb.Module

class NewPatientModelModule extends Module {

    static content = {
        patientFirstName { $("#firstName") }
        patientLastName { $("#lastName") }
        phoneNumber { $("#phoneNumber") }
        email { $("#email") }

        caregiverFirstName { $("#emergency-firstName") }
        caregiverLastName { $("#emergency-lastName") }
        caregiverEmail { $("#emergency-email") }
        isPermission {$("input", name: "permissionConfirm")}

        relationshipSelect { $("#relationship") }
        groupSelect { $("#selectGroup") }
        providerSelect { $("#selectStaffs") }
        treatmentSelect { $("#selectTreatment") }
        surgeryDateSelect { $("#surgeryTime") }



//        results { $(".ui-autocomplete", style: containsWord("block"))}

        //if results don't change, we need set each.




        createButton { $("#table-form").next().find("button") }
    }

}
