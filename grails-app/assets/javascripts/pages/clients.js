'use strict';

require('jquery');
require('bootstrap');
require('../component/common/defaults');


var AddClientFormDialog = require('../component/clients/addClientFormDialog'),
    ClientsTable = require('../component/clients/clientsTable');

ClientsTable.attachTo('#client-table');
AddClientFormDialog.attachTo('#client-modal');
