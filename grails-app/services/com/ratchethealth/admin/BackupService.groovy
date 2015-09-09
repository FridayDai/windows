package com.ratchethealth.admin

import grails.converters.JSON


class BackupService extends RatchetAPIService {

    def grailsApplication

    def getDBBackups(String token, max) {
        log.info("Call backend service to get backups, token: ${token}.")

        String backupURL = grailsApplication.config.ratchetv2.server.url.backup

        withGet(token, backupURL) { req ->
            def resp = req
                    .queryString("max", max)
                    .asString()

            if (resp.status == 200) {
                def result = JSON.parse(resp.body)

                log.info("Get backups success, token: ${token}")
                return result
            } else {
                handleError(resp)
            }
        }
    }
}
