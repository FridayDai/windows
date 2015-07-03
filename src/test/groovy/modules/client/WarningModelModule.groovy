package modules.client

import geb.Module


class WarningModelModule extends Module {
    static content = {
        agreeButton { $(".btn-agree") }
    }
}
