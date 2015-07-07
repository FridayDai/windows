package com.ratchethealth.admin

import grails.converters.JSON

class ProfileController extends BaseController {

    def accountPasswordService
    def scheduleTimeService

    def goToProfilePage() {
        render view: 'profile'
    }

    def updatePassword() {
        def resp = accountPasswordService.updatePassword(request, params)
        def result = [resp: resp]
        render result as JSON
    }

    def changeScheduleTime() {
        def resp = scheduleTimeService.changeTimeForSchedule(request, params.debugDate)
        def result = [resp: resp]
        render result as JSON
    }

}
