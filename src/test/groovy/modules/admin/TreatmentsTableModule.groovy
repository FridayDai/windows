package modules.admin

import geb.Module

class TreatmentsTableModule extends Module {
    static content = {
        treatmentItems { $("tbody tr").moduleList(TreatmentTableItemModule)}
    }
}
