package pages.client

import geb.Browser
import geb.Page
import model.GroupModel
import modules.client.GroupModalModule
import modules.client.GroupsTableModule


class GroupsPage extends Page {
    static url = '/groups'

    static at = { title.startsWith("Groups") }

    static content = {
        newGroupButton { $("#add-group") }
        groupModal { $("#group-form").module(GroupModalModule)}
        accountTab { $(".icon-account") }
        groupsTable { $('#groupsTable').module(GroupsTableModule) }
        treatmentChoose { $(".select2-results").find("li")}
        createButton { $(".ui-dialog-buttonset").find("span") }
    }

    def addGroup(GroupModel group) {
        when:
        Thread.sleep(2000)
        waitFor(30, 1) {
            newGroupButton.displayed
        }

        and:
        newGroupButton.click()

        and:
        waitFor(30, 1) { groupModal.displayed }
        groupModal.groupName << group.groupName

        and: "Click treatments input"
        groupModal.treatmentsSelect.input.click()

//        Thread.sleep(1000)
//        waitFor(32, 1) {
//            //groupModal.treatmentsSelect.displayed
//            groupModal.treatmentsSelect.results.size() == 1
//        }

        and: 'Pick the first result'
        Thread.sleep(2000)
        treatmentChoose[0].click()

//        and: "Click treatments input again"
//        Thread.sleep(2000)
//        groupModal.treatmentsSelect.input.click()
//
//        and: 'Pick the second result'
//        Thread.sleep(3000)
//        treatmentChoose[1].click()

        and: "Click create button"
        Thread.sleep(1000)
        createButton.click()

        and: "Wait for group modal disappear"
        waitFor(30, 1) { !groupModal.displayed }
    }

    def goToAccountsPage(){
        and:
        waitFor(30, 1){
            accountTab.displayed
        }

        and:
        accountTab.click()
    }
}
