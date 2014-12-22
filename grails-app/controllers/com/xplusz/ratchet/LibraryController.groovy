package com.xplusz.ratchet

class LibraryController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/library/library'
    }
}
