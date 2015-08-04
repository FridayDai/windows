package com.ratchethealth.admin.pages

import com.ratchethealth.admin.modules.common.SideNavModule
import geb.Page


class RatchetPage extends Page {

    static content = {
        navSide { module SideNavModule, $(".nav") }

    }

}
