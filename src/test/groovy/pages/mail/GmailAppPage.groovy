package pages.mail

import geb.Page

class GmailAppPage extends Page {
    static at = { title.endsWith("- Gmail") }

    static content = {
        searchInput { $('#gbqfq') }
        searchButton { $('#gbqfb') }
        indexButton { $('a', href: contains("#inbox")) }
        mainContent { $('div', role: 'main') }
        mailTable { $('table.zt') }
    }
}
