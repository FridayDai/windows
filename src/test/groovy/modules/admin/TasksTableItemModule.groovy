package modules.admin

import geb.Module

class TasksTableItemModule extends Module {
    static content = {
        cell { int index -> $("td", index) }
        id { cell(0)?.text()?.toInteger() }
        toolTitle { cell(1)?.text() }
        description { cell(2)?.text() }
        toolType { cell(3)?.text() }
        sendTime { cell(4)?.text() }
        dueTime { cell(5)?.text() }
    }
}
