package pages.mail

import geb.Page

class GmailAppPage extends Page {
    static at = { title.endsWith("- Gmail") }

    static content = {
        searchInput { $('#gbqfq') }
        searchButton { $('#gbqfb') }
        inboxButton { $('a', href: contains("#inbox")) }
        mainContent { $('div', role: 'main') }
        mailTable { mainContent.find('table.zt') }
        mailContent { mainContent.find('table', role: 'presentation') }
    }
}
