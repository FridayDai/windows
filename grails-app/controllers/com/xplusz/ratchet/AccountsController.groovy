package com.xplusz.ratchet

class AccountsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/account/accounts'
    }
}
