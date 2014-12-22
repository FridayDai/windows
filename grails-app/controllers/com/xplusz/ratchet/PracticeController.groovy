package com.xplusz.ratchet

class PracticeController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/practice/practice'
    }
}
