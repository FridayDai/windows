package modules.admin

import geb.Module

class TasksTableModule extends Module {
    static content = {
        taskItems { $("tbody tr").moduleList(TasksTableItemModule)}
    }
}
