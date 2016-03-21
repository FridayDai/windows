package modules.client

import geb.Module
import modules.Component.Select2Module


class GroupModalModule extends Module {
    static content = {
        groupName { $("input", name: "name") }
        //treatmentsSelect { module(Select2Module) }
        //treatmentsSelect { $('.treatments').module(Select2Module)}
        treatmentsSelect { $('.treatments').module(Select2Module)}
    }
}
