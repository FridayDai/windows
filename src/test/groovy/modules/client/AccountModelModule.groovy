package modules.client

import geb.Module

class AccountModelModule extends Module{
    static content = {
        isDoctor { $("#doctor") }
        accountFirstName { $("#firstName") }
        accountLastName { $("#lastName") }
        email { $("#email") }
        isProvider { $("#provider") }
        groupSelect { $(".select2-choices") }
        groupResults { $(".select2-results") }
        groupFirstResult { groupResults.find("li", 0).children() }
        createButton { $(".accounts-form").next().find("button") }
    }

}
