package com.ratchethealth.admin.specs

import com.ratchethealth.admin.pages.AccountsPage
import com.ratchethealth.admin.pages.ClientsPage
import com.ratchethealth.admin.pages.LoginPage
import geb.spock.GebReportingSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class AccountsFunctionalSpec extends GebReportingSpec {
    @Shared
    AccountsPage accountsPage
//    @Shared
//    ClientsPage clientsPage

    static REQUIRE_ERROR_MESSAGE = 'This field is required.'
    static EMAIL_ADDRESS_INPUT = 'ratchet.testing+8@gmail.com'
    static INITIAL_ACCOUNT_STATUS = 'Inactive'
    static INITIAL_ACCOUNT_ENABLED = 'true'
    static MODIFIED_ACCOUNT_ENABLED = 'false'
    static ISENABLDE_VALUE = 'isEnabled'

    @Shared
            newAccountDialog

//    def getNewAccountDialog() {
//        newAccountDialog = accountsPage.showNewAccountDialog()
//
//    }

    def "login successfully"() {
        when:
        def loginPage = to LoginPage

        report "Login page"

        loginPage.login()

        then:
        at ClientsPage
    }

    def "direct to accounts page and check all contents displayed"() {
        when:
        accountsPage = to AccountsPage

        then:

        waitFor(3, 1) {
            accountsPage.createAccountButton.displayed
            accountsPage.accountsTable.displayed
        }
    }

    def "show new account dialog and check contents in it and check close button"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()
//        waitFor(1, 3) {
//            newAccountDialog.displayed
//        }
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
        waitFor(3, 1) {
            !newAccountDialog.displayed
        }
    }

    def "click cancel button in new account dialog will close it"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(3, 1) {
            newAccountDialog.displayed
        }

        when:
        newAccountDialog.cancelButton.click()

        then:
        waitFor(3, 1) {
            !newAccountDialog.displayed
        }
    }

    def "check required validation for all fields"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(3, 1) {
            newAccountDialog.displayed
        }

        when:
        newAccountDialog.createButton.click()

        then:
        waitFor(3, 1) {
            newAccountDialog.displayed
        }

        newAccountDialog.emailInput.parents('.form-group').hasClass('has-error')
        newAccountDialog.emailInput.next().text() == REQUIRE_ERROR_MESSAGE

        when:
        newAccountDialog.cancelButton.click()

        then:
        waitFor(3, 1) {
            !newAccountDialog.displayed
        }
    }

    def "create new account successfully"() {
        when:
        def newAccountDialog = accountsPage.showNewAccountDialog()

        then:
        waitFor(3, 1) {
            newAccountDialog.displayed
        }

        when:
        Thread.sleep(1000)
        newAccountDialog.emailInput << EMAIL_ADDRESS_INPUT
        newAccountDialog.createButton.click()

//        waitFor(30, 1) {
//            !newAccountDialog.displayed
//        }
        newAccountDialog.cancelButton.click()

        then:
        waitFor(30, 1) {
            accountsPage.accountFirstLine.find("td", 1).text() == EMAIL_ADDRESS_INPUT
            accountsPage.accountFirstLine.find("td", 2).text() == INITIAL_ACCOUNT_STATUS
            accountsPage.accountFirstLine.find("td", 3).text() == INITIAL_ACCOUNT_ENABLED
//            accountsPage.editAccountButton.displayed
//            accountsPage.deleteAccountButton.displayed
        }
    }


    def "show edit account dialog and check edit contents in it and check close button"() {
        when:
        def editAccountDialog = accountsPage.showEditAccountDialog()

        then:
        waitFor(3, 1) {
            editAccountDialog.displayed
        }

        editAccountDialog.title.text() == 'Edit Account'

//        editAccountDialog.accountEmail.value() == EMAIL_ADDRESS_INPUT
        editAccountDialog.accountEnabled.value() == ISENABLDE_VALUE

        editAccountDialog.closeButton.displayed
        editAccountDialog.cancelButton.displayed
        editAccountDialog.updateButton.displayed

        when:
        editAccountDialog.closeButton.click()

        then:
        waitFor(3, 1) {
            !editAccountDialog.displayed
        }
    }

    def "click cancel button in edit account dialog will close it"() {
        when:
        def editAccountDialog = accountsPage.showEditAccountDialog()

        then:
        waitFor(3, 1) {
            editAccountDialog.displayed
        }

        when:
        editAccountDialog.closeButton.click()

        then:
        waitFor(1, 3) {
            !editAccountDialog.displayed
        }
    }

//    def "edit account successfully"() {
//        when:
//        def editAccountDialog = accountsPage.showEditAccountDialog()
//
//        then:
//        waitFor(3, 1) {
//            editAccountDialog.displayed
//        }
//
//        when:
//        editAccountDialog.accountEnabled.click()
//        editAccountDialog.updateButton.click()
//
//
//        then:
//        waitFor(3, 1) {
//            !editAccountDialog.displayed
//        }
//
//        accountsPage.accountFirstLine.find("td", 4).text() == MODIFIED_ACCOUNT_ENABLED
//    }

    def "show delete account dialog and check delete contents in it and check close button"() {
        when:
        def deleteAccountDialog = accountsPage.showDeleteAccountDialog()

        then:
        waitFor(3, 1) {
            deleteAccountDialog.displayed
        }

        deleteAccountDialog.title.text() == 'Delete Account'

        deleteAccountDialog.accountPromptMsg.text() == 'Are you sure to delete this account?'

        deleteAccountDialog.closeButton.displayed
        deleteAccountDialog.cancelButton.displayed
        deleteAccountDialog.deleteButton.displayed

        when:
        deleteAccountDialog.closeButton.click()

        then:
        waitFor(3, 1) {
            !deleteAccountDialog.displayed
        }
    }
}
