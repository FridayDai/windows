package com.ratchethealth.admin

import grails.converters.JSON

class ClientsController extends BaseController {
    def beforeInterceptor = [action: this.&auth]

    def clientService
    def staffService
    def treatmentService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE
        def isAjax = params.ajax ?: false

        def clientList = clientService.getClients(request, response, page, pagesize)

        if (isAjax) {
            render clientList as JSON
        } else {
            render view: '/client/clients', model: [clientList: clientList, pagesize: pagesize]
        }
    }

    def addClient(Client client) {
        // Client information
        def logoFile = params.logo
        def favIconFile = params.favIcon

        // Transfer logo file to Base64 string
        client.logo = Base64.encoder.encodeToString(logoFile?.getBytes()).encodeAsURL()
        client.favIcon = Base64.encoder.encodeToString(favIconFile?.getBytes()).encodeAsURL()

        client = clientService.createClient(request, response, client)

        if (client.id) {
            render client as JSON
        }
    }

    def getClients() {
        def offset = params?.start
        def max = params?.length
        def resp = clientService.getClients(request, response, offset, max)
        render resp as JSON
    }

    def clientDetail() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int clientId = params.id.toInteger()

        def client = clientService.getClient(request, response, clientId)
        def treatmentList = treatmentService.getTreatments(request, response, client.id, page.toInteger(), pagesize.toInteger())

        render view: '/client/clientDetail', model: [client: client, treatmentList: treatmentList]
    }

    def editClient(Client client) {
        // Client information
        def logoFile = params.logo
        def favIconFile = params.favIcon

        // Transfer logo file to Base64 string
        client.logo = Base64.encoder.encodeToString(logoFile?.getBytes()).encodeAsURL()
        client.favIcon = Base64.encoder.encodeToString(favIconFile?.getBytes()).encodeAsURL()

        client.id = params.id.toInteger()

        def success = clientService.updateClient(request, response, client)

        if (success) {
            render client as JSON
        }
    }

    def editAgent(Staff agent) {
        agent.clientId = params.clientId.toInteger()
        agent.id = params.agentId.toInteger()

        def success = staffService.updateAgent(request, response, agent)

        if (success) {
            render agent as JSON
        }
    }

    def addAgent(Staff agent) {
        agent.clientId = params.clientId.toInteger()

        agent = staffService.addAgent(request, response, agent)

        if (agent.id) {
            render agent as JSON
        }
    }

    def deleteAgent() {
        int agentId = params.agentId.toInteger()

        def success = staffService.deleteAgent(request, response, agentId)

        if (success) {
            render status: 204
        }
    }
}
