package pages.client

import geb.Page
import modules.client.GroupModelModule


class GroupsPage extends Page {
    static url = '/groups'

    static at = { title.startsWith("Groups") }

    static content = {
        newGroupButton { $("#add-group") }
        groupModelModule { module GroupModelModule, $(".ui-dialog").has("#group-form") }
        accountTab { $(".icon-account") }
    }
}
