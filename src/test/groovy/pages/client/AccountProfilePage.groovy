package pages.client

import geb.Page
import model.AccountModel
import model.ClientModel
import model.StaffModel

class AccountProfilePage extends Page {
    //static at = { $(".profile-icon") }
    static at = { title.endsWith("Ratchet Health") }

    static content = {
        accountFirstName { $("#accountFirstName") }
        accountLastName { $("#accountLastName") }
        accountEmail { $("td .account-email") }
        accountStatus { $(".account-table tr", 2).find("td", 1) }
        provider { $("#accountRole") }
        permission { $("#isAccountManage") }
        groups { $("#groups") }
        accountTable { $(".account-table") }

        logoutLink { $("#logout") }
    }


    def checkProfileDetail(StaffModel agent){
        when:
        waitFor(25,1){
            accountTable.displayed
        }
        then: "Check profile details"
        waitFor(30,1){
            accountFirstName.text() == agent.firstName
            accountLastName.text() == agent.lastName
            accountEmail.text() == agent.email
            accountStatus.text().trim() == "VERIFIED"
            provider.text() == "No"
        }
    }

    def logout(){
        when:"Direct to logout"
        logoutLink.click()
        
        then:"Redirect to login page"
        waitFor(20, 1) {
            browser.at LoginPage
        }
    }
}
