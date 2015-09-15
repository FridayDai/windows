package com.ratchethealth.admin.modules

import com.ratchethealth.admin.modules.common.BaseModalModule


class AnnounceModalModule extends BaseModalModule {

    static content = {
        AnnouncementContent { $("#content") }
        BgcolorSelect { $("#colorHex") }

        createButton { $("button.create-btn") }

        AnnouncementErrorMessage { errorPlace(AnnouncementContent) }
    }
}
