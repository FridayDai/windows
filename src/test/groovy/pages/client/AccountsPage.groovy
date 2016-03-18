package pages.client

import geb.Page
import model.AccountModel
import modules.client.AccountModelModule

class AccountsPage extends Page {

    static url = '/accounts'

    static at = {title.startsWith("Accounts") }

    static content = {
        newAccountButton { $("#add-account") }
        accountModelModule { module AccountModelModule }
//        accountModelModule { module AccountModelModule, $(".ui-dialog") }
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
        waitFor(30, 1) { accountModelModule.displayed }

        and: "select doctor, type firstName lastName and email address, select provider and choose group"
        accountModelModule.isDoctor.value(true)
        accountModelModule.accountFirstName << account.firstName
        accountModelModule.accountLastName << account.lastName
        accountModelModule.email << account.email
        accountModelModule.isProvider.value(true)
        Thread.sleep(1000 as long)
        accountModelModule.npi << account.npi
        accountModelModule.groupSelect.click()

        waitFor(20, 1) { accountModelModule.groupFirstResult.displayed }

        and:
        accountModelModule.groupFirstResult.click()

        and: "Click create button"
        accountModelModule.createButton.click()

        then: "Account should created and displayed on page"
        waitFor(50, 1) {
            firstLine.find("td", 1).text().trim() == account.firstName + " " + account.lastName
            firstLine.find("td", 2).text() == account.email
        }

    }
    def goToAccountDetailPage(){
        when: "Click first line of table"
        firstLine.click()

        then: "Direct to account detail page"
        waitFor(30, 1) {
            browser.at AccountDetailPage
        }

    }

}
