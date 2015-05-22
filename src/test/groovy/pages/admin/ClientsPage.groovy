package pages.admin

import geb.Page
import modules.admin.ClientModelModule

class ClientsPage extends Page {
	static url = "/clients"

	static at = { $("#add-client") }
	
	static content = {
		addClientButton { $("#add-client") }
		newCLientModel { $("#client-modal") }
		newCLientModelM(wait: true) { module ClientModelModule, $("#client-modal") }
	}
}
