package com.ratchethealth.admin

class HomeController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        redirect(controller: "clients", action: "index")
    }
}
