package com.ratchethealth.admin

import com.ratchethealth.admin.exceptions.AccountValidationException

/**
 * Authentication controller for login/logout
 *
 */
class AuthenticationController extends BaseController {

    static allowedMethods = [login: ['POST', 'GET'], logout: ['GET']]

    def beforeInterceptor = [action: this.&auth, except: ['login']]

    def authenticationService

    def login() {
        if (request.method == "GET") {
            render(view: '/security/login')
        } else if (request.method == "POST") {
            def resp = authenticationService.authenticate(request, params)

            if (resp?.authenticated) {
                redirect(uri: '/')
            }
        }
    }

    /**
     * Handle logout.
     * @return
     */
    def logout() {
        def result = authenticationService.logout(request)
        if (result) {
            redirect(uri: '/login')
        }
    }

    /**
     * handle AccountValidationException, when Exception happened, it should be back to login.
     * @param request
     * @param response
     * @param params
     * @return
     */

    def handleAccountValidationException(AccountValidationException e) {
        def time

        if (e.limitSeconds) {
            time = e.limitSeconds
        } else {
            time = null
        }
        log.error("Account validation exception : ${e.message}, stack trace: ${e.getStackTrace()}, token: ${session.token}")
        def msg = e.getMessage()
        render(view: '/security/login', model: [errorMsg: msg, rateLimit: time])

    }

}
