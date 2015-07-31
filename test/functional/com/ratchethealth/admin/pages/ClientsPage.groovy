package com.ratchethealth.admin.pages

import com.ratchethealth.admin.modules.ClientModelModule

class ClientsPage extends RatchetPage {
	static url = "/clients"
	static at = { $("#add-client").displayed }

	static content = {
		searchClientNameInput { $("#client-search-input") }
		searchClientNameButton { $("#client-search-btn") }
		createClientButton { $("#add-client") }
		clientsTable { $("#client-table") }

		newClientModel { module ClientModelModule, $("#client-modal") }
	}

	ClientModelModule getNewClientDialog() {
		newClientModel
	}

	ClientModelModule showNewClientDialog() {
		createClientButton.click()

		newClientModel
	}
}


