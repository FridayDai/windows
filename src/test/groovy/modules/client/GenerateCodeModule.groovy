package modules.client

import geb.Module

class GenerateCodeModule extends Module {
    static content = {
        codeLink { $(".link-to-patient") }
        doneButton { $(".ui-button-text", text: 'Done') }
        code { $(".code").text() }
    }
}
