package modules.client

import geb.Module


class NoEmailWarningModule extends Module {
    static content = {
        agreeButton { $(".btn-agree .ui-button-text", text: 'Yes') }
    }
}