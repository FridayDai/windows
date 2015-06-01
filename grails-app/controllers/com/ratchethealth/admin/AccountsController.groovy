package com.ratchethealth.admin

import grails.converters.JSON

class AccountsController extends BaseController {

    def beforeInterceptor = [action: this.&auth, except: ['goToForgetPasswordPage', 'forgotPassword', 'resetPassword', 'confirmResetPassword', 'goToActiveAccountPage']]

    static allowedMethods = [addAccount: ['POST']]
    def accountService
    def accountPasswordService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE
        def isAjax = params.ajax ?: false

        def accountList = accountService.getAccounts(request, page, pagesize)

        if (isAjax) {
            render accountList as JSON
        } else {
            render view: '/account/accounts', model: [accountList: accountList, pagesize: pagesize]
        }
    }

    def getAccounts() {
        def offset = params?.start
        def max = params?.length
        def resp = accountService.getAccounts(request, offset, max)
        render resp as JSON
    }

    def addAccount(Account account) {
        account = accountService.createAccount(request, account)

        if (account.id) {
            render account as JSON
        }
    }

    def deleteAccount() {
        Integer accountId = params.int("accountId")
        def resp = accountService.deleteAccount(request, accountId)
        def result = [resp: resp]
        render result as JSON
    }

    def goToForgetPasswordPage() {
        render view: '/forgotPassword/forgotPassword'
    }

    def forgotPassword() {
        accountPasswordService.askForResetPassword(request, params.email, "admin")
        render view: '/forgotPassword/resettingIntroduction', model: [email: params.email]
    }

    def resetPassword() {
        def code = params?.code
        def resp = accountPasswordService.validPasswordCode(request, code)
        if (resp) {
            render view: '/forgotPassword/resetPassword', model: [code: code]
        }
    }

    def confirmResetPassword() {
        def resp = accountPasswordService.resetPassword(request, params)
        if (resp) {
            render view: '/security/login'
        }
    }

    def goToActiveAccountPage() {
        render view: '/account/activeAccount'
    }

}
