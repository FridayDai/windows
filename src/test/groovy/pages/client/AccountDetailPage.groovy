package pages.client

import geb.Page
import model.AccountModel
import model.GroupModel

class AccountDetailPage extends Page {
//    static at = { $(".account") }
    static at = {title.startsWith("Dr")}

    static content = {
        accountFirstName { $("#accountFirstName") }
        accountLastName { $("#accountLastName") }
        accountEmail { $("td .account-email") }
        accountStatus { $("#span-invited") }
        provider { $("#accountRole") }
        groups { $("#groups") }
        accountTable {$(".account-table")}

        profileButton { $('a', href: contains("/profile")) }
    }

    def checkDetail(AccountModel account,GroupModel group){
        when:
        waitFor(25,1) {
            accountTable.displayed
        }

        then: "Check the details"
        waitFor(30,1){
            accountFirstName.text() == account.firstName
            accountLastName.text() == account.lastName
            accountEmail.text() == account.email
            accountStatus.text().trim() == "UNVERIFIED"
            provider.text() == "Yes"
            groups.text().trim() == group.groupName
        }
    }

    def goToProfilePage(){
        when: "Click to account profile page"
        profileButton.click()

        then: "Direct to profile page"
        waitFor(16, 1) {
            browser.at AccountProfilePage
        }
    }
}
