package modules.admin

import geb.Module

class TaskModelModule extends Module {
	static content = {
		tool { $("#add-item-tool-id") }
		sendTimeDirection { $("select", name: "sendTimeDirection") }
		sendTimeWeeks { $("select", name: "sendTimeWeeks") }
		sendTimeDays { $("select", name: "sendTimeDays") }
		sendTimeHours { $("select", name: "sendTimeHours") }
		sendTimeMinutes { $("select", name: "sendTimeMinutes") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
