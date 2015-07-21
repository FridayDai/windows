package com.ratchethealth.admin

class SchedulerService extends RatchetAPIService {
    def grailsApplication

    def changeTimeForSchedule(String token, String debugDate) {
        log.info("Call backend service to set date for debug schedule with debug time, token: ${token}.")

        String timeUrl = grailsApplication.config.ratchetv2.server.url.scheduleTime

        withPost(token, timeUrl) { req ->
            def resp = req
                    .field("dateForDebug",debugDate)
                    .asString()

            if (resp.status == 200) {
                log.info("set date for debug schedule successfully, token: ${token}")

                true
            } else {
                handleError(resp)
            }
        }
    }
}
