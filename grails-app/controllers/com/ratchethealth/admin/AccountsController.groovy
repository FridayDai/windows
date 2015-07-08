package com.ratchethealth.admin

import grails.converters.JSON

class AccountsController extends BaseController {

    def beforeInterceptor = [action: this.&auth, except: ['activateAccount', 'confirmAccountPassword']]

    static allowedMethods = [addAccount: ['POST']]
    def accountService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE
        def isAjax = params.ajax ?: false
        String token = request.session.token

        def accountList = accountService.getAccounts(token, page, pageSize)

        if (isAjax) {
            render accountList as JSON
        } else {
            render view: '/account/accounts', model: [accountList: accountList, pagesize: pageSize]
        }
    }

    def getAccounts() {
        def offset = params?.start
        def max = params?.length
        String token = request.session.token

        def resp = accountService.getAccounts(token, offset, max)

        render resp as JSON
    }

    def addAccount(Account account) {
        String token = request.session.token

        account = accountService.createAccount(token, account)

        if (account.id) {
            render account as JSON
        }
    }

    def deleteAccount() {
        int accountId = params.accountId as int
        String token = request.session.token

        def resp = accountService.deleteAccount(token, accountId)

        def result = [resp: resp]
        render result as JSON
    }

    def updateAccount() {
        String token = request.session.token

        def resp = accountService.updateAccount(token, params?.accountId as int, params?.email, params?.enabled)

        render resp as JSON
    }

    def activateAccount() {
        String token = request.session.token
        String code = params.code
        def resp = accountService.validateCode(token, code)

        if (resp) {
            render(view: '/account/activeAccount', model: [code: code])
        }
    }

    def confirmAccountPassword() {
        String token = request.session.token

        def resp = accountService.activateAccount(token, params?.code, params?.newPassword, params?.confirmPassword)

        if (resp) {
            render view: '/security/login'
        }
    }
}
