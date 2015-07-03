package pages.mail

import geb.Page

class GmailAppPage extends Page {
    static at = { title.endsWith("- Gmail") }

    static content = {
        searchInput { $('#gbqfq') }
        searchButton { $('#gbqfb') }
        inboxButton { $('a.J-Ke.n0', href: contains("#inbox"), 0) }
        mainContent { $('div.BltHke.nH.oy8Mbf', role: 'main', 0) }
        previewMain { $('div.nH', role: 'main', 0) }
        mailTable { mainContent.find('table.zt', 0) }
        mailContent { previewMain.find('table', role: 'presentation', 0) }
        toolBar { $('div.Cq.aqL').findAll { it.'@gh' == 'mtb' } }
        chooseButton { toolBar.find('div.T-I.J-J5-Ji.T-Pm.T-I-ax7.L3.J-JN-M-I.G-as3', role: 'button', 0) }
        chooseAllCheckbox { chooseButton.find('span', role: 'checkbox', 0) }
        archiveButton { toolBar.find('div.T-I.J-J5-Ji.lR.T-I-ax7', role: 'button', 0) }
        refreshButton { toolBar.find('div.T-I.J-J5-Ji.nu.T-I-ax7.L3', role: 'button', 0) }
    }
}
