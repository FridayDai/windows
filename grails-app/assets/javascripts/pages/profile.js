require('jquery');
require('bootstrap');
require('../components/common/initSetup');


var ChangePasswordFormDialog = require('../components/profile/changePasswordFormDialog'),
    DebugScheduleFormDialog = require('../components/profile/debugScheduleFormDialog'),
    DebugSchedulePanel = require('../components/profile/debugSchedulePanel');

ChangePasswordFormDialog.attachTo('#change-password-modal');
DebugScheduleFormDialog.attachTo('#change-time-modal');
DebugSchedulePanel.attachTo('#debug-schedule');
