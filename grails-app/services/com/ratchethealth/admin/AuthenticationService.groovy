package com.ratchethealth.admin

import com.ratchethealth.admin.exceptions.AccountValidationException
import grails.converters.JSON


class AuthenticationService extends RatchetAdminService {
    def grailsApplication

    def messageSource

    def authenticate(String token, email, password)
            throws AccountValidationException
    {

        def url = grailsApplication.config.ratchetv2.server.url.login
        log.info("Call backend service to login with email, password, clientPlatform and clientType.")

        withPost(url) { req ->
            def resp = req
                    .field("email", email)
                    .field("password", password)
                    .field("clientPlatform", RatchetConstants.CLIENT_PLATFORM)
                    .field("clientType", RatchetConstants.CLIENT_TYPE)
                    .asString()

            if (resp.status == 200) {
                def result = JSON.parse(resp.body)

                def data = [
                        token: result.token,
                        authenticated: true
                ]

                log.info("login Authenticate success, token: ${token}")
                return [resp, data]
            }

            if (resp.status == 403) {
                def result = JSON.parse(resp.body)

                log.info("login Authenticate forbidden")
                def rateLimit = result?.error?.errorMessage

                Integer[] args = [rateLimit]
                def errorMessage = messageSource.getMessage("security.errors.login.rateLimit", args, Locale.default)

                throw new AccountValidationException(errorMessage, rateLimit)
            }

            [resp, null]
        }
    }

    def logout(String token) {
        if (!token) {
            log.error("There is no token.")
            return false
        }

        String url = grailsApplication.config.ratchetv2.server.url.logout
        log.info("Call backend service to logout, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 200) {
                log.info("Logout success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def askForResetPassword(String token, email, clientType) {
        String url = grailsApplication.config.ratchetv2.server.url.password.reset
        log.info("Call backend service to ask for reset password with email and client type, token: ${token}.")

        withPost(url) { req ->
            def resp = req
                    .field("email", email)
                    .field("clientType", clientType)
                    .asString()

            if (resp.status == 200) {
                log.info("Ask for reset password success, token: ${token}.")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def validPasswordCode(String token, code) {
        String url = grailsApplication.config.ratchetv2.server.url.password.restCheck
        log.info("Call backend service to valid password code, token: ${token}.")

        withGet(url) { req ->
            def resp = req
                    .queryString("code", code)
                    .asString()

            if (resp.status == 200) {
                log.info("Valid password code success, token: ${token}.")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def resetPassword(String token, code, newPassword, confirmPassword) {
        String url = grailsApplication.config.ratchetv2.server.url.password.confirm
        log.info("Call backend service to reset password with code and password, token: ${token}.")

        withPost(url) { req ->
            def resp = req
                    .field("code", code)
                    .field("password", newPassword)
                    .field("confirmPassword", confirmPassword)
                    .asString()

            if (resp.status == 200) {
                log.info("Reset password success, token: ${token}.")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def updatePassword(String token, oldPassword, newPassword, confirmPassword) {
        String url = grailsApplication.config.ratchetv2.server.url.updatePassword
        log.info("Call backend service to update password with old and new password, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("oldPassword", oldPassword)
                    .field("password", newPassword)
                    .field("confirmPassword", confirmPassword)
                    .asString()

            if (resp.status == 200) {
                log.info("Update password success, token: ${token}.")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
