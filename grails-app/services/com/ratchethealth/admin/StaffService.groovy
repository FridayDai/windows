package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class StaffService {
    //dependency injection for grailsApplication
    def grailsApplication

    /**
     * Create new agent
     *
     * @param agent # new Staff instance
     * @return agent   # created agent
     */
    def addAgent(HttpServletRequest request, Staff agent) throws ServerException {
        String staffsUrl = grailsApplication.config.ratchetv2.server.url.staffs
        log.info("Call backend service to add agent with clientId, email, firstName, lastName, type and doctor, token: ${request.session.token}.")

        def resp = Unirest.post(staffsUrl)
                .header("X-Auth-Token", request.session.token)
                .field("clientId", agent.clientId)
                .field("email", agent.email)
                .field("firstName", agent.firstName)
                .field("lastName", agent.lastName)
                .field("type", agent.type)
                .field("doctor", agent.isDoctor)
                .asString()

        def result = JSON.parse(resp.body)

        if (resp.status == 201) {
            log.info("Add agent success, token: ${request.session.token}")
            agent.id = result.id
            return agent
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Update agent
     *
     * @param agent # updated Staff instance
     * @return isSuccess
     */
    def updateAgent(HttpServletRequest request, Staff agent) throws ServerException {
        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff
        log.info("Call backend service to update Agent with clientId, email, firstName, lastName, type and doctor, token: ${request.session.token}.")

        def staffUrl = String.format(oneStaffUrl, agent.id)

        def resp = Unirest.post(staffUrl)
                .header("X-Auth-Token", request.session.token)
                .field("clientId", agent.clientId)
                .field("email", agent.email)
                .field("firstName", agent.firstName)
                .field("lastName", agent.lastName)
                .field("type", agent.type)
                .field("doctor", agent.isDoctor)
                .asString()

        if (resp.status == 200) {
            log.info("Update agent success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Delete agent
     *
     * @param agentId # delete agent id
     * @return isSuccess
     */
    def deleteAgent(HttpServletRequest request, agentId) throws ServerException {
        String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff

        def staffUrl = String.format(oneStaffUrl, agentId)
        log.info("Call backend service to delete Agent, token: ${request.session.token}.")

        def resp = Unirest.delete(staffUrl)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 204) {
            log.info("Delete agent success,token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }
}
