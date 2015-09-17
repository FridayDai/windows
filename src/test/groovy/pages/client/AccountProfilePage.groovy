package pages.client

import geb.Page

class AccountProfilePage extends Page {
    static at = { $(".profile-icon") }

    static content = {
        accountFirstName { $("#accountFirstName") }
        accountLastName { $("#accountLastName") }
        accountEmail { $("td .account-email") }
        accountStatus { $(".account-table tr", 2).find("td", 1) }
        provider { $("#accountRole") }
        permission { $("#isAccountManage") }
        groups { $("#groups") }

        logoutLink { $("#logout") }
    }

}
