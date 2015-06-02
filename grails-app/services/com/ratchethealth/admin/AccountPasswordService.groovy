package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest



class AccountPasswordService {

    def grailsApplication

    def askForResetPassword(HttpServletRequest request, email, clientType) {

        def url = grailsApplication.config.ratchetv2.server.url.password.reset

            log.info("Call backend service to ask for reset password with email and client type, token: ${request.session.token}.")
            def resp = Unirest.post(url)
                    .field("email", email)
                    .field("clientType", clientType)
                    .asString()

            log.info("Ask for reset password success, token: ${request.session.token}.")
            return resp

    }


    def validPasswordCode(HttpServletRequest request, code)
            throws ServerException {
        def url = grailsApplication.config.ratchetv2.server.url.password.restCheck

        log.info("Call backend service to valid password code, token: ${request.session.token}.")
        def resp = Unirest.get(url)
                .queryString("code", code)
                .asString()

        if (resp.status == 200) {
            log.info("Valid password code success, token: ${request.session.token}.")
            return true
        } else {
            def result = JSON.parse(resp.body)
            def message = result?.error?.errorMessage
            throw new ServerException(resp.status, message)
        }

    }

    def resetPassword(HttpServletRequest request, params)
            throws ServerException {
        def url = grailsApplication.config.ratchetv2.server.url.password.confirm

        log.info("Call backend service to reset password with code and password, token: ${request.session.token}.")
        def resp = Unirest.post(url)
                .field("code", params?.code)
                .field("password", params?.newPassword)
                .field("confirmPassword", params?.confirmPassword)
                .asString()

        if (resp.status == 200) {
            log.info("Reset password success, token: ${request.session.token}.")
            return true
        } else {
            def result = JSON.parse(resp.body)
            def message = result?.error?.errorMessage
            throw new ServerException(resp.status, message)
        }

    }

    def updatePassword(HttpServletRequest request, params)
            throws ServerException {

        def url = grailsApplication.config.ratchetv2.server.url.updatePassword

        log.info("Call backend service to update password with old and new password, token: ${request.session.token}.")
        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("oldPassword", params["old-password"])
                .field("password", params["new-password"])
                .field("confirmPassword", params["confirm-password"])
                .asString()

        if (resp.status == 200) {
            log.info("Update password success, token: ${request.session.token}.")
            return true
        } else {
            def result = JSON.parse(resp.body)
            def message = result?.error?.errorMessage
            throw new ServerException(resp.status, message)
        }
    }
}
