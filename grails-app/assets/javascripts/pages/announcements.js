'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');


var AddAnnouncementFormDialog = require('../component/announcements/addAnnouncementFormDialog'),
    AnnouncementsTable = require('../component/announcements/announcementsTable');

AnnouncementsTable.attachTo('#announcements-table');
AddAnnouncementFormDialog.attachTo('#announcements-add-modal');
