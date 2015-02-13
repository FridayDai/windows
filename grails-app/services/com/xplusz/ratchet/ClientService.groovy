package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import com.xplusz.ratchet.exceptions.ServerException
import grails.converters.JSON

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
	def getClients(offset, max) throws ServerException {
		String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients

		def resp = Unirest.get(clientsUrl)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		def result = JSON.parse(resp.body)

		if (resp.status == 200) {
			return result
		} else {
			String errorMessage = result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Get one client by id
	 *
	 * @param clientId
	 * @return client
	 */
	def getClient(int clientId) throws ServerException {
		String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

		def clientUrl = String.format(oneClientUrl, clientId)

		def resp = Unirest.get(clientUrl).asString()

		def result = JSON.parse(resp.body)

		if (resp.status == 200) {
			return result
		} else {
			String errorMessage = result?.errors?.message
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Create new client
	 *
	 * @param client # new client instance
	 * @return client   # created client
	 */
	def createClient(Client client) throws ServerException {
		String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients

		def resp = Unirest.post(clientsUrl)
				.field("name", client.name)
				.field("logo", client.logo)
				.field("subDomain", client.subDomain)
				.field("portalName", client.portalName)
				.field("primaryColorHex", client.primaryColorHex)
				.asString()

		def result = JSON.parse(resp.body)

		if (resp.status == 201) {
			client.id = result.id
			return client
		} else {
			String errorMessage = result?.errors[0]?.message
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Update client
	 *
	 * @param client # updated client instance
	 * @return isSuccess
	 */
	def updateClient(Client client) throws ServerException {
		String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

		def clientUrl = String.format(oneClientUrl, client.id)

		def resp = Unirest.post(clientUrl)
				.field("name", client.name)
				.field("subDomain", client.subDomain)
				.field("portalName", client.portalName)
				.field("primaryColorHex", client.primaryColorHex)
				.field("logo", client.logo)
				.asString()

		def result = JSON.parse(resp.body)

		if (resp.status == 200) {
			return true
		} else {
			String errorMessage = result?.errors?.message
			throw new ServerException(errorMessage)
		}
	}
}
