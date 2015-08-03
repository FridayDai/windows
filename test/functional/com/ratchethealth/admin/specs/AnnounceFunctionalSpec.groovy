package com.ratchethealth.admin.specs

import com.ratchethealth.admin.pages.AnnouncePage
import com.ratchethealth.admin.pages.ClientsPage
import geb.spock.GebReportingSpec
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class AnnounceFunctionalSpec extends GebReportingSpec {
    @Shared
    ClientsPage clientsPage
    @Shared
    AnnouncePage announcePage

    def "direct to announce page"() {
        when:
        clientsPage = to ClientsPage
        clientsPage.navSide.announceIcon.click()

        then:
        at AnnouncePage

    }

    def "check announce table info"() {
        when:
        announcePage = to AnnouncePage

        then:
        waitFor {
            announcePage.announceTable.tbody.displayed
        }
    }

    def "open and close announcementModal successfully"() {
        given:
        def announceModal = announcePage.newAnnounceModal

        when:
        announcePage.addAnnounceBtn.click()

        then:
        waitFor {
            announceModal.displayed
        }

        and:
        announceModal.title == "New Announcement"

        when:
        announceModal.closeButton.click()

        then:
        !announceModal.displayed

        when:
        announcePage.addAnnounceBtn.click()
        waitFor { announceModal.displayed }
        announceModal.cancelButton.click()

        then:
        !announceModal.displayed
    }

    def "add announcement successfully"() {
        given:
        def announceModal = announcePage.newAnnounceModal

        when:
        announcePage.addAnnounceBtn.click()
        waitFor { announceModal.displayed }
        announceModal.AnnouncementContent << "TEST CONTENT FOR ANNOUNCEMENT"
        announceModal.createButton.click()

        then:
        waitFor { !announceModal.displayed }
    }
}
