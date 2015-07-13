package com.ratchethealth.admin

import com.mashape.unirest.request.GetRequest
import com.mashape.unirest.request.body.MultipartBody
import com.ratchethealth.admin.exceptions.ServerException
import grails.test.mixin.TestFor
import groovy.json.JsonBuilder
import spock.lang.Specification

@TestFor(SchedulerService)
class SchedulerServiceSpec extends Specification{
	def "test changeTimeForSchedule with successful result"() {
		given:
		def jBuilder = new JsonBuilder()
		jBuilder {
			id 2
		}

		MultipartBody.metaClass.asString = { ->
			return [
					status: 200,
					body: jBuilder.toString()
			]
		}

		when:
		def result = service.changeTimeForSchedule('token', 'debugDate')

		then:
		result == true
	}

	def "test changeTimeForSchedule without successful result"() {
		given:
		MultipartBody.metaClass.asString = { ->
			return [
					status: 400,
					body: "body"
			]
		}

		when:
		service.changeTimeForSchedule('token', 'debugDate')

		then:
		ServerException e = thrown()
		e.getMessage() == "body"
	}
}
