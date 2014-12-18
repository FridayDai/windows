package com.xplusz.ratchet
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
            render(view: '/login/login')
        } else if (request.method == "POST") {
            authenticate()
        }
    }

    /**
     * Handle logout.
     * @return
     */
    def logout() {
        if (!authenticationService.logout(request, response)) {
            log.warn("logout failed")
        }
        redirect(uri: '/login')
    }

    private def authenticate() {
        def resp = authenticationService.authenticate(request, response, params)
        if (resp) {
            if (resp?.authenticated) {
                redirect(uri: '/')
            } else {
                render(view: '/login/login', model: [errorMsg: resp.errorMessage])
            }
        } else {
            def errorMsg = message(code: "security.errors.login.missParams")
            render(view: '/login/login', model: [errorMsg: errorMsg])
        }
    }

}
