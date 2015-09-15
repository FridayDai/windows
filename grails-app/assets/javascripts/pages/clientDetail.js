require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var AddTreatmentFormDialog = require('../components/clientDetail/addTreatmentFormDialog'),
    AgentFormDialog = require('../components/clientDetail/agentFormDialog'),
    AgentInfoPanel = require('../components/clientDetail/agentInfoPanel'),
    ContentStatusInfoPanel = require('../components/clientDetail/contentStatusInfoPanel'),
    ClientInfoPanel = require('../components/clientDetail/clientInfoPanel'),
    DeleteAgentDialog = require('../components/clientDetail/deleteAgentDialog'),
    EditClientFormDialog = require('../components/clientDetail/editClientFormDialog'),
    TreatmentsTable = require('../components/clientDetail/treatmentsTable');


AddTreatmentFormDialog.attachTo('#treatment-modal');
AgentFormDialog.attachTo('#agent-modal');
AgentInfoPanel.attachTo('#client-info-panel .agent');
ContentStatusInfoPanel.attachTo('#client-info-panel .content-status-info');
ClientInfoPanel.attachTo('#client-info-panel .client-profile');
DeleteAgentDialog.attachTo('#agent-delete-modal');
EditClientFormDialog.attachTo('#client-modal');
TreatmentsTable.attachTo('#treatment-table');
