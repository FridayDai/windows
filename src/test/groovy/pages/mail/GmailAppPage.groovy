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
        toolBar { $('div.Cq.aqL', 0) }
        chooseButton { toolBar.find('div.T-I.J-J5-Ji.T-Pm.T-I-ax7.L3.J-JN-M-I.G-as3', 0) }
        chooseAllCheckbox { chooseButton.find('span', role: 'checkbox', 0) }
        archiveButton { toolBar.find('div.T-I.J-J5-Ji.lR.T-I-ax7', role: 'button', 0) }
    }
}
