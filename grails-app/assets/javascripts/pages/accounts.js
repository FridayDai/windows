'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var AccountsTable = require('../component/accounts/accountsTable'),
    AddAccountFormDialog = require('../component/accounts/addAccountFormDialog'),
    EditAccountFormDialog = require('../component/accounts/editAccountFormDialog'),
    DeleteAccountFormDialog = require('../component/accounts/deleteAccountFormDialog');

AccountsTable.attachTo('#account-table');
AddAccountFormDialog.attachTo('#add-account-modal');
EditAccountFormDialog.attachTo('#edit-account-modal');
DeleteAccountFormDialog.attachTo('#delete-account-modal');
