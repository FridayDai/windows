package modules

import geb.Module

class ClientModelModule extends Module {
	static content = {
		clientName { $("#name") }
		subDomain { $("#subDomain") }
		pateintPortalName { $("#portalName") }
		primaryColorHex { $("#primaryColorHex") }
		logo { $("#logo") }
		favicon { $("#favIcon") }
		createButton { $(".modal-footer").find("button", 1) }
	}
}
