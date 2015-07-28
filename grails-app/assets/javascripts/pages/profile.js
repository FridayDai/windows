'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');


var ChangePasswordFormDialog = require('../component/profile/changePasswordFormDialog'),
    DebugScheduleFormDialog = require('../component/profile/debugScheduleFormDialog'),
    DebugSchedulePanel = require('../component/profile/debugSchedulePanel');

ChangePasswordFormDialog.attachTo('#change-password-modal');
DebugScheduleFormDialog.attachTo('#change-time-modal');
DebugSchedulePanel.attachTo('#debug-schedule');
