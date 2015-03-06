package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import com.xplusz.ratchet.exceptions.ServerException
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
	def addAgent(Staff agent) throws ServerException {
		String staffsUrl = grailsApplication.config.ratchetv2.server.url.staffs

		def resp = Unirest.post(staffsUrl)
				.field("clientId", agent.clientId)
				.field("email", agent.email)
				.field("firstName", agent.firstName)
				.field("lastName", agent.lastName)
				.field("type", agent.type)
				.field("doctor", agent.isDoctor)
				.asString()

		def result = JSON.parse(resp.body)

		if (resp.status == 201) {
			agent.id = result.id
			return agent
		} else {
			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Update agent
	 *
	 * @param agent # updated Staff instance
	 * @return isSuccess
	 */
	def updateAgent(Staff agent) throws ServerException {
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
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Delete agent
	 *
	 * @param agentId # delete agent id
	 * @return isSuccess
	 */
	def deleteAgent(agentId) throws ServerException {
		String oneStaffUrl = grailsApplication.config.ratchetv2.server.url.oneStaff

		def staffUrl = String.format(oneStaffUrl, agentId)

		def resp = Unirest.delete(staffUrl).asString()

		if (resp.status == 204) {
			return true
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}
}
