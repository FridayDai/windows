package com.ratchethealth.admin

import com.mashape.unirest.request.GetRequest
import com.mashape.unirest.request.HttpRequestWithBody
import com.mashape.unirest.request.body.MultipartBody
import com.ratchethealth.admin.exceptions.ServerException
import grails.test.mixin.TestFor
import groovy.json.JsonBuilder
import spock.lang.Specification

@TestFor(AnnouncementService)
class AnnouncementServiceSpec extends Specification {
	def "test getAnnouncements with successful result"() {
		given:
		def jBuilder = new JsonBuilder()
		jBuilder {
			totalCount 2
			items 1, 2
		}

		GetRequest.metaClass.asString = { ->
			return [
					status: 200,
					body: jBuilder.toString()
			]
		}

		when:
		def result = service.getAnnouncements('token', 1, 1)

		then:
		result.recordsTotal == 2
		result.recordsFiltered == 2
		result.data == [1, 2]
	}

	def "test getAnnouncements without successful result"() {
		given:
		GetRequest.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		when:
		service.getAnnouncements('token', 1, 1)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}

	def "test addAnnouncement with successful result"() {
		given:
		def jBuilder = new JsonBuilder()
		jBuilder {
			id 1
		}

		MultipartBody.metaClass.asString = { ->
			return [
					status: 201,
					body: jBuilder.toString()
			]
		}

		Announcement announcement = new Announcement()

		when:
		def result = service.addAnnouncement('token', announcement)

		then:
		result.id == 1
	}

	def "test addAnnouncement without successful result"() {
		given:
		MultipartBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		Announcement announcement = new Announcement()

		when:
		service.addAnnouncement('token', announcement)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}

	def "test editAnnouncement with successful result"() {
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

		Announcement announcement = new Announcement()
		announcement.id = 1
		announcement.status = 1
		announcement.content = 'content'
		announcement.colorHex = '111111'


		when:
		def result = service.editAnnouncement('token', announcement)

		then:
		result.id == 1
		result.status == 1
		result.content == 'content'
		result.colorHex == '111111'
	}

	def "test editAnnouncement without successful result"() {
		given:
		MultipartBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		Announcement announcement = new Announcement()

		when:
		service.editAnnouncement('token', announcement)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}

	def "test deleteAnnouncement with successful result"() {
		given:
		HttpRequestWithBody.metaClass.asString = { ->
			return [
					status: 204,
					body: ''
			]
		}

		Announcement announcement = new Announcement()

		when:
		def result = service.deleteAnnouncement('token', announcement)

		then:
		result == true
	}

	def "test deleteAnnouncement without successful result"() {
		given:
		HttpRequestWithBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		Announcement announcement = new Announcement()

		when:
		service.deleteAnnouncement('token', announcement)

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}
}
