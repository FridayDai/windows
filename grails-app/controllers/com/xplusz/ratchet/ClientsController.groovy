package com.xplusz.ratchet

import grails.converters.JSON

class ClientsController extends BaseController {
	def beforeInterceptor = [action: this.&auth]

	def clientService
	def staffService
	def treatmentService

	def index() {
		def page = params.page?:RatchetConstants.DEFAULT_PAGE_OFFSET
		def pagesize = params.pagesize?:RatchetConstants.DEFAULT_PAGE_SIZE
		def isAjax = params.ajax?:false

		def clientList = clientService.getClients(page, pagesize)

		if (isAjax) {
			render clientList as JSON
		} else {
			render view: '/client/clients', model: [clientList: clientList]
		}
	}

	def addClient(Client client) {
		// Client information
		def logoFile = params.logo

		// Transfer logo file to Base64 string
		client.logo = Base64.encoder.encodeToString(logoFile?.getBytes())

		client = clientService.createClient(client)

		if (client.id) {
			def agent = new Staff(id: client.id, email: params.agentEmail, firstName: params.agentFirstName, lastName: params.agentLastName)
			agent = staffService.addAgent(agent)

			if (agent.id) {
				client.clientStaff = agent

				render client as JSON
			}
		} else {
			// TODO: Error handle
		}
	}

	def clientDetail() {
		def page = params.page?:RatchetConstants.DEFAULT_PAGE_OFFSET
		def pagesize = params.pagesize?:RatchetConstants.DEFAULT_PAGE_SIZE

		int clientId = params.id.toInteger()

		def client = clientService.getClient(clientId)
		def treatmentList = treatmentService.getTreatments(client.id, page.toInteger(), pagesize.toInteger())

		render view: '/client/clientDetail', model: [client: client, treatmentList: treatmentList]
	}

	def editClient(Client client) {
		// Client information
		def logoFile = params.logo

		// Transfer logo file to Base64 string
		client.logo = Base64.encoder.encodeToString(logoFile?.getBytes())

		client.id = params.id.toInteger()

		def success = clientService.updateClient(client)

		if (success) {
			render client as JSON
		} else {
			// TODO: Error handle
		}
	}

	def editAgent(Staff agent) {
		agent.clientId = params.clientId.toInteger()
		agent.id = params.agentId.toInteger()

		def success = staffService.updateAgent(agent)

		if (success) {
			render agent as JSON
		} else {
			// TODO: Error handle
		}
	}

	def addAgent(Staff agent) {
		agent.clientId = params.clientId.toInteger()

		agent = staffService.addAgent(agent)

		if (agent.id) {
			render agent as JSON
		} else {
			// TODO: Error handle
		}
	}

	def deleteAgent() {
		int agentId = params.agentId.toInteger()

		def success = staffService.deleteAgent(agentId)

		if (success) {
			render status: 204
		} else {
			// TODO: Error handle
		}
	}
}
