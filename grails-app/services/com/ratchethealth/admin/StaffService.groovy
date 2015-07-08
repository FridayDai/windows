package com.ratchethealth.admin

import grails.converters.JSON

class StaffService extends RatchetAdminService {
    def grailsApplication

    def addAgent(String token, Staff agent) {
        String staffsUrl = grailsApplication.config.ratchetv2.server.url.staffs
        log.info("Call backend service to add agent with clientId, email, firstName, " +
                "lastName, type and doctor, token: ${token}.")
        withPost(token, staffsUrl) { req ->
            def resp = req
                    .field("clientId", agent.clientId)
                    .field("email", agent.email)
                    .field("firstName", agent.firstName)
                    .field("lastName", agent.lastName)
                    .field("type", agent.type)
                    .field("doctor", agent.isDoctor)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 201) {
                log.info("Add agent success, token: ${token}")
                agent.id = result.id
                return [resp, agent]
            }

            [resp, null]
        }
    }

    def updateAgent(String token, Staff agent) {
        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff
        log.info("Call backend service to update Agent with clientId, email, " +
                "firstName, lastName, type and doctor, token: ${token}.")

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
                return [resp, true]
            }

            [resp, null]
        }
    }

    def deleteAgent(String token, int agentId) {
        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff
        def staffUrl = String.format(oneStaffUrl, agentId)
        log.info("Call backend service to delete Agent, token: ${token}.")

        withDelete(token, staffUrl) { req ->
            def resp = req.asString()

            if (resp.status == 204) {
                log.info("Delete agent success,token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
