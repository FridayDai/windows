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
		clientsTableInfo { $("#client-table_info") }

		newClientModel { module ClientModelModule, $("#client-modal") }
	}

	ClientModelModule getNewClientDialog() {
		newClientModel
	}

	ClientModelModule showNewClientDialog() {
		createClientButton.click()

		newClientModel
	}

	def getAllClients() {
		when:
		searchClientNameInput.value('')
		searchClientNameButton.click()

		then:
		waitFor(30, 1) {
			getTableLineSize() >= 10
		}
	}

	def clickPaginationButton(num) {
		$('.paginate_button', num).click()
	}

	def getTableLineSize() {
		clientsTable.find('tbody tr').size()
	}

	def getClientNameInTable(line = 0) {
		clientsTable.find('tbody tr', line).find('td', 1).text()
	}

	def getActiveStaffCountInTable(line = 0) {
		clientsTable.find('tbody tr', line).find('td', 2).text()
	}

	def getActivePatientCountInTable(line = 0) {
		clientsTable.find('tbody tr', line).find('td', 3).text()
	}

	def getActiveTreatmentCountInTable(line = 0) {
		clientsTable.find('tbody tr', line).find('td', 4).text()
	}

}


