package com.xplusz.ratchet

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

/**
 * Base Controller.
 */
class BaseController {

    def baseService
    /**
     *  Verify Permissions.
     * @return
     */
    protected auth() {
        if (!session.uid) {
            redirect(uri: "/login")
            return false
        }

        def sessionId = session.uid
        def resp = baseService.validateSession(sessionId)
        if (resp.status == 200) {
            return true
        } else {
            redirect(uri: "/login")
            return false
        }
    }

    protected renderAuthForbiddenResponse(String msg) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        Map data = [
                errorId     : HttpServletResponse.SC_FORBIDDEN,
                errorMessage: msg
        ]
        JSON json = new JSON([response: null, error: data])
        json.render(response)
    }

    def handleException(RuntimeException exception) {
        log.error(exception)
    }

}
