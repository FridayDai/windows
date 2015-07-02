package pages.client

import geb.Page

class AccountDetailPage extends Page {
    static at = { $(".account") }

    static content = {
        accountFirstName { $("#accountFirstName") }
        accountLastName { $("#accountLastName") }
        accountEmail { $("td .account-email") }
        accountStatus { $("#span-invited") }
        provider { $("#accountRole") }
        groups { $("#groups") }

        profileButton { $('a', href: contains("/profile")) }
    }

}
