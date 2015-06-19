'use strict';

require('jquery');
require('bootstrap');
var AddTreatmentFormDialog = require('../component/clientDetail/addTreatmentFormDialog'),
    AgentFormDialog = require('../component/clientDetail/agentFormDialog'),
    AgentInfoPanel = require('../component/clientDetail/agentInfoPanel'),
    ContentStatusInfoPanel = require('../component/clientDetail/contentStatusInfoPanel'),
    ClientInfoPanel = require('../component/clientDetail/clientInfoPanel'),
    DeleteAgentFormDialog = require('../component/clientDetail/deleteAgentFormDialog'),
    EditClientFormDialog = require('../component/clientDetail/editClientFormDialog'),
    TreatmentsTable = require('../component/clientDetail/treatmentsTable');


require('../component/common/defaults');

AddTreatmentFormDialog.attachTo('#treatment-modal');
AgentFormDialog.attachTo('#agent-modal');
AgentInfoPanel.attachTo('#client-info-panel .agent');
ContentStatusInfoPanel.attachTo('#client-info-panel .content-status-info');
ClientInfoPanel.attachTo('#client-info-panel .client-profile');
DeleteAgentFormDialog.attachTo('#agent-delete-modal');
EditClientFormDialog.attachTo('#client-modal');
TreatmentsTable.attachTo('#treatment-table');