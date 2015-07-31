package com.ratchethealth.admin.specs

import com.ratchethealth.admin.pages.ClientsPage
import com.ratchethealth.admin.pages.LoginPage
import geb.spock.GebReportingSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class ClientsFunctionalSpec extends GebReportingSpec {
	@Shared ClientsPage clientsPage

	static REQUIRE_ERROR_MESSAGE = 'This field is required.'

	def setupSpec() {
		def loginPage = to(LoginPage)

		loginPage.login()
	}

	def "check all contents displayed"() {
		when:
		clientsPage = to ClientsPage

		then:
		clientsPage.searchClientNameInput.displayed
		clientsPage.searchClientNameButton.displayed
		clientsPage.createClientButton.displayed
		clientsPage.clientsTable.displayed
	}

	def "show new client dialog and check contents in it and check close button"() {
		when:
		def newClientDialog = clientsPage.showNewClientDialog()

		then:
		waitFor(3, 1) {
			newClientDialog.displayed
		}

		newClientDialog.title.text() == 'New Client'
		newClientDialog.closeButton.displayed
		newClientDialog.cancelButton.displayed
		newClientDialog.createButton.displayed

		newClientDialog.clientName.displayed
		newClientDialog.clientName.text() == ''

		newClientDialog.subDomain.displayed
		newClientDialog.subDomain.text() == ''

		newClientDialog.patientPortalName.displayed
		newClientDialog.patientPortalName.text() == ''

		newClientDialog.primaryColorHex.displayed
		newClientDialog.primaryColorHex.text() == ''

		when:
		newClientDialog.closeButton.click()

		then:
		waitFor(3, 1) {
			!clientsPage.newClientModel.displayed
		}
	}

	def "click cancel button in new client dialog will close it"() {
		when:
		def newClientDialog = clientsPage.showNewClientDialog()

		then:
		waitFor(3, 1) {
			newClientDialog.displayed
		}

		when:
		newClientDialog.closeButton.click()

		then:
		waitFor(3, 1) {
			!newClientDialog.displayed
		}
	}

	def "check required validation for all fields"() {
		when:
		def newClientDialog = clientsPage.showNewClientDialog()

		then:
		waitFor(3, 1) {
			newClientDialog.displayed
		}

		when:
		newClientDialog.createButton.click()

		then:
		waitFor(3, 1) {
			newClientDialog.displayed
		}

		newClientDialog.clientName.parents('.form-group').hasClass('has-error')
		newClientDialog.clientName.next().text() == REQUIRE_ERROR_MESSAGE

		newClientDialog.subDomain.parents('.form-group').hasClass('has-error')
		newClientDialog.subDomain.next().text() == REQUIRE_ERROR_MESSAGE

		newClientDialog.patientPortalName.parents('.form-group').hasClass('has-error')
		newClientDialog.patientPortalName.next().text() == REQUIRE_ERROR_MESSAGE

		newClientDialog.primaryColorHex.parents('.form-group').hasClass('has-error')
		newClientDialog.primaryColorHex.next().text() == REQUIRE_ERROR_MESSAGE

		newClientDialog.logo.parents('.form-group').hasClass('has-error')
		newClientDialog.logo.next().text() == REQUIRE_ERROR_MESSAGE

		newClientDialog.favicon.parents('.form-group').hasClass('has-error')
		newClientDialog.favicon.next().text() == REQUIRE_ERROR_MESSAGE
	}
}
