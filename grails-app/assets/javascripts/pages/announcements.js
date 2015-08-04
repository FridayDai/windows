'use strict';

require('jquery');
require('bootstrap');
require('../component/common/initSetup');


var AddAnnouncementFormDialog = require('../component/announcements/addAnnouncementFormDialog'),
    AnnouncementsTable = require('../component/announcements/announcementsTable'),
    DeleteAnnounceDialog = require('../component/announcements/deleteAnnouncementDialog');
    //AnnounceTableRow = require('../component/announcements/announceTableRow');

AnnouncementsTable.attachTo('#announcements-table');
AddAnnouncementFormDialog.attachTo('#announcement-add-modal');
DeleteAnnounceDialog.attachTo('#announcement-delete-modal');
//AnnounceTableRow.attachTo('#announcements-table tr');
