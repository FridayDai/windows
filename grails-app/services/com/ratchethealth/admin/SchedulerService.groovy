package com.ratchethealth.admin

class SchedulerService extends RatchetAdminService {
    def grailsApplication

    def changeTimeForSchedule(String token, String debugDate) {
        String timeUrl = grailsApplication.config.ratchetv2.server.url.scheduleTime
        log.info("Call backend service to set date for debug schedule with debug time, token: ${token}.")

        withPost(token, timeUrl) { req ->
            def resp = req
                    .field("dateForDebug",debugDate)
                    .asString()

            if (resp.status == 200) {
                log.info("set date for debug schedule successfully, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
