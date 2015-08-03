require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var AccountsTable = require('../components/accounts/accountsTable'),
    AddAccountFormDialog = require('../components/accounts/addAccountFormDialog'),
    AccountTableRow = require('../components/accounts/accountTableRow'),
    EditAccountFormDialog = require('../components/accounts/editAccountFormDialog'),
    DeleteAccountFormDialog = require('../components/accounts/deleteAccountFormDialog');

AccountsTable.attachTo('#account-table');
AddAccountFormDialog.attachTo('#add-account-modal');
AccountTableRow.attachTo('#account-table tr');
EditAccountFormDialog.attachTo('#edit-account-modal');
DeleteAccountFormDialog.attachTo('#delete-account-modal');
