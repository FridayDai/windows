package com.ratchethealth.admin.modules.common

import geb.Module

class SlideNavModel extends Module{
    static content = {
        clientsIcon { $(".icon-client") }
        accountsIcon { $(".icon-account") }
        announceIcon { $(".icon-announcements") }
    }
}
