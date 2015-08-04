package com.ratchethealth.admin.pages

import com.ratchethealth.admin.modules.AnnounceModalModule
import com.ratchethealth.admin.modules.common.TableModule


class AnnouncePage extends RatchetPage {

    static url = "/announcements"
    static at = { $("#add-announcement-btn").displayed }
    static content = {
        announceTable { module TableModule, $("#announcements-table") }

        addAnnounceBtn { $('#add-announcement-btn') }
        newAnnounceModal { module AnnounceModalModule, $("#announcement-add-modal") }
    }

}
