import geb.spock.GebReportingSpec

/**
 * Created by sky on 1/5/15.
 */
class LoginFunctionalSpec extends GebReportingSpec {

    def "Check that user logins successfully"() {
        when: "we login with correct credential"
        login("xplusz@xplusz.com", "111")

        then: "the pages displays homepage"
        waitFor {
            $(".profile li:first").text() == "logged in as"
        }
    }

    private login(String username, String password) {
        go "login"
        $("input[name='email']").value(username)
        $("input[name='password']").value(password)
        $("input[type='submit']").click()
    }
}
