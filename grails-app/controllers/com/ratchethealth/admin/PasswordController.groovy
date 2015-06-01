package com.ratchethealth.admin

class PasswordController {

    def accountPasswordService

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
}
