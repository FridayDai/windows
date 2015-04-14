package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.ratchethealth.admin.exceptions.AccountValidationException
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationService {

    // dependency injection for grailsApplication
    def grailsApplication

    def messageSource

    /**
     * Authenticate against backend. when login, authenticate will be needed. It used username and password to call
     * ratchet-v2-server restAPI login.
     *
     * @param request
     * @param response
     * @param cmd
     *
     * @return the authenticated status and errorMessage which restAPI returned.
     */

    def authenticate(HttpServletRequest request, HttpServletResponse response, params) throws AccountValidationException {

        def email = params.email
        def password = params.password

        /**
         * Call backend login api
         *
         * @param username
         * @param password
         * @param client_platform
         * @param client_type
         *
         * @requestMethod post
         *
         * @return
         */
        def url = grailsApplication.config.ratchetv2.server.url.login
        log.info("Call backend service to login with email, password, clientPlatform and clientType.")
        def resp = Unirest.post(url)
                .field("email", email)
                .field("password", password)
                .field("clientPlatform", RatchetConstants.CLIENT_PLATFORM)
                .field("clientType", RatchetConstants.CLIENT_TYPE)
                .asString()
        def result = JSON.parse(resp.body)

        if (resp.status == 200) {
            request.session.token = result.token
            def data = [
                    authenticated: true,
            ]
            log.info("login Authenticate success, token: ${request.session.token}")
            return data
        }

        if (resp.status == 403) {
            log.info("login Authenticate forbidden")
            def rateLimit = result?.error?.errorMessage
            Integer[] args = [rateLimit]
            def errorMessage = messageSource.getMessage("security.errors.login.rateLimit", args, Locale.default)
            throw new AccountValidationException(errorMessage, rateLimit)

        } else {
            def errorMessage = result?.error?.errorMessage
            throw new AccountValidationException(errorMessage)

        }


    }

    /**
     * Logout user, Here is two step. Step one is call ratchet-v2-server restAPI logout and check returned status .
     * Step two ,session in local needs to be invalidate.
     *
     * @param request
     * @param response
     */
    def logout(request, response) throws ServerException {
        def session = request.session
        def token = session?.token
        /**
         * Call backend logout api
         *
         * @param token
         *
         * @requestMethod get
         */
        if (!token) {
            log.error("There is no token.")
            return false
        }

        def url = grailsApplication.config.ratchetv2.server.url.logout
        log.info("Call backend service to logout, token: ${request.session.token}.")
        def resp = Unirest.get(url).asString()
        if (resp.status == 200) {
            log.info("Logout success, token: ${token}")
            session.invalidate()
            return true
        } else {
            def result = JSON.parse(resp.body)
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }
}
