package com.ratchethealth.admin

import grails.converters.JSON

class ClientsController extends BaseController {
    def beforeInterceptor = [action: this.&auth]

    def clientService
    def staffService
    def treatmentService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE
        def isAjax = params.ajax ?: false
        String token = request.session.token

        def clientList = clientService.getClients(token, page, pageSize)

        if (isAjax) {
            render clientList as JSON
        } else {
            render view: '/client/clients', model: [clientList: clientList, pagesize: pageSize]
        }
    }

    def addClient(Client client) {
        String token = request.session.token

        // Client information
        def logoFile = params.logo
        def favIconFile = params.favIcon

        // Transfer logo file to Base64 string
        client.logo = Base64.encoder.encodeToString(logoFile?.getBytes()).encodeAsURL()
        client.favIcon = Base64.encoder.encodeToString(favIconFile?.getBytes()).encodeAsURL()
        client.logoFileName = logoFile.fileItem.fileName
        client.favIconFileName = favIconFile.fileItem.fileName

        client = clientService.createClient(token, client)

        if (client.id) {
            render client as JSON
        }
    }

    def getClients() {
        def offset = params?.start
        def max = params?.length
        String token = request.session.token

        def resp = clientService.getClients(token, offset, max)

        render resp as JSON
    }

    def clientDetail() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int clientId = params.id as int

        String token = request.session.token

        def client = clientService.getClient(token, clientId)
        def treatmentList = treatmentService.getTreatments(token, client.id as int, page, pageSize)

        render view: '/client/clientDetail',
                model: [
                        client: client,
                        treatmentList: treatmentList,
                        pagesize: pageSize
                ]
    }

    def editClient(Client client) {
        // Client information
        def logoFile = params.logo
        def favIconFile = params.favIcon
        String token = request.session.token

        // Transfer logo file to Base64 string
        client.logo = Base64.encoder.encodeToString(logoFile?.getBytes()).encodeAsURL()
        client.favIcon = Base64.encoder.encodeToString(favIconFile?.getBytes()).encodeAsURL()
        client.logoFileName = logoFile.fileItem.fileName
        client.favIconFileName = favIconFile.fileItem.fileName

        client.id = params.id.toInteger()

        def success = clientService.updateClient(token, client)

        if (success) {
            render client as JSON
        }
    }

    def editAgent(Staff agent) {
        agent.clientId = params.clientId as int
        agent.id = params.agentId as int
        String token = request.session.token

        def success = staffService.updateAgent(token, agent)

        if (success) {
            render agent as JSON
        }
    }

    def addAgent(Staff agent) {
        String token = request.session.token
        agent.clientId = params.clientId as int

        agent = staffService.addAgent(token, agent)

        if (agent.id) {
            render agent as JSON
        }
    }

    def deleteAgent() {
        String token = request.session.token
        int agentId = params.agentId as int

        def success = staffService.deleteAgent(token, agentId)

        if (success) {
            render status: 204
        }
    }
}
