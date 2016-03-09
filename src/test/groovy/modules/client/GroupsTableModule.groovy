package modules.client

import geb.Module

class GroupsTableModule extends Module {
    static content = {
        groupItems { $("tbody tr").moduleList(GroupsTableItemModule)}
    }
}
