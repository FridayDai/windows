package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class AccountService {

    def grailsApplication

    /**
     * Get account list
     *
     * @param offset # page index from 0
     * @param max # page size
     * @return account list
     */
    def getAccounts(HttpServletRequest request, offset, max)
            throws ServerException {
        String adminsUrl = grailsApplication.config.ratchetv2.server.url.admins
        log.info("Call backend service to get accounts with offset and max, token: ${request.session.token}.")

        def resp = Unirest.get(adminsUrl)
                .header("X-Auth-Token", request.session.token)
                .queryString("offset", offset)
                .queryString("max", max)
                .asString()

        def result = JSON.parse(resp.body)

        if (resp.status == 200) {
            def map = [:]
            map.put("recordsTotal", result.totalCount)
            map.put("recordsFiltered", result.totalCount)
            map.put("data", result.items)
            log.info("Get accounts success, token: ${request.session.token}")
            return map
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Create new account
     *
     * @param account # new client instance
     * @return account   # created account
     */
    def createAccount(HttpServletRequest request, Account account) throws ServerException {
        String adminsUrl = grailsApplication.config.ratchetv2.server.url.admins
        log.info("Call backend service to create account,token: ${request.session.token}.")

        def resp = Unirest.post(adminsUrl)
                .header("X-Auth-Token", request.session.token)
                .field("email", account?.email)
                .asString()

        def result = JSON.parse(resp.body)

        if (resp.status == 201) {
            account.id = result.id
            log.info("Create account success, token: ${request.session.token}")
            return account
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Delete account
     *
     */
    def deleteAccount(HttpServletRequest request, accountId) throws ServerException {
        String adminUrl = grailsApplication.config.ratchetv2.server.url.oneAdmin
        def url = String.format(adminUrl, accountId)
        log.info("Call backend service to delete account,token: ${request.session.token}.")

        def resp = Unirest.delete(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 204) {
            log.info("Delete account success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Update account
     *
     */
    def updateAccount(HttpServletRequest request, params) throws ServerException {
        String adminUrl = grailsApplication.config.ratchetv2.server.url.oneAdmin
        def url = String.format(adminUrl, params?.accountId)
        log.info("Call backend service to update account,token: ${request.session.token}.")

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("email", params?.email)
                .field("enabled", params?.enabled)
                .asString()
        def result = JSON.parse(resp.body)

        if (resp.status == 200) {
            log.info("Update account success, token: ${request.session.token}")
            return result
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     *
     * @param request
     * @param params
     * @return
     * @throws ServerException
     */
    def activateAccount(HttpServletRequest request, params) throws ServerException {
        String confirmAdminUrl = grailsApplication.config.ratchetv2.server.url.admin.confirm
        log.info("Call backend service to activate account,token: ${request.session.token}.")

        def resp = Unirest.post(confirmAdminUrl)
                .header("X-Auth-Token", request.session.token)
                .field("code", params?.code)
                .field("password", params?.newPassword)
                .field("confirmPassword", params?.confirmPassword)
                .asString()

        if (resp.status == 200) {
            log.info("Activate account success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }
}
