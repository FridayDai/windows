package specs

import geb.spock.GebReportingSpec
import pages.ClientDetailPage
import pages.ClientsPage
import pages.LoginPage
import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class SmokeFunctionalSpec extends GebReportingSpec {

	def IDENTIFY = System.currentTimeMillis()
	def CLIENT_NAME = "AST${IDENTIFY} CN"
	def SUB_DOMAIN = "ast${IDENTIFY}sd"
	def PATIENT_PORTAL_NAME = "AST${IDENTIFY} PPN"
	def PRIMARY_COLOR_HEX = "#9EFF9E"
	def LOGO_RELATIVE_PATH = "test/functional/ps.png"
	def FAVICON_RELATIVE_PATH = "test/functional/ps-favicon.png"

	def AGENT_EMAIL = "albert.zhang+ast${IDENTIFY}@xplusz.com"
	def AGENT_FIRST_NAME = "albert+ast${IDENTIFY}"
	def AGENT_LAST_NAME = "zhang"

	def "should login successfully"() {
		when: "Go to login page"
		to LoginPage

		and: "Type in admin email and password, also login button clicked"
		emailInput << "admin@ratchethealth.com"
		passwordInput << "qEWD2LDvE9MWrR"
		loginButton.click()

		then: "At clients page now"
		at ClientsPage
	}

	@Ignore
	def "add client successfully"() {
		File logo = new File(LOGO_RELATIVE_PATH)
		File favicon = new File(FAVICON_RELATIVE_PATH)

		when: "At clients page now"
		at ClientsPage

		and: "Click add client button"
		addClientButton.click()

		and: "Wait for new client model come up, type in client name, sub domain, patient portal name, etc and click create button"
		waitFor { newCLientModel.displayed }
		newCLientModelModule.clientName << CLIENT_NAME
		newCLientModelModule.subDomain << SUB_DOMAIN
		newCLientModelModule.pateintPortalName << PATIENT_PORTAL_NAME
		newCLientModelModule.primaryColorHex << PRIMARY_COLOR_HEX
		newCLientModelModule.logo = logo.absolutePath
		newCLientModelModule.favicon = favicon.absolutePath

		newCLientModelModule.createButton.click()

		waitFor { !newCLientModel.displayed }

		then: "New client should display one the first line of table"
		$("tr", 1).find("td", 1).text() == CLIENT_NAME
		$("tr", 1).find("td", 2).text() == "0"
		$("tr", 1).find("td", 3).text() == "0"
		$("tr", 1).find("td", 4).text() == "0"
	}

	def "go to client details page successfully"() {
		when: "At clients page"
		at ClientsPage

		and: "Click the first line of clients table"
//		assert $("tr", 1).find("td", 1).text() == CLIENT_NAME
		$("tr", 1).click()

		then: "Go to client detail page"
		at ClientDetailPage
	}

	def "add agent email successfully"() {
		when: "At client detail page"
		at ClientDetailPage

		and: "Click new agent button"
		agentButton.click()

		and: "Wait for agent model come up, type in agent email, agent first name and agent last name, create button clicked"
		waitFor { agentModel.displayed }
		agentModelModule.agentEmail << AGENT_EMAIL
		agentModelModule.agentFirstName << AGENT_FIRST_NAME
		agentModelModule.agentLastName << AGENT_LAST_NAME

		agentModelModule.createButton.click()

		waitFor { !agentModel.displayed }

		then: "Agent should be created and display on page"
		agentEmail == AGENT_EMAIL
		agentFistName == AGENT_FIRST_NAME
		agentLastName == AGENT_LAST_NAME
	}

	@Ignore
	def "invite email should received"() {}

	@Ignore
	def "should logout successfully"() {

	}
}
