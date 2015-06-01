package com.ratchethealth.admin

import grails.converters.JSON

class ProfileController extends BaseController {

    def accountPasswordService

    def goToProfilePage() {
        render view: 'profile'
    }

    def updatePassword() {
        def resp = accountPasswordService.updatePassword(request, params)
        def result = [resp: resp]
        render result as JSON
    }

}
