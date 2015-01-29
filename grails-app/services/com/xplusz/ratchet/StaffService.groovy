package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import grails.converters.JSON

class StaffService {
	//dependency injection for grailsApplication
	def grailsApplication

	/**
	 * Create new agent
	 *
	 * @param agent # new Staff instance
	 * @return agent   # created agent
	 */
	def addAgent(Staff agent) {
		String staffsUrl = grailsApplication.config.ratchetv2.server.url.staffs

		def resp = Unirest.post(staffsUrl)
				.field("clientId", agent.clientId)
				.field("email", agent.email)
				.field("firstName", agent.firstName)
				.field("lastName", agent.lastName)
				.field("type", agent.type)
				.field("doctor", agent.isDoctor)
				.asString()

		if (resp.status == 201) {
			def result = JSON.parse(resp.body)

			agent.id = result.id
			return agent
		} else {
		//TODO: Error handler
		}
	}

	/**
	 * Update agent
	 *
	 * @param agent # updated Staff instance
	 * @return isSuccess
	 */
	def updateAgent(Staff agent) {
		String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff

		def staffUrl = String.format(oneStaffUrl, agent.id)

		def resp = Unirest.post(staffUrl)
				.field("clientId", agent.clientId)
				.field("email", agent.email)
				.field("firstName", agent.firstName)
				.field("lastName", agent.lastName)
				.field("type", agent.type)
				.field("doctor", agent.isDoctor)
				.asString()

		if (resp.status == 200) {
			return true
		} else {
			//TODO: Error handler
		}

		return false
	}

	/**
	 * Delete agent
	 *
	 * @param agentId # delete agent id
	 * @return isSuccess
	 */
	def deleteAgent(agentId) {
		String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff

		def staffUrl = String.format(oneStaffUrl, agentId)

		def resp = Unirest.delete(staffUrl).asString()

		if (resp.status == 204) {
			return true
		} else {
			//TODO: Error handler
		}

		return false
	}
}
