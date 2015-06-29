package modules.client

import geb.Module


class GroupModelModule extends Module{
    static content = {
        groupName { $("input", name: "groupName") }
        createButton { $("#group-form").next().find("button") }
    }
}
