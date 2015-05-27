package pages

import geb.Page
import modules.AgentModelModule

class ClientDetailPage extends Page {
	static url = "/clients"

	static at = { $("#client-info-panel") }

	static content = {
		agentButton { $("button").findAll { it.'@data-target' == '#agent-modal' } }
		agentModel { $("#agent-modal") }
		agentModelModule { module AgentModelModule, $("#agent-modal") }

		agentEmail { $("dt", text: "Agent Email:").next().text() }
		agentFistName { $("dt", text: "Agent First Name:").next().text() }
		agentLastName { $("dt", text: "Agent Last Name:").next().text() }

		addTreatmentButton { $("#add-treatment") }
	}
}
