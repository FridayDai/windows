package com.ratchethealth.admin

import grails.converters.JSON

class ClientService extends RatchetAdminService {
    def grailsApplication

    def getClients(String token, offset, max) {
        String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients
        log.info("Call backend service to get clients with offset and max, token: ${token}.")

        withGet(token, clientsUrl) { req ->
            def resp = req
                    .queryString("offset", offset)
                    .queryString("max", max)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                def map = [:]

                map.put("recordsTotal", result.totalCount)
                map.put("recordsFiltered", result.totalCount)
                map.put("data", result.items)
                log.info("Get clients success, token: ${token}")

                return [resp, map]
            }

            [resp, null]
        }
    }

    def getClient(String token, int clientId) {
        String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

        def clientUrl = String.format(oneClientUrl, clientId)
        log.info("Call backend service to get client, token: ${token}.")

        withGet(token, clientUrl) { req ->
            def resp = req.asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                log.info("Get client success, token: ${token}")

                return [resp, result]
            }

            [resp, null]
        }
    }

    def createClient(String token, Client client) {
        String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients
        log.info("Call backend service to create clients with name, logo, favIcon, subDomain, " +
                "portalName and primaryColorHex, token: ${token}.")

        withPost(token, clientsUrl) { req ->
            def resp = req
                    .field("name", client.name)
                    .field("logo", client.logo)
                    .field("logoFileName", client.logoFileName)
                    .field("favIcon", client.favIcon)
                    .field("favIconFileName", client.favIconFileName)
                    .field("subDomain", client.subDomain)
                    .field("portalName", client.portalName)
                    .field("primaryColorHex", client.primaryColorHex)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 201) {
                client.logo = null
                client.favIcon = null
                client.id = result.id
                log.info("Create client success, token: ${token}")
                return [resp, client]
            }

            [resp, null]
        }
    }

    def updateClient(String token, Client client) {
        String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

        def clientUrl = String.format(oneClientUrl, client.id)
        log.info("Call backend service to update clients with name, subDomain, " +
                "protalName, logo and facIcon, token: ${token}.")

        withPost(token, clientUrl) { req ->
            def resp = req
                    .field("name", client.name)
                    .field("subDomain", client.subDomain)
                    .field("portalName", client.portalName)
                    .field("primaryColorHex", client.primaryColorHex)
                    .field("logo", client.logo)
                    .field("logoFileName", client.logoFileName)
                    .field("favIcon", client.favIcon)
                    .field("favIconFileName", client.favIconFileName)
                    .asString()

            if (resp.status == 200) {
                log.info("Update client success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
