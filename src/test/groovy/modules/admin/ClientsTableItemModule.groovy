package modules.admin

import geb.Module

class ClientsTableItemModule extends Module {
    static content = {
        cell { int index -> $("td", index) }
        id { cell(0)?.text().toInteger() }
        clientName { cell(1)?.text() }
        activeStaff { cell(2)?.text().toInteger() }
        activePatient { cell(3)?.text().toInteger() }
        activeTreatment { cell(4)?.text().toInteger() }
    }
}
