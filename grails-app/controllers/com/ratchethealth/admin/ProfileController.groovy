package com.ratchethealth.admin

import grails.converters.JSON

class ProfileController extends BaseController {

    def accountPasswordService
    def schedulerService

    def goToProfilePage() {
        render view: 'profile'
    }

    def updatePassword() {
        String token = request.session.token
        def oldPassword = params["old-password"]
        def newPassword = params["new-password"]
        def confirmPassword = params["confirm-password"]

        def resp = accountPasswordService.updatePassword(token, oldPassword, newPassword, confirmPassword)

        def result = [resp: resp]

        render result as JSON
    }

    def changeScheduleTime() {
        String token = request.session.token
        String date = params.debugDate

        def resp = schedulerService.changeTimeForSchedule(token, date)
        def result = [resp: resp]

        render result as JSON
    }

}
