package modules.admin

import geb.Module

class DefinedToolModalModule extends Module {
	static content = {
		tool { $("#outcome-tool-type") }
		defaultDueTimeDay { $("select", name: "defaultDueTimeDay") }
		defaultDueTimeHour { $("select", name: "defaultDueTimeHour") }
		defaultExpireTimeDay { $("select", name:"defaultExpireTimeDay") }
		defaultExpireTimeHour { $("select", name:"defaultExpireTimeHour")}
		reminder { $("#defined-tool-reminder") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
