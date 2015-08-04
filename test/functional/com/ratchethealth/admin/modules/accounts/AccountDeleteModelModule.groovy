package com.ratchethealth.admin.modules.accounts

import geb.Module

class AccountDeleteModelModule extends Module {
    static content = {
        title { $(".modal-title") }

        accountPromptMsg { $(".delete-prompt-msg") }

        closeButton { $("button.close") }
        cancelButton { $("button", text: "Cancel") }
        deleteButton { $("button.delete-btn") }
    }
}
