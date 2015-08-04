package com.ratchethealth.admin.pages

import com.ratchethealth.admin.modules.common.SlideNavModel
import geb.Page


class RatchetPage extends Page {
    static content = {
        slideNavModel { module SlideNavModel, $(".nav") }
    }
}
