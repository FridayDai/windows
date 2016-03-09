package modules.admin

import geb.Module

class ToolsTableModule extends Module {
    static content = {
        toolItems { $("tbody tr").moduleList(ToolsTableItemModule)}
    }
}
