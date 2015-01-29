package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import grails.converters.JSON

class TreatmentService {
	//dependency injection for grailsApplication
	def grailsApplication

	/**
	 * Get treatment list
	 *
	 * @param clientId # client id
	 * @param offset
	 * @param max
	 * @return treatmentList   # treatment List
	 */
	def getTreatments(int clientId, int offset, int max) {
		String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

		def url = String.format(treatmentsUrl, clientId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			//TODO: Error handler
		}
	}

	/**
	 * Create new treatment
	 *
	 * @param treatment # new Treatment instance
	 * @return treatment   # created treatment
	 */
	def createTreatment(Treatment treatment) {
		String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

		def url = String.format(treatmentsUrl, treatment.clientId)

		def resp = Unirest.post(url)
				.field("title", treatment.title)
				.field("tmpTitle", treatment.tmpTitle)
				.field("description", treatment.description)
				.field("surgeryTimeRequired", treatment.surgeryTimeRequired)
				.asString()

		if (resp.status == 201) {
			def result = JSON.parse(resp.body)

			treatment.id = result.id
			return treatment
		} else {
			//TODO: Error handler
		}
	}

	/**
	 * Get one treatment by id
	 *
	 * @param clientId
	 * @param treatmentId
	 * @return client
	 */
	def getTreatment(int clientId, int treatmentId) {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, clientId, treatmentId)

		def resp = Unirest.get(url).asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			//TODO: Error handler
		}
	}

	/**
	 * Update treatment
	 *
	 * @param treatment # updated Treatment instance
	 * @return isSuccess
	 */
	def updateTreatment(Treatment treatment) {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, treatment.clientId, treatment.id)

		def resp = Unirest.post(url)
				.field("title", treatment.title)
				.field("tmpTitle", treatment.tmpTitle)
				.field("description", treatment.description)
				.field("surgeryTimeRequired", treatment.surgeryTimeRequired)
				.asString()

		if (resp.status == 200) {
			return true
		} else {
			//TODO: Error handler
		}

		return false
	}

	/**
	 * Close treatment
	 *
	 * @param clientId # client id
	 * @param treatmentId # treatment id
	 * @return isSuccess
	 */
	def closeTreatment(int clientId, int treatmentId) {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, clientId, treatmentId)

		def resp = Unirest.delete(url).asString()

		if (resp.status == 204) {
			return true
		} else {
			//TODO: Error handler
		}

		return false
	}

	/**
	 * Get tool list
	 *
	 * @param treatmentId # treatment id
	 * @param offset # the tool index where start
	 * @param max # page size
	 * @return tool list
	 */
	def getTools(int treatmentId, int offset, int max) {
		String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

		String url = String.format(toolsUrl, treatmentId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			//TODO: Error handler
		}
	}

	/**
	 * Get task list
	 *
	 * * @param treatmentId # treatment id
	 * @param offset # the tool index where start
	 * @param max # page size
	 * @return task list
	 */
	def getTasks(int treatmentId, int offset, int max) {
		String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks

		String url = String.format(tasksUrl, treatmentId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			//TODO: Error handler
		}
	}
}
