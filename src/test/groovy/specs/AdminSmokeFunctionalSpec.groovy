package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.admin.ClientDetailPage
import pages.admin.ClientsPage
import pages.admin.LoginPage
import pages.admin.ProfilePage
import pages.admin.TreatmentPage
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

	@Shared TREATMENT_TITLE

	@Shared GMAIL_WINDOW

	static ADMIN_ACCOUNT = "admin@ratchethealth.com"
	static ADMIN_PASSWORD = "qEWD2LDvE9MWrR"

	static ACTIVATE_EMAIL_TITLE = "Activate your Ratchet Health Account!"

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

		TREATMENT_TITLE = "treatment${IDENTIFY}"

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

		and: "Type in admin email and password"
		emailInput << ADMIN_ACCOUNT
		passwordInput << ADMIN_PASSWORD

		and: "Click login button"
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

		and: "Wait for agent model come up"
		waitFor(3, 1) { agentModel.displayed }

		and: "Type in agent email, agent first name and agent last name"
		agentModelModule.agentEmail << AGENT_EMAIL
		agentModelModule.agentFirstName << AGENT_FIRST_NAME
		agentModelModule.agentLastName << AGENT_LAST_NAME

		and: "Click create button"
		agentModelModule.createButton.click()

		and: "Wait for agent model disappear"
		waitFor(30, 1) { !agentModel.displayed }

		then: "Agent should be created and displayed on page"
		waitFor(10) {
			agentEmail == AGENT_EMAIL
			agentFistName == AGENT_FIRST_NAME
			agentLastName == AGENT_LAST_NAME
		}
	}

	//	@Ignore
	def "add automated treatment successfully"() {
		when: "Add treatment button is visible"
		at ClientDetailPage

		and: "Click add treatment button"
		addTreatmentButton.click()

		and: "Wait for treatment model come up"
		waitFor(3, 1) { addTreatmentModel.displayed }

		and: "Type title, template title, description and select required field"
		addTreatmentModelModule.title << TREATMENT_TITLE
		addTreatmentModelModule.templateTitle << "T"
		addTreatmentModelModule.surgeryTimeRequired.value("Yes")
		addTreatmentModelModule.description << "This is automated smoking test template."

		and: "Click create button"
		addTreatmentModelModule.createButton.click()

		and: "Wait for treatment model disappear"
		waitFor(30, 1) { !addTreatmentModel.displayed }

		then: "Treatment should created and displayed on page"
		waitFor(10) {
			$("tr", 1).find("td", 1).text() == TREATMENT_TITLE
			$("tr", 1).find("td", 2).text() == "T"
			$("tr", 1).find("td", 3).text() == "0"
			$("tr", 1).find("td", 4).text() == "This is automated smoking test template."
			$("tr", 1).find("td", 5).text() == "Active"
		}
	}

	//	@Ignore
	def "go to automated treatment page successfully"() {
		when: "At client detail page"
		at ClientDetailPage

		and: "Click the first line of treatments table"
		assert $("tr", 1).find("td", 1).text() == TREATMENT_TITLE
		$("tr", 1).click()

		then: "Go to treatment detail page"
		at TreatmentPage
	}

	//	@Ignore
	def "add NDI tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select NDI as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "NDI"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "NDI tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "NDI"
			toolTable.find("tr", 1).find("td", 2).text() == "Neck Disability Index"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add NRS-NECK tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select NRS-NECK as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "NRS-NECK"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "NRS-NECK tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "NRS-NECK"
			toolTable.find("tr", 1).find("td", 2).text() == "Numeric Rating Scale (NRS) for Neck Pain Intensity"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add NRS-BACK tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select NRS-BACK as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "NRS-BACK"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "NRS-BACK tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "NRS-BACK"
			toolTable.find("tr", 1).find("td", 2).text() == "Numeric Rating Scale (NRS) for Back Pain Intensity"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add DASH tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select DASH as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "DASH"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "DASH tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "DASH"
			toolTable.find("tr", 1).find("td", 2).text() == "Disabilities of the Arm, Shoulder and Hand"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add QuickDASH tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select QuickDASH as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "QuickDASH"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "QuickDASH tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "QuickDASH"
			toolTable.find("tr", 1).find("td", 2).text() == "Quick measurement tool for disabilities of the arm, shoulder and hand"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add ODI tool successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add tool button"
		addToolButton.click()

		and: "Wait for add tool button dropdown list, defined tool button come up"
		waitFor(3, 1) { addDefinedToolButton.displayed }

		and: "Click defined tool button"
		addDefinedToolButton.click()

		and: "Wait for add defined tool model come up"
		waitFor(3, 1) { addDefinedToolModel.displayed }

		and: "Select ODI as tool, select due time to 2 days and type reminder with 1"
		addDefinedToolModelModule.tool = "ODI"
		addDefinedToolModelModule.defaultDueTimeDay = "2"
		addDefinedToolModelModule.reminder << "1"

		and: "Click create button"
		addDefinedToolModelModule.createButton.click()

		and: "Wait for adding new tool and model disappear"
		waitFor(30, 1) { !addDefinedToolModel.displayed }

		then: "ODI tool should created and displayed on the first line of tool table"
		waitFor(10) {
			toolTable.find("tr", 1).find("td", 1).text() == "ODI"
			toolTable.find("tr", 1).find("td", 2).text() == "Oswestry Disability Index"
			toolTable.find("tr", 1).find("td", 3).text() == "Outcome"
		}
	}

	//	@Ignore
	def "add NDI immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select NDI as tool, select immediate as send time"
		addTaskModleModule.tool = "NDI"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "NDI tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "NDI"
			taskTable.find("tr", 1).find("td", 2).text() == "Neck Disability Index"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "add NRS-NECK immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select NRS-NECK as tool, select immediate as send time"
		addTaskModleModule.tool = "NRS-NECK"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "NRS-NECK tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "NRS-NECK"
			taskTable.find("tr", 1).find("td", 2).text() == "Numeric Rating Scale (NRS) for Neck Pain Intensity"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "add NRS-BACK immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select NRS-BACK as tool, select immediate as send time"
		addTaskModleModule.tool = "NRS-BACK"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "NRS-BACK tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "NRS-BACK"
			taskTable.find("tr", 1).find("td", 2).text() == "Numeric Rating Scale (NRS) for Back Pain Intensity"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "add DASH immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select DASH as tool, select immediate as send time"
		addTaskModleModule.tool = "DASH"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "DASH tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "DASH"
			taskTable.find("tr", 1).find("td", 2).text() == "Disabilities of the Arm, Shoulder and Hand"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "add QuickDASH immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select QuickDASH as tool, select immediate as send time"
		addTaskModleModule.tool = "QuickDASH"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "QuickDASH tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "QuickDASH"
			taskTable.find("tr", 1).find("td", 2).text() == "Quick measurement tool for disabilities of the arm, shoulder and hand"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "add ODI immediate task successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click add task button"
		addTaskButton.click()

		and: "Wait for add task model come up"
		waitFor(3, 1) { addTaskModel.displayed }

		and: "Select ODI as tool, select immediate as send time"
		addTaskModleModule.tool = "ODI"
		addTaskModleModule.sendTimeDirection = "Immediate"

		and: "Click create button"
		addTaskModleModule.createButton.click()

		and: "Wait for adding new task and model disappear"
		waitFor(30, 1) { !addTaskModel.displayed }

		then: "ODI tool should created and displayed on the first line of task table"
		waitFor(10) {
			taskTable.find("tr", 1).find("td", 1).text() == "ODI"
			taskTable.find("tr", 1).find("td", 2).text() == "Oswestry Disability Index"
			taskTable.find("tr", 1).find("td", 3).text() == "Outcome"
			taskTable.find("tr", 1).find("td", 4).text() == "Immediate"
			taskTable.find("tr", 1).find("td", 5).text() == "2D 0H"
		}
	}

	//	@Ignore
	def "logout successfully"() {
		when: "At treatment detail page"
		at TreatmentPage

		and: "Click profile tab in navigator"
		profileTab.click()

		then: "Navigate to profile page"
		at ProfilePage

		when: "Click logout button"
		logoutButton.click()

		then: "Navigate to login page"
		at LoginPage
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

		when: "Type agent first name in search input"
		searchInput << AGENT_FIRST_NAME

		and: "Click search button"
		searchButton.click()

		then: "Wait for Activate Ratchet Health Account line"
		waitFor(30, 1) {
			$('td', text: contains(ACTIVATE_EMAIL_TITLE)).size() >= 1
		}

        when:
        waitFor(10, 1) {
            $('td', text: contains(ACTIVATE_EMAIL_TITLE)).displayed
        }

		and: "Click activate ratchet health account line"
		$('td', text: contains(ACTIVATE_EMAIL_TITLE)).click()

		then: "Got activate email"
		waitFor(300, 1) {
			$('td', text: contains(AGENT_FIRST_NAME)).size() >= 1
		}
	}
}
