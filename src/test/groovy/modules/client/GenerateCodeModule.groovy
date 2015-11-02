package modules.client

import geb.Module

/**
 * Created by thomas on 10/27/15.
 */
class GenerateCodeModule extends Module {
    static content = {
        codeLink { $(".link-to-patient") }
        doneButton { $(".ui-button-text", text: 'Done') }
        code { $(".code").text() }
    }
}
