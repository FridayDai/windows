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
        toolBar { $('div.Cq.aqL').findAll { it.'@gh' == 'mtb' } }
        chooseButton { toolBar.find('div', role: 'button').findAll { it.'@aria-label' == 'Select' } }
        chooseAllCheckbox { chooseButton.find('span', role: 'checkbox', 0) }
        archiveButton { toolBar.find('div', role: 'button').findAll { it.'@aria-label' == 'Archive' } }
        refreshButton { toolBar.find('div', role: 'button').findAll { it.'@aria-label' == 'Refresh' } }
    }
}
