package modules.client

import geb.Module

class GroupsTableItemModule extends Module {
    static content = {
        cell { int index -> $("td", index) }
        id { cell(0).text()?.toInteger() }
        groupName { cell(1)?.text() }
        treatments { cell(2)?.text() }
        lastUpdated { cell(3)?.text() }
    }
}
