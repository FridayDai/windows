package com.ratchethealth.admin.modules.common

import geb.Module

class TableModule extends Module {
    static content = {
        tbody { $("tbody")}
        paginate { $(".dataTables_paginate") }
        previousBtn { paginate.find(".previous") }
        nextBtn { paginate.find(".next") }
    }
}
