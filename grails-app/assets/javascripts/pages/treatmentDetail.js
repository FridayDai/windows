require('jquery');
require('bootstrap');
require('../component/common/initSetup');

var TreatmentInfoPanel = require('../component/treatmentDetail/treatmentInfoPanel'),
    EditTreatmentFormDialog = require('../component/treatmentDetail/editTreatmentFormDialog'),
    CloseTreatmentDialog = require('../component/treatmentDetail/closeTreatmentDialog'),
    ToolPoolPanelTool = require('../component/treatmentDetail/toolPoolPanelTool'),
    DefinedToolFormDialog = require('../component/treatmentDetail/definedToolFormDialog'),
    DeleteToolDialog = require('../component/treatmentDetail/deleteToolDialog'),
    ToolsTable = require('../component/treatmentDetail/toolsTable'),
    TaskPanelTool = require('../component/treatmentDetail/taskPanelTool'),
    TaskFormDialog = require('../component/treatmentDetail/taskFormDialog'),
    DeleteTaskDialog = require('../component/treatmentDetail/deleteTaskItemDialog'),
    TasksTable = require('../component/treatmentDetail/tasksTable');


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
