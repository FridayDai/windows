package com.xplusz.ratchet

class ToolsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/tools/tools'
    }
}
