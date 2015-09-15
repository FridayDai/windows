package com.ratchethealth.admin.modules

import geb.Module

class ClientModelModule extends Module {
	static content = {
		title { $(".modal-title") }

		clientName { $("#name") }
		subDomain { $("#subDomain") }
		patientPortalName { $("#portalName") }
		primaryColorHex { $("#primaryColorHex") }
		logo { $("#logo") }
		favicon { $("#favIcon") }

		closeButton { $("button.close") }
		cancelButton { $("button", text: "Cancel") }
		createButton { $("button.create-btn") }
	}
}
