package specs

import geb.error.NoNewWindowException
import geb.spock.GebReportingSpec
import geb.waiting.UnknownWaitForEvaluationResult
import geb.waiting.WaitTimeoutException

class RatchetSmokeFunctionalSpec extends GebReportingSpec {
	static GMAIL_ACCOUNT = "ratchet.testing@gmail.com"
	static GMAIL_PASSWORD = "K6)VkqMUDy(mRseYHZ>v23zGt"

	def getAdminUrl() {
		def env = System.getProperty("env")
		"http://admin.${env}.ratchethealth.com"
	}

    def getClientUrl() {
        def env = System.getProperty("env")
        "http://client.${env}.ratchethealth.com"
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

		waitFor(300, 5) { (availableWindows - originalWindows).size() == 1 }
		def newWindows = (availableWindows - originalWindows) as List

		if (newWindows.size() != 1) {
			def message = newWindows ? 'There has been more than one window opened' : 'No new window has been opened'
			throw new NoNewWindowException(message)
		}
		switchToWindow(newWindows.first())
	}

	Date calculateTimeoutFromNow(Double timeout) {
		calculateTimeoutFrom(new Date(), timeout)
	}

	Date calculateTimeoutFrom(Date start, Double timeout) {
		def calendar = Calendar.instance
		calendar.time = start
		calendar.add(Calendar.MILLISECOND, Math.ceil(timeout * 1000) as int)
		calendar.time
	}

	void sleepForRetryInterval(Double interval) {
		Thread.sleep((interval * 1000) as long)
	}

	public <T> T repeatActionWaitFor(Double timeout, Double interval, Closure action, Closure<T> block) {
		def stopAt = calculateTimeoutFromNow(timeout)
		def pass
		def thrown = null

		try {
			pass = block()
		} catch (Throwable e) {
			pass = new UnknownWaitForEvaluationResult(e)
			thrown = e
		}

		def timedOut = new Date() > stopAt
		while (!pass && !timedOut) {
			action.call()
			sleepForRetryInterval(interval)
			try {
				pass = block()
				thrown = null
			} catch (Throwable e) {
				pass = new UnknownWaitForEvaluationResult(e)
				thrown = e
			} finally {
				timedOut = new Date() > stopAt
			}
		}

		if (!pass && timedOut) {
			throw new WaitTimeoutException(this, thrown, pass)
		}

		pass as T
	}

	def gmail_mainContent() {
		$('div.BltHke.nH.oy8Mbf', role: 'main', 0)
	}

	def gmail_mailTable() {
		gmail_mainContent().find('table.zt', 0)
	}

	def gmail_mailContent() {
		$('div.nH', role: 'main', 0).find('table', role: 'presentation', 0)
	}

	def gmail_toolbar() {
		$('div.Cq.aqL').findAll { it.'@gh' == 'mtb' }
	}

	def gmail_refreshButton() {
		gmail_toolbar().find('div.T-I.J-J5-Ji.nu.T-I-ax7.L3', role: 'button', 0)
	}

	def gmail_chooseButton() {
		gmail_toolbar().find('div.T-I.J-J5-Ji.T-Pm.T-I-ax7.L3.J-JN-M-I.G-as3', role: 'button', 0)
	}

	def gmail_chooseMenu() {
		gmail_toolbar().find('div.J-M.jQjAxd', role: 'menu', 0)
	}

	def gmail_allMenuItem() {
		gmail_chooseMenu().find('div.J-N .J-N-Jz', role: 'menuitem', text: contains('All'), 0)
	}

	def gmail_archiveButton() {
		gmail_toolbar().find('div.T-I.J-J5-Ji.lR.T-I-ax7', role: 'button', 0)
	}

	def gmail_inboxButton() {
		$('a.J-Ke.n0', href: contains("#inbox"), 0)
	}
}
