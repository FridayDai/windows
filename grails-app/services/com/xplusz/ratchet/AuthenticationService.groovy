package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationService {

    def grailsApplication

    /**
     * Authenticate against backend
     *
     * @param request
     * @param response
     * @param params
     */
    def authenticate(HttpServletRequest request, HttpServletResponse response, params) {
        // TODO replace following code with api calls to backend and set uid to session id returned from backend

        if (!(params.username && params.password)) {
            return false
        }

        /**
         * Call backend login api
         *
         * @param username
         * @param password
         *
         * @requestMethod post
         *
         * @return
         */
        def resp = Unirest.post(grailsApplication.config.ratchetv2.server.login.url)
                .field("username", params.username)
                .field("password", params.password)
                .asString()
        def result = JSON.parse(resp.body)

        def data
        if (resp?.status == 200) {
            request.session.uid = result.sessionId
            request.session.identifier = UUID.randomUUID().toString()
            data = [
                    authenticated: true,
            ]
        } else {
            data = [
                    authenticated: false,
                    errorMessage : result.error.errorMessage
            ]
        }
        return data
    }

    /**
     * Logout user
     *
     * @param request
     * @param response
     */

    def logout(request, response) {
        def session = request.session
        def uid = session?.uid

        /**
         * Call backend logout api
         *
         * @param sessionId
         *
         * @requestMethod get
         */
        def resp = Unirest.get(grailsApplication.config.ratchetv2.server.logout.url)
                .queryString("sessionId", "${uid}")
                .asString()

        if (!uid || resp.status != 200) {
            log.warn("No user login in the session.")
            return false
        }
        log.info("Logout $uid")
        session.invalidate()
        return true
    }

}
