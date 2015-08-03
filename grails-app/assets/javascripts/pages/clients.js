require('jquery');
require('bootstrap');
require('../components/common/initSetup');


var AddClientFormDialog = require('../components/clients/addClientFormDialog'),
    ClientsTool = require('../components/clients/clientsTool'),
    ClientsTable = require('../components/clients/clientsTable');

ClientsTable.attachTo('#client-table');
ClientsTool.attachTo('.clients-tool-bar');
AddClientFormDialog.attachTo('#client-modal');
