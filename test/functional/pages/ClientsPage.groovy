package pages

import geb.Page
import modules.ClientModelModule

class ClientsPage extends Page {
	static url = "/clients"

	static at = { $("#add-client") }
	
	static content = {
		addClientButton { $("#add-client") }
		newCLientModel { $("#client-modal") }
		newCLientModelM(wait: true) { module ClientModelModule, $("#client-modal") }
	}
}
