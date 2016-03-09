package modules.admin

import geb.Module

class ClientsTableModule extends Module {
    static content = {
        clientItems { $("tbody tr").moduleList(ClientsTableItemModule)}
    }
}
