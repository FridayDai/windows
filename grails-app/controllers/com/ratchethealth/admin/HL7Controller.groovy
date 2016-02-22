package com.ratchethealth.admin

import grails.converters.JSON

class HL7Controller extends BaseController {

    def hl7Service

    def beforeInterceptor = [action: this.&auth]

    def index() {
        redirect action: 'getReportingPage'
    }

    def getReportingPage() {
        render view: '/HL7/reporting'
    }

    def getFailuresPage() {
        render view: '/HL7/failures'
    }
}
