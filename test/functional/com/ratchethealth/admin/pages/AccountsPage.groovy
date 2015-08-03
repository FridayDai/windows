package com.ratchethealth.admin.pages

import com.ratchethealth.admin.modules.accounts.AccountDeleteModelModule
import com.ratchethealth.admin.modules.accounts.AccountEditModelModule
import com.ratchethealth.admin.modules.accounts.AccountsModelModule

class AccountsPage extends RatchetPage {
    static url = "/accounts"
    static at = { $("#add-account").displayed }

    static content = {
        createAccountButton { $("#add-account") }
        accountsTable { $("#account-table") }
        editAccountButton { $("a.btn-edit") }
        deleteAccountButton { $("a.btn-remove") }

        newAccountModel { module AccountsModelModule, $("#add-account-modal") }
        editAccountModel { module AccountEditModelModule, $("#edit-account-modal") }
        deleteAccountModel { module AccountDeleteModelModule, $("#delete-account-modal") }
    }

    AccountsModelModule getNewAccountDialog() {
        newAccountModel
    }

    AccountsModelModule showNewAccountDialog() {
        createAccountButton.click()
        newAccountModel
    }

    AccountEditModelModule getEditAccountDialog() {
        editAccountModel
    }

    AccountEditModelModule showEditAccountDialog() {
        editAccountButton.click()
        editAccountModel
    }

    AccountDeleteModelModule getDeleteAccountDialog() {
        deleteAccountModel
    }

    AccountDeleteModelModule showDeleteAccountDialog() {
        deleteAccountButton.click()
        deleteAccountModel
    }
}




