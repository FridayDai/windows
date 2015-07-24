'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var AddTreatmentFormDialog = require('../component/clientDetail/addTreatmentFormDialog'),
    AgentFormDialog = require('../component/clientDetail/agentFormDialog'),
    AgentInfoPanel = require('../component/clientDetail/agentInfoPanel'),
    ContentStatusInfoPanel = require('../component/clientDetail/contentStatusInfoPanel'),
    ClientInfoPanel = require('../component/clientDetail/clientInfoPanel'),
    DeleteAgentDialog = require('../component/clientDetail/deleteAgentDialog'),
    EditClientFormDialog = require('../component/clientDetail/editClientFormDialog'),
    TreatmentsTable = require('../component/clientDetail/treatmentsTable');


AddTreatmentFormDialog.attachTo('#treatment-modal');
AgentFormDialog.attachTo('#agent-modal');
AgentInfoPanel.attachTo('#client-info-panel .agent');
ContentStatusInfoPanel.attachTo('#client-info-panel .content-status-info');
ClientInfoPanel.attachTo('#client-info-panel .client-profile');
DeleteAgentDialog.attachTo('#agent-delete-modal');
EditClientFormDialog.attachTo('#client-modal');
TreatmentsTable.attachTo('#treatment-table');
