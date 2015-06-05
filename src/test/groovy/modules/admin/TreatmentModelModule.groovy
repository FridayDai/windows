package modules.admin

import geb.Module

class TreatmentModelModule extends Module {
	static content = {
		title { $("#title") }
		templateTitle { $("#tmpTitle") }
		surgeryTimeRequired { $("#surgeryTimeRequired") }
		description { $("#description") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
