package specs

import com.gmongo.GMongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import pages.client.StaffEmailConfirmationPage
import pages.mail.GmailAppPage
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class ClientSmokeFunctionalSpec extends RatchetSmokeFunctionalSpec {
    @Shared IDENTIFY
    @Shared GMAIL_WINDOW

    def setupSpec() {

        def credentials = MongoCredential.createMongoCRCredential('albert.zhang', 'ratchet-tests', 'Passw0rd_1' as char[])
        def client = new GMongoClient(new ServerAddress('ds043012.mongolab.com', 43012), [credentials])
        def db = client.getDB('ratchet-tests');

        IDENTIFY = db.smoking.findOne(name: 'IDENTIFY').value

        GMAIL_WINDOW = ''
    }

    @Ignore
    def "direct to admin agent email confirmation page successfully"() {
        // TODO: this should navigate to email page first
        when: "Click the line of email to view details"
        $("table").find("td", text: contains("FN+ast"), 0).click()

        waitFor(20, 1) {
            $('a', href: contains(getClientDomain())).displayed
        }

        GMAIL_WINDOW = currentWindow
        switchToNewWindow {
            $('a', href: contains(getClientDomain())).click()
        }


        then: "Direct to staff email confirmation page"
        waitFor(10, 1) {
            at StaffEmailConfirmationPage
        }
    }

    @Ignore
    def "activate agent created by admin successfully"() {
    }

    def "should login with the activate agent created by admin successfully"() {

    }

    def "add account successfully"() {}

    def "go to account detail page successfully"() {

    }

    def "should logout with the activate agent created by admin successfully"() {

    }

    def "direct to client account email confirmation page successfully"() {

    }

    def "activate account created by client successfully"() {
    }

    def "should login with the activate account created by client successfully"() {

    }

    def "add patient successfully"() {}

    def "go to patient detail page successfully"() {}

    def "activate patient successfully"() {}

    def "should receive task email successfully"() {}


}
