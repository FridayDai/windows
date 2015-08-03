package com.ratchethealth.admin.modules.common

import geb.Module

class BaseModelModule extends Module {

    static content = {
        title { $(".modal-title").text() }

        closeButton { $("button.close") }
        cancelButton { $("button", text: "Cancel") }

    }

}
