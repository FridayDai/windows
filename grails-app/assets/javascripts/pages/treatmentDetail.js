require('jquery');
require('bootstrap');
require('../components/common/initSetup');

var TreatmentInfoPanel = require('../components/treatmentDetail/treatmentInfoPanel'),
    EditTreatmentFormDialog = require('../components/treatmentDetail/editTreatmentFormDialog'),
    CloseTreatmentDialog = require('../components/treatmentDetail/closeTreatmentDialog'),
    ToolPoolPanelTool = require('../components/treatmentDetail/toolPoolPanelTool'),
    DefinedToolFormDialog = require('../components/treatmentDetail/definedToolFormDialog'),
    DeleteToolDialog = require('../components/treatmentDetail/deleteToolDialog'),
    ToolsTable = require('../components/treatmentDetail/toolsTable'),
    TaskPanelTool = require('../components/treatmentDetail/taskPanelTool'),
    TaskFormDialog = require('../components/treatmentDetail/taskFormDialog'),
    DeleteTaskDialog = require('../components/treatmentDetail/deleteTaskItemDialog'),
    TasksTable = require('../components/treatmentDetail/tasksTable');


TreatmentInfoPanel.attachTo('#treatment-info-panel');
EditTreatmentFormDialog.attachTo('#edit-treatment-modal');
CloseTreatmentDialog.attachTo('#close-treatment-modal');
ToolPoolPanelTool.attachTo('#tool-pool-list-panel .tool-bar');
DeleteToolDialog.attachTo('#delete-tool-modal');
DefinedToolFormDialog.attachTo('#add-defined-tool-modal');
ToolsTable.attachTo('#tool-pool-table');
TaskPanelTool.attachTo('#task-list-panel .tool-bar');
TaskFormDialog.attachTo('#add-item-modal');
DeleteTaskDialog.attachTo('#delete-item-modal');
TasksTable.attachTo('#task-table');
