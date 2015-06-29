package specs

import geb.error.NoNewWindowException
import geb.spock.GebReportingSpec

class RatchetSmokeFunctionalSpec extends GebReportingSpec {
    def getAdminUrl() {
        def env = System.getProperty("env")
        "http://admin.${env}.ratchethealth.com"
    }

    def getGmailUrl() {
        "https://www.gmail.com"
    }

    def getClientDomain() {
        def env = System.getProperty("env")
        "client.${env}.ratchethealth.com"
    }

    def switchToWindow(String window) {
        driver.switchTo().window(window)
    }

    def switchToNewWindow(Closure windowOpeningBlock) {
        def originalWindows = availableWindows
        windowOpeningBlock.call()

        waitFor { (availableWindows - originalWindows).size() == 1 }
        def newWindows = (availableWindows - originalWindows) as List

        if (newWindows.size() != 1) {
            def message = newWindows ? 'There has been more than one window opened' : 'No new window has been opened'
            throw new NoNewWindowException(message)
        }
        switchToWindow(newWindows.first())
    }
}
