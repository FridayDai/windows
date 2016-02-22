package com.ratchethealth.admin

import grails.converters.JSON

class HL7Service extends RatchetAPIService {
    def grailsApplication

    def getQueueStatus(String token) {
        log.info("Call backend service to get HL7 Queue status, token: ${token}.")

        String queueStatusURL = grailsApplication.config.ratchetv2.server.url.HL7.queueStatus
        String clientSecret = grailsApplication.config.hmac.access.secret

        withGet(token, queueStatusURL) { req ->
            def resp = req
                .headers(HMACUtils.generateAuthenticationInfo(clientSecret, "GET", queueStatusURL))
                .asString()

            if (resp.status == 200) {
                log.info("Get HL7 Queue status success, token: ${token}")

                JSON.parse(resp.body)
            } else {
                handleError(resp)
            }
        }
    }

    def getMessageStatus(String token) {
        log.info("Call backend service to get HL7 message status, token: ${token}.")

        String messageStatusURL = grailsApplication.config.ratchetv2.server.url.HL7.messageStatus

        withGet(token, messageStatusURL) { req ->
            def resp = req
                .headers(HMACUtils.generateAuthenticationInfo(grailsApplication.config.hmac.access.secret, 'GET', messageStatusURL))
                .asString()

            if (resp.status == 200) {
                log.info("Get HL7 message status success, token: ${token}")

                JSON.parse(resp.body)
            } else {
                handleError(resp)
            }
        }
    }

    def getFailureList(String token) {
        log.info("Call backend service to get HL7 failure list, token: ${token}.")

        String failuresListURL = grailsApplication.config.ratchetv2.server.url.HL7.failuresList

        withGet(token, failuresListURL) { req ->
            def resp = req
                .headers(HMACUtils.generateAuthenticationInfo(grailsApplication.config.hmac.access.secret, 'GET', failuresListURL))
                .asString()

            if (resp.status == 200) {
                log.info("Get HL7 failure list success, token: ${token}")

                JSON.parse(resp.body)
            } else {
                handleError(resp)
            }
        }
    }
}
