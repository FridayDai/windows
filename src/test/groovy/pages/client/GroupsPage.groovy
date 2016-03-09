package pages.client

import geb.Page
import groovy.json.JsonBuilder
import modules.client.GroupModalModule
import modules.client.GroupsTableModule


class GroupsPage extends Page {
    static url = '/groups'

    static at = { title.startsWith("Groups") }

    static content = {
        newGroupButton { $("#add-group") }
        groupModal { $("#group-form").module(GroupModalModule)}
        accountTab { $(".icon-account") }
        groupsTable { $('#client-table').module(GroupsTableModule) }
    }

    def addGroup(identify) {
        def groupName = "group${identify}"

        when: 'Wait for new group button display'
        waitFor(30, 1) {
            newGroupButton.displayed
        }

        and:
        newGroupButton.click()

        and: "Type in group name"
        waitFor(5, 1) { groupModal.displayed }
        groupModal.groupName << groupName

        and: "Click treatments input"
        groupModal.treatmentsSelect.input.click()

        waitFor(5, 1) {
            groupModal.treatmentsSelect.results.size() == 2
        }

        and: 'Pick the first result'
        groupModal.treatmentsSelect.results[0].click()

        and: "Click treatments input again"
        groupModal.treatmentsSelect.input.click()

        waitFor(5, 1) {
            groupModal.treatmentsSelect.results.size() == 1
        }

        and: 'Pick the first result'
        groupModal.treatmentsSelect.results[0].click()

        and: "Click create button"
        groupModal.createButton.click()

        and: "Wait for group modal disappear"
        waitFor(5, 1) { !groupModal.displayed }

        then: "Check add group successfully"
        waitFor(20, 1) {
            groupsTable.groupItems[0].groupName == groupName
            groupsTable.groupItems[0].treatments == "treatment${identify}FIRST, treatment${identify}SECOND"
        }
    }
}
