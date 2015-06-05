package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
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
class AdminSmokeFunctionalSpec extends RatchetSmokeFunctionalSpec {
	@Shared IDENTIFY
	@Shared CLIENT_NAME
	@Shared SUB_DOMAIN
	@Shared PATIENT_PORTAL_NAME
	@Shared PRIMARY_COLOR_HEX
	@Shared LOGO_RELATIVE_PATH
	@Shared FAVICON_RELATIVE_PATH

	@Shared AGENT_EMAIL
	@Shared AGENT_FIRST_NAME
	@Shared AGENT_LAST_NAME

	@Shared GMAIL_WINDOW

	static GMAIL_ACCOUNT = "ratchet.testing@gmail.com"
	static GMAIL_PASSWORD = "K6)VkqMUDy(mRseYHZ>v23zGt"

	def setupSpec() {
		IDENTIFY = System.currentTimeMillis()
		CLIENT_NAME = "AST${IDENTIFY} CN"
		SUB_DOMAIN = "ast${IDENTIFY}sd"
		PATIENT_PORTAL_NAME = "AST${IDENTIFY} PPN"
		PRIMARY_COLOR_HEX = "#9EFF9E"
		LOGO_RELATIVE_PATH = "src/test/resources/ps.png"
		FAVICON_RELATIVE_PATH = "src/test/resources/ps-favicon.png"

		AGENT_EMAIL = "ratchet.testing+ast${IDENTIFY}@gmail.com"
		AGENT_FIRST_NAME = "FN+ast${IDENTIFY}"
		AGENT_LAST_NAME = "AST"

		GMAIL_WINDOW = ""

		def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
		def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
		def db = client.getDB('ratchet-tests');

		db.smoking.drop()
		db.smoking << [name: 'IDENTIFY', value: IDENTIFY]
	}

//	@Ignore
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

//	@Ignore
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

//	@Ignore
	def "go to client details page successfully"() {
		when: "At clients page"
		at ClientsPage

		and: "Click the first line of clients table"
		assert $("tr", 1).find("td", 1).text() == CLIENT_NAME
		$("tr", 1).click()

		then: "Go to client detail page"
		at ClientDetailPage
	}

//	@Ignore
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

	//	@Ignore
	def "add automated treatment successfully"() {
	}

	//	@Ignore
	def "go to automated treatment page successfully"() {}

	//	@Ignore
	def "add NDI tool successfully"() {}

	//	@Ignore
	def "add NRS-NECK tool successfully"() {}

	//	@Ignore
	def "add NRS-BACK tool successfully"() {}

	//	@Ignore
	def "add DASH tool successfully"() {}

	//	@Ignore
	def "add QuickDASH tool successfully"() {}

	//	@Ignore
	def "add ODI tool successfully"() {}

	//	@Ignore
	def "add NDI immediate task successfully"() {}

	//	@Ignore
	def "add NRS-NECK immediate task successfully"() {}

	//	@Ignore
	def "add NRS-BACK immediate task successfully"() {}

	//	@Ignore
	def "add DASH immediate task successfully"() {}

	//	@Ignore
	def "add QuickDASH immediate task successfully"() {}

	//	@Ignore
	def "add ODI immediate task successfully"() {}

	//	@Ignore
	def "logout successfully"() {}

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
			$('td', text: contains(AGENT_FIRST_NAME))
		}
	}
}
