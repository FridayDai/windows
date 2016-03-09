package modules.admin

import geb.Module

class ToolsTableItemModule extends Module {
    static content = {
        cell { int index -> $("td", index) }
        id { cell(0)?.text()?.toInteger() }
        toolTitle { cell(1)?.text() }
        description { cell(2)?.text() }
        type { cell(3)?.text() }
    }
}
