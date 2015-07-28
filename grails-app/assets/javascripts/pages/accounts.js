'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var AccountsTable = require('../component/accounts/accountsTable'),
    AddAccountFormDialog = require('../component/accounts/addAccountFormDialog'),
    AccountTableRow = require('../component/accounts/accountTableRow'),
    EditAccountFormDialog = require('../component/accounts/editAccountFormDialog'),
    DeleteAccountFormDialog = require('../component/accounts/deleteAccountFormDialog');

AccountsTable.attachTo('#account-table');
AddAccountFormDialog.attachTo('#add-account-modal');
AccountTableRow.attachTo('#account-table tr');
EditAccountFormDialog.attachTo('#edit-account-modal');
DeleteAccountFormDialog.attachTo('#delete-account-modal');
