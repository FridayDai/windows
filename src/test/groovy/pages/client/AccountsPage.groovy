package pages.client

import geb.Page
import modules.client.AccountModelModule

class AccountsPage extends Page {

    static url = '/accounts'

    static at = {title.startsWith("Accounts") }

    static content = {
        newAccountButton { $("#add-account") }
        accountModelModule { module AccountModelModule }
//        accountModelModule { module AccountModelModule, $(".ui-dialog") }
        logoutLink { $(".log-out") }

    }
}
