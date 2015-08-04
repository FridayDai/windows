require('jquery');
require('bootstrap');
require('../components/common/initSetup');


var AddAnnouncementFormDialog = require('../components/announcements/addAnnouncementFormDialog'),
    AnnouncementsTable = require('../components/announcements/announcementsTable'),
    DeleteAnnounceDialog = require('../components/announcements/deleteAnnouncementDialog');

AnnouncementsTable.attachTo('#announcements-table');
AddAnnouncementFormDialog.attachTo('#announcement-add-modal');
DeleteAnnounceDialog.attachTo('#announcement-delete-modal');
