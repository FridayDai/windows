package com.ratchethealth.admin.modules

import com.ratchethealth.admin.modules.common.BaseModelModule


class AnnounceModalModule extends BaseModelModule {

    static content = {
        AnnouncementContent { $("#content") }
        BgcolorSelect { $("#colorHex") }

        createButton { $("button.create-btn") }
    }
}
