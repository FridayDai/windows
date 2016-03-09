package pages.admin

import geb.Page
import model.admin.StaffModel
import model.admin.TreatmentModel
import modules.admin.AgentModalModule
import modules.admin.TreatmentModalModule
import modules.admin.TreatmentsTableModule

class ClientDetailPage extends Page {
	static at = { $("#client-info-panel").size() == 1 }

	static content = {
		agentButton { $("button").findAll { it.'@data-target' == '#agent-modal' } }
		agentModal { $("#agent-modal").module(AgentModalModule)}

		agentEmail { $("dt", text: "Agent Email:").next().text() }
		agentFistName { $("dt", text: "Agent First Name:").next().text() }
		agentLastName { $("dt", text: "Agent Last Name:").next().text() }

		addTreatmentButton { $("#add-treatment") }
		addTreatmentModal { $("#treatment-modal").module(TreatmentModalModule) }

        profileTab { $("#menu a", text: contains("PROFILE")) }

		treatmentsTable { $('#treatment-table').module(TreatmentsTableModule) }
	}

    def addTreatment(TreatmentModel treatment) {
        addTreatmentButton.click()

        waitFor(3, 1) { addTreatmentModal.displayed }

        addTreatmentModal.title << treatment.treatmentTitle
        addTreatmentModal.templateTitle << treatment.templateTitle
        addTreatmentModal.surgeryTimeRequired.value(treatment.surgeryTimeRequired ? "Yes" : "No")
        addTreatmentModal.description << treatment.description

        addTreatmentModal.createButton.click()

        waitFor(30, 1) { !addTreatmentModal.displayed }
    }

    def findTreatmentInTable(TreatmentModel treatment) {
        waitFor(5, 1) {
            treatmentsTable.treatmentItems.findAll({ it?.treatmentTitle == treatment.treatmentTitle}).size() == 1
        }

        treatmentsTable.treatmentItems[0]
    }

    def addAgent(StaffModel agent) {
        agentButton.click()

        waitFor(3, 1) { agentModal.displayed }

        agentModal.agentEmail << agent.email
        agentModal.agentFirstName << agent.firstName
        agentModal.agentLastName << agent.lastName

        agentModal.createButton.click()

        waitFor(30, 1) { !agentModal.displayed }
    }

    def viewTreatmentDetail(TreatmentModel treatment) {
        waitFor(10, 1) {
            treatmentsTable.treatmentItems.findAll({ it?.id == treatment.id}).size() == 1
        }

        treatmentsTable.treatmentItems[0].click()
    }
}
