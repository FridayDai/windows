package pages.mail

import geb.Page

class GmailAppPage extends Page {
    static at = { title.endsWith("- Gmail") }

    static content = {
        searchInput { $('#gbqfq') }
        searchButton { $('#gbqfb') }
        inboxButton { $('a', href: contains("#inbox")) }
        mainContent { $('div.BltHke.nH.oy8Mbf', role: 'main', 0) }
        previewMain { $('div.nH', role: 'main', 0) }
        mailTable { mainContent.find('table.zt', 0) }
        mailContent { previewMain.find('table', role: 'presentation', 0) }
        toolBar { $('div.aqL') }
        selectButton { toolBar.find('.J-J5-Ji', 0) }
    }
}
