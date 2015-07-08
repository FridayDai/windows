package com.ratchethealth.admin

import grails.converters.JSON


class AccountService extends RatchetAdminService {

    def grailsApplication

    def getAccounts(String token, offset, max) {
        String adminsUrl = grailsApplication.config.ratchetv2.server.url.admins
        log.info("Call backend service to get accounts with offset and max, token: ${token}.")

        withGet(token, adminsUrl) { req ->
            def resp = req
                    .queryString("max", max)
                    .queryString("offset", offset)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                def map = [:]
                map.put("recordsTotal", result.totalCount)
                map.put("recordsFiltered", result.totalCount)
                map.put("data", result.items)
                log.info("Get accounts success, token: ${token}")

                return [resp, map]
            }

            [resp, null]
        }
    }

    def createAccount(String token, Account account) {
        String adminsUrl = grailsApplication.config.ratchetv2.server.url.admins
        log.info("Call backend service to create account,token: ${token}.")

         withPost(token, adminsUrl) { req ->
             def resp = req
                     .field("email", account?.email)
                     .asString()

             def result = JSON.parse(resp.body)

             if (resp.status == 201) {
                 account.id = result.id
                 log.info("Create account success, token: ${token}")
                 return [resp, account]
             }

             [resp, null]
         }
    }

    def deleteAccount(String token, int accountId) {
        String adminUrl = grailsApplication.config.ratchetv2.server.url.oneAdmin
        def url = String.format(adminUrl, accountId)
        log.info("Call backend service to delete account,token: ${token}.")

        withDelete(token, url) { req ->
            def resp = req
                    .header("X-Auth-Token", token)
                    .asString()

            if (resp.status == 204) {
                log.info("Delete account success, token: ${token}")

                return [resp, true]
            }

            [resp, null]
        }
    }

    def updateAccount(String token, int accountId, email, enabled) {
        String adminUrl = grailsApplication.config.ratchetv2.server.url.oneAdmin
        def url = String.format(adminUrl, accountId)
        log.info("Call backend service to update account,token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("email", email)
                    .field("enabled", enabled)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                log.info("Update account success, token: ${token}")
                return [resp, result]
            }

            [resp, null]
        }
    }

    def activateAccount(String token, code, newPassword, confirmPassword) {
        String confirmAdminUrl = grailsApplication.config.ratchetv2.server.url.admin.confirm
        log.info("Call backend service to activate account,token: ${token}.")

        withPost(token, confirmAdminUrl) { req ->
            def resp = req
                    .field("code", code)
                    .field("password", newPassword)
                    .field("confirmPassword", confirmPassword)
                    .asString()

            if (resp.status == 200) {
                log.info("Activate account success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def validateCode(String token, String code) {
        String validateCodeUrl = grailsApplication.config.ratchetv2.server.url.admin.validateCode
        log.info("Call backend service to validate code,token: ${token}.")
        def url = String.format(validateCodeUrl, code)

        withPost(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 200) {
                log.info("Validate code success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
