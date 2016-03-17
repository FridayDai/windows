package modules.admin

import geb.Module

class DefinedToolModalModule extends Module {
	static content = {
		tool { $("#defined-tool-type") }
		defaultDueTimeDay { $("select", name: "defaultDueTimeDay") }
		defaultDueTimeHour { $("select", name: "defaultDueTimeHour") }
		defaultExpireTimeDay { $("select", name:"defaultExpireTimeDay") }
		reminder { $("#defined-tool-reminder") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
