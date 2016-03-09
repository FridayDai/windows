package modules.admin

import geb.Module

class AgentModalModule extends Module {
	static content = {
		agentEmail { $("#email") }
		agentFirstName { $("#firstName") }
		agentLastName { $("#lastName") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
