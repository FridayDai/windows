package modules

import geb.Module

class AgentModelModule extends Module {
	static content = {
		agentEmail { $("#email") }
		agentFirstName { $("#firstName") }
		agentLastName { $("#lastName") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
