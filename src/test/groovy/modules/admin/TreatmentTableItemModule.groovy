package modules.admin

import geb.Module

class TreatmentTableItemModule extends Module {
    static content = {
        cell { int index -> $("td", index) }
        id { cell(0)?.text()?.toInteger() }
        treatmentTitle { cell(1)?.text() }
        templateTitle { cell(2)?.text() }
        active { cell(3).text()?.toInteger() }
        description { cell(4)?.text() }
        status { cell(5)?.text() }
    }
}
