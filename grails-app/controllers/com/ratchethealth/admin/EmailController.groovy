package com.ratchethealth.admin

class EmailController extends BaseController {

    def mailService

    def index(String email, String firstName) {
        mailService.sendMail {
            async true
            to email
            subject grailsApplication.config.yyoga.email.resetPasswordSuccess.emailSubject
            html g.render(template: "/email/resetPasswordSuccessEmailTemplate", model: [firstName: firstName])
        }
    }
}
