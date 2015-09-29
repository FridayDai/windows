package com.ratchethealth.admin

import grails.converters.JSON

class StaffService extends RatchetAPIService {
    def grailsApplication

    def addAgent(String token, Staff agent) {
        log.info("Call backend service to add agent with clientId, email, firstName, " +
                "lastName, type and doctor, token: ${token}.")

        String staffsUrl = grailsApplication.config.ratchetv2.server.url.staffs

        withPost(token, staffsUrl) { req ->
            def resp = req
                    .field("clientId", agent.clientId)
                    .field("email", agent.email)
                    .field("firstName", agent.firstName)
                    .field("lastName", agent.lastName)
                    .field("type", agent.type)
                    .field("doctor", agent.isDoctor)
                    .asString()

            if (resp.status == 201) {
                def result = JSON.parse(resp.body)
                log.info("Add agent success, token: ${token}")

                agent.id = result.id

                agent
            } else {
                handleError(resp)
            }
        }
    }

    def updateAgent(String token, Staff agent) {
        log.info("Call backend service to update Agent with clientId, email, " +
                "firstName, lastName, type and doctor, token: ${token}.")

        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff
        def staffUrl = String.format(oneStaffUrl, agent.id)

        withPost(token, staffUrl) { req ->
            def resp = req
                    .field("clientId", agent.clientId)
                    .field("email", agent.email)
                    .field("firstName", agent.firstName)
                    .field("lastName", agent.lastName)
                    .field("type", agent.type)
                    .field("doctor", agent.isDoctor)
                    .asString()

            if (resp.status == 200) {
                log.info("Update agent success, token: ${token}")

                true
            } else {
                handleError(resp)
            }
        }
    }

    def deleteAgent(String token, long agentId) {
        log.info("Call backend service to delete Agent, token: ${token}.")

        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff
        def staffUrl = String.format(oneStaffUrl, agentId)

        withDelete(token, staffUrl) { req ->
            def resp = req.asString()

            if (resp.status == 204) {
                log.info("Delete agent success,token: ${token}")

                true
            } else {
                handleError(resp)
            }
        }
    }
}
