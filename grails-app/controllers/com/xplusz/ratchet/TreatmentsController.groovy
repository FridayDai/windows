package com.xplusz.ratchet

import grails.converters.JSON

class TreatmentsController extends BaseController {

	def beforeInterceptor = [action: this.&auth]

	def treatmentService

	def addTreatment(Treatment treatment) {
		treatment.clientId = params.clientId.toInteger()

		treatment = treatmentService.createTreatment(treatment)

		if (treatment.id) {
			render treatment as JSON
		} else {
			// TODO: Error handle
		}
	}

	def treatmentDetail() {
		int clientId = params.clientId.toInteger()
		int treatmentId = params.treatmentId.toInteger()

		def treatment = treatmentService.getTreatment(clientId, treatmentId)

		render view: '/treatment/treatmentDetail', model: [clientId: clientId, treatment: treatment]
	}

	def editTreatment(Treatment treatment) {
		treatment.clientId = params.clientId.toInteger()
		treatment.id = params.treatmentId.toInteger()

		def success = treatmentService.updateTreatment(treatment)

		if (success) {
			render status: 200
		} else {
			// TODO: Error handle
		}
	}

	def closeTreatment() {
		int clientId = params.clientId.toInteger()
		int treatmentId = params.treatmentId.toInteger()

		def success = treatmentService.closeTreatment(clientId, treatmentId)

		if (success) {
			render status: 204
		} else {
			// TODO: Error handle
		}
	}

	def getTools() {
		def page = params.page?:RatchetConstants.DEFAULT_PAGE_OFFSET
		def pagesize = params.pagesize?:RatchetConstants.DEFAULT_PAGE_SIZE

		int treatmentId = params.treatmentId.toInteger()

		def toolList = treatmentService.getTools(treatmentId, page, pagesize)

		if (toolList) {
			render toolList as JSON
		} else {
			//TODO: Error  handle
		}
	}

	def getTasks() {
		def page = params.page?:RatchetConstants.DEFAULT_PAGE_OFFSET
		def pagesize = params.pagesize?:RatchetConstants.DEFAULT_PAGE_SIZE

		int treatmentId = params.treatmentId.toInteger()

		def taskList = treatmentService.getTasks(treatmentId, page, pagesize)

		if (taskList) {
			render taskList as JSON
		} else {
			//TODO: Error  handle
		}
	}
}
