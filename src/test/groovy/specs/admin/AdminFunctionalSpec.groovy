package specs.admin

import model.admin.ClientModel
import model.admin.OutcomeTaskModel
import model.admin.OutcomeToolModel
import model.admin.StaffModel
import model.admin.TreatmentModel
import pages.admin.ClientDetailPage
import pages.admin.ClientsPage
import pages.admin.LoginPage
import pages.admin.ProfilePage
import pages.admin.TreatmentPage
import specs.RatchetFunctionalSpec
import spock.lang.*
import utils.Utility


@Stepwise
class AdminFunctionalSpec extends RatchetFunctionalSpec {
	@Shared IDENTIFY

    @Shared ClientModel CLIENT
    @Shared StaffModel AGENT
    @Shared TreatmentModel TREATMENT_FIRST
    @Shared TreatmentModel TREATMENT_SECOND

    @Shared OutcomeToolModel NDI_TOOL
    @Shared OutcomeToolModel NRS_NECK_TOOL
    @Shared OutcomeToolModel NRS_BACK_TOOL
    @Shared OutcomeToolModel DASH_TOOL
    @Shared OutcomeToolModel QUICK_DASH_TOOL
    @Shared OutcomeToolModel ODI_TOOL
    @Shared OutcomeToolModel KOOS_TOOL
    @Shared OutcomeToolModel HOOS_TOOL
    @Shared OutcomeToolModel FNS_TOOL
    @Shared OutcomeToolModel HARRIS_HIP_SCORE_TOOL

    @Shared OutcomeTaskModel NDI_TASK
    @Shared OutcomeTaskModel NRS_NECK_TASK
    @Shared OutcomeTaskModel NRS_BACK_TASK
    @Shared OutcomeTaskModel DASH_TASK
    @Shared OutcomeTaskModel QUICK_DASH_TASK
    @Shared OutcomeTaskModel ODI_TASK
    @Shared OutcomeTaskModel KOOS_TASK
    @Shared OutcomeTaskModel HOOS_TASK
    @Shared OutcomeTaskModel FNS_TASK
    @Shared OutcomeTaskModel HARRIS_HIP_SCORE_TASK

	static ADMIN_ACCOUNT = "joseph@ratchethealth.com"
	static ADMIN_PASSWORD = "password"

	def setupSpec() {
		IDENTIFY = System.currentTimeMillis()

        CLIENT = new ClientModel(
            clientName: "AST${IDENTIFY}CN",
            subDomain: "ast${IDENTIFY}sd",
            patientPortalName: "AST${IDENTIFY}PPN"
        )

        AGENT = new StaffModel(
            email: "ratchet.testing+ast${IDENTIFY}@gmail.com",
            firstName: "FN+ast${IDENTIFY}",
            lastName: "AST"
        )

        TREATMENT_FIRST = new TreatmentModel(
            treatmentTitle: "treatment${IDENTIFY}FIRST"
        )

        TREATMENT_SECOND = new TreatmentModel(
            treatmentTitle: "treatment${IDENTIFY}SECOND"
        )

        NDI_TOOL = new OutcomeToolModel(
            name: "NDI",
            description: "Neck Disability Index"
        )

        NRS_NECK_TOOL = new OutcomeToolModel(
            name: "NRS-NECK",
            description: "Numeric Rating Scale (NRS) for Neck Pain Intensity"
        )

        NRS_BACK_TOOL = new OutcomeToolModel(
            name: "NRS-BACK",
            description: "Numeric Rating Scale (NRS) for Back Pain Intensity"
        )

        DASH_TOOL = new OutcomeToolModel(
            name: "DASH",
            description: "Disabilities of the Arm, Shoulder and Hand"
        )

        QUICK_DASH_TOOL = new OutcomeToolModel(
            name: "QuickDASH",
            description: "Quick measurement tool for disabilities of the arm, shoulder and hand"
        )

        ODI_TOOL = new OutcomeToolModel(
            name: "ODI",
            description: "Oswestry Disability Index"
        )

        KOOS_TOOL = new OutcomeToolModel(
            name: "KOOS",
            description: "Knee injury and Osteoarthritis Outcome Score"
        )

        HOOS_TOOL = new OutcomeToolModel(
            name: "HOOS",
            description: "Hip dysfunction and Osteoarthritis Outcome Score"
        )

        FNS_TOOL = new OutcomeToolModel(
            name: "Fairley Nasal Symptom",
            description: "Fairley Nasal Symptom"
        )

        HARRIS_HIP_SCORE_TOOL = new OutcomeToolModel(
            name: "Harris Hip Score",
            description: "Harris Hip Score"
        )

        NDI_TASK = new OutcomeTaskModel(
            tool: NDI_TOOL
        )

        NRS_NECK_TASK = new OutcomeTaskModel(
            tool: NRS_NECK_TOOL
        )

        NRS_BACK_TASK = new OutcomeTaskModel(
            tool: NRS_BACK_TOOL
        )

        DASH_TASK = new OutcomeTaskModel(
            tool: DASH_TOOL
        )

        QUICK_DASH_TASK = new OutcomeTaskModel(
            tool: QUICK_DASH_TOOL
        )

        ODI_TASK = new OutcomeTaskModel(
            tool: ODI_TOOL
        )

        KOOS_TASK = new OutcomeTaskModel(
            tool: KOOS_TOOL
        )

        HOOS_TASK = new OutcomeTaskModel(
            tool: HOOS_TOOL
        )

        FNS_TASK = new OutcomeTaskModel(
            tool: FNS_TOOL
        )

        HARRIS_HIP_SCORE_TASK = new OutcomeTaskModel(
            tool: HARRIS_HIP_SCORE_TOOL
        )
	}

    def before() {
        browser.setBaseUrl(getAdminUrl())
    }

    def after() {
        Utility.writeAppVar(
            [
                "IDENTIFY": IDENTIFY,
                "CLIENT_ID": CLIENT.id,
                "TREATMENT_ID_FIRST": TREATMENT_FIRST.id,
                "TREATMENT_ID_SECOND": TREATMENT_SECOND.id
            ]
        )
    }

	def "should login successfully"() {
		when: "Go to login page"
		def loginPage = new LoginPage()

		to loginPage

        and: "Login with account and password"
        loginPage.login(ADMIN_ACCOUNT, ADMIN_PASSWORD)

        then: "At clients page now"
        waitFor (30, 1) {
            at ClientsPage
        }
	}

	def "add client successfully"() {
		when: "At clients page now"
        def clientsPage = new ClientsPage()
		at clientsPage

		and:
        clientsPage.addClient(CLIENT)

        and:
        clientsPage.search(CLIENT.patientPortalName)

        and:
        def matchedRow = clientsPage.findClientInTable(CLIENT)
        CLIENT.id = matchedRow.id

        then:
        waitFor(90, 1) {
            matchedRow.clientName == CLIENT.clientName
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
        clientsPage.viewClientDetail(CLIENT)

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
        clientDetailPage.addAgent(AGENT)

        then:
        waitFor(10, 1) {
            clientDetailPage.agentEmail == AGENT.email
            clientDetailPage.agentFistName == AGENT.firstName
            clientDetailPage.agentLastName == AGENT.lastName
        }
	}

    def "add first automated treatment successfully"() {
        when:
        def clientDetailPage = new ClientDetailPage()
        at clientDetailPage

        and:
        clientDetailPage.addTreatment(TREATMENT_FIRST)

        and:
        def matchedRow = clientDetailPage.findTreatmentInTable(TREATMENT_FIRST)
        TREATMENT_FIRST.id = matchedRow.id

        then:
        waitFor(10, 1) {
            matchedRow.treatmentTitle == TREATMENT_FIRST.treatmentTitle
            matchedRow.templateTitle == TREATMENT_FIRST.templateTitle
            matchedRow.active == 0
            matchedRow.description == TREATMENT_FIRST.description
            matchedRow.status == "Active"
        }
    }

	def "go to first automated treatment page successfully"() {
		when:
        def clientDetailPage = new ClientDetailPage()
		at clientDetailPage

		and:
        clientDetailPage.viewTreatmentDetail(TREATMENT_FIRST)

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
        tool << [NDI_TOOL, NRS_NECK_TOOL, NRS_BACK_TOOL, DASH_TOOL, QUICK_DASH_TOOL]
    }

	def "add immediate tasks for first treatment successfully"() {
		when:
        def treatmentPage = new TreatmentPage()
		at treatmentPage

        then:
        treatmentPage.addImmediateTask(task)

        where:
        task << [NDI_TASK, NRS_NECK_TASK, NRS_BACK_TASK, DASH_TASK, QUICK_DASH_TASK]
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
        clientsPage.viewClientDetail(CLIENT)

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
        clientDetailPage.addTreatment(TREATMENT_SECOND)

        and:
        def matchedRow = clientDetailPage.findTreatmentInTable(TREATMENT_SECOND)
        TREATMENT_SECOND.id = matchedRow.id

        then:
        waitFor(10, 1) {
            matchedRow.treatmentTitle == TREATMENT_SECOND.treatmentTitle
            matchedRow.templateTitle == TREATMENT_SECOND.templateTitle
            matchedRow.active == 0
            matchedRow.description == TREATMENT_SECOND.description
            matchedRow.status == "Active"
        }
    }

    def "go to second automated treatment page successfully"() {
        when:
        def clientDetailPage = new ClientDetailPage()
        at clientDetailPage

        and:
        clientDetailPage.viewTreatmentDetail(TREATMENT_SECOND)

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
        tool << [ODI_TOOL, KOOS_TOOL, HOOS_TOOL, FNS_TOOL, HARRIS_HIP_SCORE_TOOL]
    }

    def "add immediate tasks for second treatment successfully"() {
        when:
        def treatmentPage = new TreatmentPage()
        at treatmentPage

        then:
        treatmentPage.addImmediateTask(task)

        where:
        task << [ODI_TASK, KOOS_TASK, HOOS_TASK, FNS_TASK, HARRIS_HIP_SCORE_TASK]
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
