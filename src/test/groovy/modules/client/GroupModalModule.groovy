package modules.client

import geb.Module
import modules.Component.Select2Module


class GroupModalModule extends Module {
    static content = {
        groupName { $("input", name: "name") }
        treatmentsSelect { $('.treatments').module(Select2Module)}
        createButton { $("#group-form").next().find("button") }
    }
}
