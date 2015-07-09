package com.ratchethealth.admin

import com.ratchethealth.admin.exceptions.ApiAccessException
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(RatchetAPIService)
class RatchetAPIServiceSpec extends Specification {

	def "test handle error as resp with null"() {
		given: "Set resp null"
		service.messageSource = [getMessage: { key, args, locale -> return "message" }]

		when: "Call handleError with resp"
		service.handleError(null)

		then: "Exception caught"
		ApiAccessException e = thrown()
		e.getMessage() == "message"
	}
}
