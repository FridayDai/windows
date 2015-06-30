package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import com.ratchethealth.admin.exceptions.ApiAccessException
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class ScheduleTimeService {

    // dependency injection for grailsApplication
    def grailsApplication

    def changeTimeForSchedule(HttpServletRequest request, String debugDate)
            throws ServerException, ApiAccessException {
        try {
            String timeUrl = grailsApplication.config.ratchetv2.server.url.scheduleTime
            log.info("Call backend service to set date for debug schedule with debug time, token: ${request.session.token}.")

            def resp = Unirest.post(timeUrl)
                    .header("X-Auth-Token", request.session.token)
                    .field("dateForDebug",debugDate)
                    .asString()

            if (resp.status == 200) {
                log.info("set date for debug schedule successfully, token: ${request.session.token}")
                return true
            } else {
                def result = JSON.parse(resp.body)
                String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
                throw new ServerException(resp.status, errorMessage)
            }
        } catch (UnirestException e) {
            throw new ApiAccessException(e.message)
        }
    }
}
