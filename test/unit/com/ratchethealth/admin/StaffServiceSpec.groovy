package com.ratchethealth.admin

import com.mashape.unirest.request.HttpRequestWithBody
import com.mashape.unirest.request.body.MultipartBody
import com.ratchethealth.admin.exceptions.ServerException
import grails.test.mixin.TestFor
import groovy.json.JsonBuilder
import spock.lang.Specification

@TestFor(StaffService)
class StaffServiceSpec extends Specification {
	def "test addAgent with successful result"() {
		given:
		def jBuilder = new JsonBuilder()
		jBuilder {
			id 2
		}

		MultipartBody.metaClass.asString = { ->
			return [
					status: 201,
					body: jBuilder.toString()
			]
		}

		Staff agent = new Staff()
		agent.clientId = 1
		agent.email = 'email'
		agent.firstName = 'firstName'
		agent.lastName = 'lastName'
		agent.type = 10
		agent.isDoctor = true

		when:
		def result = service.addAgent('token', agent)

		then:
		result.id == 2
		result.clientId == 1
		result.email == 'email'
		result.firstName == 'firstName'
		result.lastName == 'lastName'
		result.type == 10
		result.isDoctor == true
	}

	def "test addAgent without successful result"() {
		given:
		MultipartBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		Staff agent = new Staff()
		agent.clientId = 1
		agent.email = 'email'
		agent.firstName = 'firstName'
		agent.lastName = 'lastName'
		agent.type = 10
		agent.isDoctor = true

		when:
		service.addAgent('token', agent)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}

	def "test updateAgent with successful result"() {
		given:
		def jBuilder = new JsonBuilder()
		jBuilder {
			id 1
		}

		MultipartBody.metaClass.asString = { ->
			return [
					status: 200,
					body: jBuilder.toString()
			]
		}

		Staff agent = new Staff()
		agent.clientId = 1
		agent.email = 'email'
		agent.firstName = 'firstName'
		agent.lastName = 'lastName'
		agent.type = 10
		agent.isDoctor = true

		when:
		def result = service.updateAgent('token', agent)

		then:
		result == true
	}

	def "test updateAgent without successful result"() {
		given:
		MultipartBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		Staff agent = new Staff()
		agent.clientId = 1
		agent.email = 'email'
		agent.firstName = 'firstName'
		agent.lastName = 'lastName'
		agent.type = 10
		agent.isDoctor = true

		when:
		service.updateAgent('token', agent)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}

	def "test deleteAgent with successful result"() {
		given:
		HttpRequestWithBody.metaClass.asString = { ->
			return [
					status: 204,
					body: ''
			]
		}

		when:
		def result = service.deleteAgent('token', 1)

		then:
		result == true
	}

	def "test deleteAgent without successful result"() {
		given:
		HttpRequestWithBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		when:
		service.deleteAgent('token', 1)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}
}
