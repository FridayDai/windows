package pages.client

import geb.Page

class PatientDetailPage extends Page{

    static at = { $(".patient-detail") }

    static content = {
        logoutLink { $(".log-out") }
    }
}
