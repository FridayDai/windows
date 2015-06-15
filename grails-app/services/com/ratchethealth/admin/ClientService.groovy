package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import com.ratchethealth.admin.exceptions.ApiAccessException
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class ClientService {
    // dependency injection for grailsApplication
    def grailsApplication

    /**
     * Get client list
     *
     * @param offset # page index from 0
     * @param max # page size
     * @return client list
     */
    def getClients(HttpServletRequest request, offset, max)
            throws ServerException, ApiAccessException {
        try {
            String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients
            log.info("Call backend service to get clients with offset and max, token: ${request.session.token}.")

            def resp = Unirest.get(clientsUrl)
                    .header("X-Auth-Token", request.session.token)
                    .queryString("offset", offset)
                    .queryString("max", max)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                def map = [:]
                map.put("recordsTotal", result.totalCount)
                map.put("recordsFiltered", result.totalCount)
                map.put("data", result.items)
                log.info("Get clients success, token: ${request.session.token}")
                return map
            } else {
                String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
                throw new ServerException(resp.status, errorMessage)
            }
        } catch (UnirestException e) {
            throw new ApiAccessException(e.message)
        }
    }

    /**
     * Get one client by id
     *
     * @param clientId
     * @return client
     */
    def getClient(HttpServletRequest request, int clientId)
            throws ServerException, ApiAccessException {
        try {
            String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

            def clientUrl = String.format(oneClientUrl, clientId)
            log.info("Call backend service to get client, token: ${request.session.token}.")

            def resp = Unirest.get(clientUrl)
                    .header("X-Auth-Token", request.session.token)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                log.info("Get client success, token: ${request.session.token}")
                return result
            } else {
                String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
                throw new ServerException(resp.status, errorMessage)
            }
        } catch (UnirestException e) {
            throw new ApiAccessException(e.message)
        }
    }

    /**
     * Create new client
     *
     * @param client # new client instance
     * @return client   # created client
     */
    def createClient(HttpServletRequest request, Client client)
            throws ServerException, ApiAccessException {
        try {
            String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients
            log.info("Call backend service to creat clients with name, logo, favIcon, subDomain, portalName and primaryColorHex, token: ${request.session.token}.")

            def resp = Unirest.post(clientsUrl)
                    .header("X-Auth-Token", request.session.token)
                    .field("name", client.name)
                    .field("logo", client.logo)
                    .field("favIcon", client.favIcon)
                    .field("subDomain", client.subDomain)
                    .field("portalName", client.portalName)
                    .field("primaryColorHex", client.primaryColorHex)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 201) {
                client.logo = null
                client.favIcon = null
                client.id = result.id
                log.info("Create client success, token: ${request.session.token}")
                return client
            } else {
                String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
                throw new ServerException(resp.status, errorMessage)
            }
        } catch (UnirestException e) {
            throw new ApiAccessException(e.message)
        }
    }

    /**
     * Update client
     *
     * @param client # updated client instance
     * @return isSuccess
     */
    def updateClient(HttpServletRequest request, Client client)
            throws ServerException, ApiAccessException {
        try {
            String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

            def clientUrl = String.format(oneClientUrl, client.id)
            log.info("Call backend service to update clients with name, subDomain, protalName, logo and facIcon, token: ${request.session.token}.")

            def resp = Unirest.post(clientUrl)
                    .header("X-Auth-Token", request.session.token)
                    .field("name", client.name)
                    .field("subDomain", client.subDomain)
                    .field("portalName", client.portalName)
                    .field("primaryColorHex", client.primaryColorHex)
                    .field("logo", client.logo)
                    .field("favIcon", client.favIcon)
                    .asString()

            if (resp.status == 200) {
                log.info("Update client success, token: ${request.session.token}")
                return true
            } else {
                def result = JSON.parse(resp.body)

                String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
                throw new ServerException(resp.status, errorMessage)
            }
        } catch (UnirestException e) {
            throw new ApiAccessException(e.message)
        }
    }
}
