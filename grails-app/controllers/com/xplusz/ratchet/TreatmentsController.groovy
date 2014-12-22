package com.xplusz.ratchet

class TreatmentsController extends BaseController{

    def beforeInterceptor = [action: this.&auth]

    def index() {
        render view: '/treatments/treatments'
    }
}
