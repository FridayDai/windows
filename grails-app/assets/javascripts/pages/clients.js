'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');


var AddClientFormDialog = require('../component/clients/addClientFormDialog'),
    ClientsTool = require('../component/clients/clientsTool'),
    ClientsTable = require('../component/clients/clientsTable');

ClientsTable.attachTo('#client-table');
ClientsTool.attachTo('.clients-tool-bar');
AddClientFormDialog.attachTo('#client-modal');
