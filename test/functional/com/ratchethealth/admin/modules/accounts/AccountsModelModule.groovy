package com.ratchethealth.admin.modules.accounts

import geb.Module

class AccountsModelModule extends Module {
    static content = {
        title { $(".modal-title") }

        emailInput { $("#email") }

        closeButton { $("button.close") }
        cancelButton { $("button", text: "Cancel") }
        createButton { $("button.create-btn") }
    }
}
