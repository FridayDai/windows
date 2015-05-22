package specs

import geb.error.NoNewWindowException
import geb.spock.GebReportingSpec
import pages.admin.ClientDetailPage
import pages.admin.ClientsPage
import pages.admin.LoginPage
import pages.client.StaffEmailConfirmationPage
import pages.mail.GmailAboutPage
import pages.mail.GmailAppPage
import pages.mail.GmailPasswordPage
import pages.mail.GmailSignInPage
import spock.lang.*

@Stepwise
class SmokeFunctionalSpec extends GebReportingSpec {
	@Shared IDENTIFY = System.currentTimeMillis()
	@Shared CLIENT_NAME = "AST${IDENTIFY} CN"
	@Shared SUB_DOMAIN = "ast${IDENTIFY}sd"
	@Shared PATIENT_PORTAL_NAME = "AST${IDENTIFY} PPN"
	@Shared PRIMARY_COLOR_HEX = "#9EFF9E"
	@Shared LOGO_RELATIVE_PATH = "src/test/resources/ps.png"
	@Shared FAVICON_RELATIVE_PATH = "src/test/resources/ps-favicon.png"

	@Shared AGENT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
	@Shared AGENT_FIRST_NAME = "FN+ast${IDENTIFY}"
	@Shared AGENT_LAST_NAME = "AST"

	@Shared GMAIL_WINDOW = ""
	@Shared CLIENT_PORTAL_WINDOW = ""

	static GMAIL_ACCOUNT = "ratchet.testing@gmail.com"
	static GMAIL_PASSWORD = "K6)VkqMUDy(mRseYHZ>v23zGt"

	def getAdminUrl() {
		def env = System.getProperty("env")
		"http://admin.${env}.ratchethealth.com"
	}

	@Ignore
	def "should login successfully"() {
		browser.setBaseUrl(getAdminUrl())

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
		waitFor(3, 1) { newCLientModel.displayed }
		newCLientModelM.clientName << CLIENT_NAME
		newCLientModelM.subDomain << SUB_DOMAIN
		newCLientModelM.pateintPortalName << PATIENT_PORTAL_NAME
		newCLientModelM.primaryColorHex << PRIMARY_COLOR_HEX
		newCLientModelM.logo = logo.absolutePath
		newCLientModelM.favicon = favicon.absolutePath

		newCLientModelM.createButton.click()

		waitFor(30, 1) { !newCLientModel.displayed }

		then: "New client should display one the first line of table"
		waitFor(90) {
			$("tr", 1).find("td", 1).text() == CLIENT_NAME
			$("tr", 1).find("td", 2).text() == "0"
			$("tr", 1).find("td", 3).text() == "0"
			$("tr", 1).find("td", 4).text() == "0"
		}
	}

	@Ignore
	def "go to client details page successfully"() {
		when: "At clients page"
		at ClientsPage

		and: "Click the first line of clients table"
		assert $("tr", 1).find("td", 1).text() == CLIENT_NAME
		$("tr", 1).click()

		then: "Go to client detail page"
		at ClientDetailPage
	}

	@Ignore
	def "add agent email successfully"() {
		when: "At client detail page"
		at ClientDetailPage

		and: "Click new agent button"
		agentButton.click()

		and: "Wait for agent model come up, type in agent email, agent first name and agent last name, create button clicked"
		waitFor(3, 1) { agentModel.displayed }
		agentModelModule.agentEmail << AGENT_EMAIL
		agentModelModule.agentFirstName << AGENT_FIRST_NAME
		agentModelModule.agentLastName << AGENT_LAST_NAME

		agentModelModule.createButton.click()

		waitFor(30, 1) { !agentModel.displayed }

		then: "Agent should be created and display on page"
		agentEmail == AGENT_EMAIL
		agentFistName == AGENT_FIRST_NAME
		agentLastName == AGENT_LAST_NAME
	}

	def getGmailUrl() {
		"https://www.gmail.com"
	}

//	@Ignore
	def "invite email should received"() {
		browser.setBaseUrl(getGmailUrl())

		when: "Go to gmail about page"
		to GmailAboutPage

		and: "Click sign in link at about page"
		signInLink.click()

		then: "At gmail sign in page"
		at GmailSignInPage

		when: "Type in email account and click next button"
		emailInput << GMAIL_ACCOUNT

		nextButton.click()

		then: "At gmail password page"
		at GmailPasswordPage

		when: "Type in email password and click sign in button"
		waitFor(3, 1) { passwordInput.displayed }

		passwordInput << GMAIL_PASSWORD

		signInButton.click()

		then: "Log into gmail successfully"
		waitFor(30, 1) {
			at GmailAppPage
		}

		waitFor(300, 1) {
//			TODO: change back
//			$('td', text: contains(AGENT_FIRST_NAME))
			$("table").find("td", text: contains("FN+ast")).size() >= 1
		}
	}

	def getClientDomain() {
		def env = System.getProperty("env")
		"client.${env}.ratchethealth.com"
	}

	def switchToWindow(String window) {
		driver.switchTo().window(window)
	}

	def switchToNewWindow(Closure windowOpeningBlock) {
		def originalWindows = availableWindows
		windowOpeningBlock.call()

		waitFor { (availableWindows - originalWindows).size() == 1 }
		def newWindows = (availableWindows - originalWindows) as List

		if (newWindows.size() != 1) {
			def message = newWindows ? 'There has been more than one window opened' : 'No new window has been opened'
			throw new NoNewWindowException(message)
		}
		switchToWindow(newWindows.first())
	}

	def "direct to email confirmation page successfully"() {
		when: "Click the line of email to view details"
		$("table").find("td", text: contains("FN+ast"), 0).click()

		waitFor(20, 1) {
			$('a', href: contains(getClientDomain())).displayed
		}

		GMAIL_WINDOW = currentWindow
		switchToNewWindow {
			$('a', href: contains(getClientDomain())).click()
		}


		then: "Direct to staff email confirmation page"
		waitFor(10, 1) {
			at StaffEmailConfirmationPage
		}
	}

	def "activate agent successfully"() {
		when:
		waitFor(10, 1) { passwordInput.displayed }
		CLIENT_PORTAL_WINDOW = currentWindow

		passwordInput << "hello"

		switchToWindow(GMAIL_WINDOW)

		then:
		waitFor(100) {
			at GmailAppPage
		}
	}

	@Ignore
	def "should logout successfully"() {

	}
}
