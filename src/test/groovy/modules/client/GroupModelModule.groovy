package modules.client

import geb.Module


class GroupModelModule extends Module{
    static content = {
        groupName { $("input", name: "name") }
        createButton { $("#group-form").next().find("button") }
    }
}
