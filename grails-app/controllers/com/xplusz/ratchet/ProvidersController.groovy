package com.xplusz.ratchet

class ProvidersController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/providers/providers'
    }
}
