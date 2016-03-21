package specs.admin

import pages.admin.ClientDetailPage
import pages.admin.ClientsPage
import pages.admin.LoginPage
import pages.admin.ProfilePage
import pages.admin.TreatmentPage
import specs.RatchetFunctionalSpec
import spock.lang.*
import utils.ModelHelper

@Stepwise
class AdminFunctionalSpec extends RatchetFunctionalSpec {

    def before() {
        browser.setBaseUrl(getAdminUrl())
    }

	def "should login successfully"() {
		when:
		def loginPage = new LoginPage()
		to loginPage

        and:
        loginPage.login(admin.email, admin.password)

        then:
        waitFor (30, 1) {
            at ClientsPage
        }
	}

	def "add client successfully"() {
		when:
        def clientsPage = new ClientsPage()
		at clientsPage

		and:
        clientsPage.addClient(client)

        and:
        clientsPage.search(client.patientPortalName)

        and:
        def matchedRow = clientsPage.findClientInTable(client)
        client.id = matchedRow.id

        then:
        waitFor(90, 1) {
            matchedRow.clientName == client.clientName
            matchedRow.activeStaff == 0
            matchedRow.activePatient == 0
            matchedRow.activeTreatment == 0
        }
	}

	def "go to client details page successfully"() {
		when:
        def clientsPage = new ClientsPage()
		at clientsPage

		and:
        clientsPage.viewClientDetail(client)

        then:
        waitFor(30, 1) {
            at ClientDetailPage
        }
	}

	def "add agent email successfully"() {
		when:
        def clientDetailPage = new ClientDetailPage()
		at clientDetailPage

		and:
        clientDetailPage.addAgent(agent)

        then:
        waitFor(10, 1) {
            clientDetailPage.agentEmail == agent.email
            clientDetailPage.agentFistName == agent.firstName
            clientDetailPage.agentLastName == agent.lastName
        }
	}

    def "add first automated treatment successfully"() {
        when:
        def clientDetailPage = new ClientDetailPage()
        at clientDetailPage

        and:
        clientDetailPage.addTreatment(treatment1)

        and:
        def matchedRow = clientDetailPage.findTreatmentInTable(treatment1)
        treatment1.id = matchedRow.id

        then:
        waitFor(10, 1) {
            matchedRow.treatmentTitle == treatment1.treatmentTitle
            matchedRow.templateTitle == treatment1.templateTitle
            matchedRow.active == 0
            matchedRow.description == treatment1.description
            matchedRow.status == "Active"
        }
    }

	def "go to first automated treatment page successfully"() {
		when:
        def clientDetailPage = new ClientDetailPage()
		at clientDetailPage

		and:
        clientDetailPage.viewTreatmentDetail(treatment1)

        then:
        waitFor(30, 1) {
            at TreatmentPage
        }
	}

	def "add tools for first treatment successfully"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        then:
        treatmentPage.addOutcomeTool(tool)

        where:
        tool << [
            ModelHelper.getNDITool(),
            ModelHelper.getNRSNECKTool(),
            ModelHelper.getNRSBACKTool(),
            ModelHelper.getDASHTool(),
            ModelHelper.getQUICKDASHTool()
        ]
    }

	def "add immediate tasks for first treatment successfully"() {
		when:
        def treatmentPage = new TreatmentPage()
		at treatmentPage

        then:
        treatmentPage.addImmediateTask(task)

        where:
        task << [
            ModelHelper.getNDITask(),
            ModelHelper.getNRSNECKTask(),
            ModelHelper.getNRSBACKTask(),
            ModelHelper.getDASHTask(),
            ModelHelper.getQUICKDASHTask()
        ]
	}

    def "click logo icon on treatment detail page back to clients page successfully"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        and:
        treatmentPage.clickLogoIcon()

        then:
        waitFor(5,1) {
            at ClientsPage
        }
    }

    def "go to the client details page successfully"() {
        when:
        def clientsPage = new ClientsPage()
        at clientsPage

        and:
        clientsPage.viewClientDetail(client)

        then:
        waitFor(30, 1) {
            at ClientDetailPage
        }
    }

    def "add the second automated treatment successfully"() {
        when:
        def clientDetailPage = new ClientDetailPage()
        at clientDetailPage

        and:
        clientDetailPage.addTreatment(treatment2)

        and:
        def matchedRow = clientDetailPage.findTreatmentInTable(treatment2)
        treatment2.id = matchedRow.id

        then:
        waitFor(10, 1) {
            matchedRow.treatmentTitle == treatment2.treatmentTitle
            matchedRow.templateTitle == treatment2.templateTitle
            matchedRow.active == 0
            matchedRow.description == treatment2.description
            matchedRow.status == "Active"
        }
    }

    def "go to second automated treatment page successfully"() {
        when:
        def clientDetailPage = new ClientDetailPage()
        at clientDetailPage

        and:
        clientDetailPage.viewTreatmentDetail(treatment2)

        then:
        waitFor(30, 1) {
            at TreatmentPage
        }
    }

    def "add tools for second treatment successfully"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        then:
        treatmentPage.addOutcomeTool(tool)

        where:
        tool << [
            ModelHelper.getODITool(),
            ModelHelper.getKOOSTool(),
            ModelHelper.getHOOSTool(),
            ModelHelper.getFNSTool(),
            ModelHelper.getHARRISHIPSCORETool()
        ]
    }

    def "add immediate tasks for second treatment successfully"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        then:
        treatmentPage.addImmediateTask(task)

        where:
        task << [
            ModelHelper.getODITask(),
            ModelHelper.getKOOSTask(),
            ModelHelper.getHOOSTask(),
            ModelHelper.getFNSTask(),
            ModelHelper.getHARRISHIPSCORETask()
        ]
    }

    def "click profile button on navigator bar"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        and:
        treatmentPage.gotoProfilePage()

        then:
        waitFor(5, 1) {
            at ProfilePage
        }
    }

	def "logout successfully"() {
		when:
        def profilePage = new ProfilePage()
		at profilePage

		and:
        profilePage.logout()

        then:
        waitFor(5, 1) {
            at LoginPage
        }
	}
}
