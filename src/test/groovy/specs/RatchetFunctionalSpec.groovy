package specs

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.model.Message
import geb.error.NoNewWindowException
import geb.spock.GebReportingSpec
import geb.waiting.UnknownWaitForEvaluationResult
import geb.waiting.WaitTimeoutException
import utils.GmailAPI

import java.util.regex.Matcher
import java.util.regex.Pattern

class RatchetFunctionalSpec extends GebReportingSpec {
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

	def refresh() {
		driver.navigate().refresh()
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
		$('div.Cq.aqL').findAll { it.displayed }
	}

	def gmail_refreshButton() {
		$('div.T-I.J-J5-Ji.nu.T-I-ax7.L3', role: 'button').findAll { it.displayed }
	}

	def gmail_chooseButton() {
		$('div.T-I.J-J5-Ji.T-Pm.T-I-ax7.L3.J-JN-M-I', role: 'button').findAll { it.displayed }
	}

	def gmail_chooseMenu() {
		gmail_toolbar().find('div.J-M.jQjAxd', role: 'menu', 0)
	}

	def gmail_allMenuItem() {
		gmail_chooseMenu().find('div.J-N .J-N-Jz', role: 'menuitem', text: contains('All'), 0)
	}

	def gmail_archiveButton() {
		$('div.T-I.J-J5-Ji.lR.T-I-ax7', role: 'button').findAll { it.displayed }
	}

	def gmail_inboxButton() {
		$('a.J-Ke.n0', href: contains("#inbox")).findAll { it.displayed }
	}

    //Gmail API support.

    def findFormList(list, query) {
        return list.find {
            it.contains(query)
        }
    }

    def getConfirmLink(String query) {
        def link = queryGmailMessage(query)
        if(link) {
            link = link[0].toString()
        } else {
            link = ""
        }
        return link
    }

    def getAllLinks(String query) {
        return queryGmailMessage(query)
    }

    def queryGmailMessage(String queryString) throws IOException {
        // Build a new authorized API client service.
        Gmail service = GmailAPI.getGmailService()

        // Print the labels in the user's account.
        String user = "me"

        List<Message> messages = GmailAPI.listMessagesMatchingQuery(service, user, queryString)
        //with the q, you can also use filter, e.g. "FN+car1440590895514 is:unread"

        def linksArray = []

        if (messages.size() == 0) {
            System.out.println("No messages found.")
        } else {
            System.out.println("Matched messages:")

            for (Message message : messages) {
                def messageContents = GmailAPI.getMessage(service, user, message.getId())
                Pattern pattern = Pattern.compile("https?://\\S*");
                Matcher matcher = pattern.matcher(messageContents);
                if(matcher.find()) {
                    linksArray.add(matcher.group())
                }
            }
        }

        return linksArray
    }

    def archivedQueryEmails(String queryString) throws IOException {
        // Build a new authorized API client service.
        Gmail service = GmailAPI.getGmailService()

        // Print the labels in the user's account.
        String user = "me"
        List<String> labels = new ArrayList<String>()
        labels.add("INBOX")

        List<Message> messages = GmailAPI.listMessagesMatchingQuery(service, user, queryString)

        if (messages.size() == 0) {
            System.out.println("No messages found.")
            return false
        } else {
            System.out.println("Matched messages:")
            for (Message message : messages) {
                GmailAPI.modifyThread(service, user, message.getThreadId(), null, labels)
            }

        }

    }


}
