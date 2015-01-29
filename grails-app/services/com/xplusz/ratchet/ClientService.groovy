package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import exceptions.ClientValidationException
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
	def getClients(offset, max) {
		String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients

		def resp = Unirest.get(clientsUrl)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def errorMessage = result?.error?.errorMessage
			throw new ClientValidationException(errorMessage)
		}
	}

	/**
	 * Get one client by id
	 *
	 * @param clientId
	 * @return client
	 */
	def getClient(int clientId) {
		String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

		def clientUrl = String.format(oneClientUrl, clientId)

		def resp = Unirest.get(clientUrl).asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def errorMessage = result?.errors?.message
			throw new ClientValidationException(errorMessage)
		}
	}

	/**
	 * Create new client
	 *
	 * @param client # new client instance
	 * @return client   # created client
	 */
	def createClient(Client client) {
		String clientsUrl = grailsApplication.config.ratchetv2.server.url.clients

		def resp = Unirest.post(clientsUrl)
				.field("name", client.name)
				.field("logo", client.logo)
				.field("subDomain", client.subDomain)
				.field("portalName", client.portalName)
				.field("primaryColorHex", client.primaryColorHex)
				.asString()

		if (resp.status == 201) {
			def result = JSON.parse(resp.body)

			client.id = result.id
			return client
		} else {
			def errorMessage = result?.errors?.message
			throw new ClientValidationException(errorMessage)
		}
	}

	/**
	 * Update client
	 *
	 * @param client # updated client instance
	 * @return isSuccess
	 */
	def updateClient(Client client) {
		String oneClientUrl = grailsApplication.config.ratchetv2.server.url.oneClient

		def clientUrl = String.format(oneClientUrl, client.id)

		def resp = Unirest.post(clientUrl)
				.field("name", client.name)
				.field("subDomain", client.subDomain)
				.field("portalName", client.portalName)
				.field("primaryColorHex", client.primaryColorHex)
				.field("logo", client.logo)
				.asString()

		if (resp.status == 200) {
			return true
		} else {
			def errorMessage = result?.errors?.message
			throw new ClientValidationException(errorMessage)
		}

		return false
	}
}
