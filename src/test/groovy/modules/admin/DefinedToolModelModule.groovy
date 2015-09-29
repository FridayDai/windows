package modules.admin

import geb.Module

class DefinedToolModelModule extends Module {
	static content = {
		tool { $("#defined-tool-type") }
		defaultDueTimeDay { $("select", name: "defaultDueTimeDay") }
		defaultDueTimeHour { $("select", name: "defaultDueTimeHour") }
		reminder { $("#defined-tool-reminder") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
