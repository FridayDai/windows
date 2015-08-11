package com.ratchethealth.admin.modules.accounts

import geb.Module

class AccountEditModelModule extends Module {
    static content = {
        title { $(".modal-title") }

        accountEmail { $("#account-email") }
        accountEnabled { $("#isEnabled") }

        closeButton { $("button.close") }
        cancelButton { $("button", text: "Cancel") }
        updateButton { $("button.update-btn") }
    }
}
