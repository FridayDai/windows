package pages.client

import geb.Page
import model.AccountModel
import modules.client.AccountModelModule

class AccountsPage extends Page {

    static url = '/accounts'

    static at = {title.startsWith("Accounts") }

    static content = {
        newAccountButton { $("#add-account") }
        accountModalDialog { module AccountModelModule }
        firstLine { $("tbody tr", 0) }
        logoutLink { $("#logout") }

    }

    def addNewAccount(AccountModel account){
        when:
        waitFor(30, 1) {
            newAccountButton.displayed
        }

        and:"Click add account button"
        newAccountButton.click()

        and: "Wait for account model come up"
        waitFor(30, 1) { accountModalDialog.displayed }

        and: "select doctor, type firstName lastName and email address, select provider and choose group"
        accountModalDialog.isDoctor.value(true)

        and:
        accountModalDialog.accountFirstName << account.firstName

        and:
        accountModalDialog.accountLastName << account.lastName

        and:
        accountModalDialog.email << account.email

        and:
        accountModalDialog.isProvider.value(true)

        and:
        accountModalDialog.npi << account.npi

        and:
        accountModalDialog.groupSelect.click()

        and:
        waitFor(20, 1) { accountModalDialog.groupFirstResult.displayed }

        and:
        accountModalDialog.groupFirstResult.click()

        and: "Click create button"
        accountModalDialog.createButton.click()

        then: "Account should created and displayed on page"
        waitFor(50, 1) {
            firstLine.find("td", 1).text().trim() == account.firstName + " " + account.lastName
            firstLine.find("td", 2).text() == account.email
        }

    }

    def goToAccountDetailPage(){
        and:
        waitFor(30, 1){
            firstLine.displayed
        }

        and: "Click first line of table"
        firstLine.click()
    }

}
