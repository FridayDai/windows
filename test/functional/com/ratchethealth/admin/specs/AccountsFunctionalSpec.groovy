package com.ratchethealth.admin.specs

import com.ratchethealth.admin.pages.AccountsPage
import geb.spock.GebReportingSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class AccountsFunctionalSpec extends GebReportingSpec {
    @Shared
    AccountsPage accountsPage

    static REQUIRE_ERROR_MESSAGE = 'This field is required.'
    static EMAIL_ADDRESS_INPUT = 'john.xu@xplusz.com'
    static INITIAL_ACCOUNT_STATUS = 'Inactive'
    static INITIAL_ACCOUNT_ENABLED = 'true'
//    @Shared
//            newAccountDialog
//
//    def setupSpec() {
//        newAccountDialog = accountsPage.showNewAccountDialog()
//
//    }

    def "check all contents displayed"() {
        when:
        accountsPage = to accountsPage

        then:
        accountsPage.createAccountButton.displayed
        accountsPage.accountsTable.displayed
    }

    def "show new account dialog and check contents in it and check close button"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(1, 3) {
            newAccountDialog.displayed
        }

        newAccountDialog.title.text() == "New Account"

        newAccountDialog.emailInput.displayed
        newAccountDialog.emailInput.text() == ""

        newAccountDialog.closeButton.displayed
        newAccountDialog.cancelButton.displayed
        newAccountDialog.createButton.displayed

        when:
        newAccountDialog.closeButton.click()

        then:
        waitFor(1, 3) {
            !newAccountDialog.displayed
        }
    }

    def "click cancel button in new account dialog will close it"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(1, 3) {
            newAccountDialog.displayed
        }

        when:
        newAccountDialog.cancelButton.click()

        then:
        waitFor(1, 3) {
            !newAccountDialog.displayed
        }
    }

    def "check required validation for all fields"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(1, 3) {
            newAccountDialog.displayed
        }

        when:
        newAccountDialog.createButton.click()

        then:
        waitFor(1, 3) {
            newAccountDialog.displayed
        }

        newAccountDialog.emailInput.parents('.form-group').hasClass('has-error')
        newAccountDialog.emailInput.next().text() == REQUIRE_ERROR_MESSAGE
    }

    def "create new account successfully"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(1, 3) {
            newAccountDialog.displayed
        }

        when:
        newAccountDialog.emailInput.value(EMAIL_ADDRESS_INPUT)
        newAccountDialog.createButton.click()

        then:
        waitFor(1, 3) {
            !newAccountDialog.displayed
        }

        waitFor(1, 30) {
            $("tr", 1).find("td", 2).text() == EMAIL_ADDRESS_INPUT
            $("tr", 1).find("td", 3).text() == INITIAL_ACCOUNT_STATUS
            $("tr", 1).find("td", 4).text() == INITIAL_ACCOUNT_ENABLED
            AccountsPage.editAccountButton.displayed
            AccountsPage.deleteAccountButton.displayed
        }
    }


    def "show edit account dialog and check edit contents in it and check close button"() {
        when:
        def editAccountDialog = accountsPage.showEditAccountDialog()

        then:
        waitFor(1, 3) {
            editAccountDialog.displayed
        }

//        editAccountDialog.
    }
}
