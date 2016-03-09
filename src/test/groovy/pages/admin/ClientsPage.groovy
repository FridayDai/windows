package pages.admin

import geb.Page
import model.admin.ClientModel
import modules.admin.ClientModalModule
import modules.admin.ClientsTableModule

class ClientsPage extends Page {
	static url = "/clients"

	static at = { $("#add-client").size() == 1 }
	
	static content = {
		addClientButton { $("#add-client") }
        logoIcon { $(".logo") }
		clientsTable { $('#client-table').module(ClientsTableModule) }
		newClientDialog { $("#client-modal").module(ClientModalModule) }
        clientSearchInput { $("#client-search-input") }
        clientSearchBtn { $("#client-search-btn") }
	}

    def addClient(ClientModel client) {
        File logo = new File(client.logoPath)
        File favicon = new File(client.faviconPath)

        addClientButton.click()

        waitFor(3, 1) { newClientDialog.displayed }

        newClientDialog.clientName << client.clientName
        newClientDialog.subDomain << client.subDomain
        newClientDialog.pateintPortalName << client.patientPortalName
        newClientDialog.primaryColorHex << client.primaryColor
        newClientDialog.logo = logo.absolutePath
        newClientDialog.favicon = favicon.absolutePath

        newClientDialog.createButton.click()

        waitFor(60, 1) { !newClientDialog.displayed }
    }

    def search(String name) {
        waitFor(60, 1) {
            clientSearchInput << name
            clientSearchInput.value() == name
        }

        clientSearchBtn.click()
    }

    def findClientInTable(ClientModel client) {
        waitFor(5, 1) {
            clientsTable.clientItems.findAll({ it?.clientName == client.clientName}).size() == 1
        }

        clientsTable.clientItems[0]
    }

    def findClientWithId(id) {
        clientsTable.clientItems.findAll({ it.id == id})
    }

    def viewClientDetail(ClientModel client) {
        def matchedRows = findClientWithId(client.id)
        waitFor(10, 1) {
            matchedRows.size() == 1
        }

        matchedRows[0].click()
    }
}
