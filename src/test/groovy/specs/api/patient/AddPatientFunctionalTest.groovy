package specs.api.patient
import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared
import static org.junit.Assert.assertEquals

class AddPatientFunctionalTest extends RatchetAPITest {
    @Shared url
    @Shared IDENTIFY
    @Shared clientId
    @Shared baseUrl = getBaseUrl()
    @Before
    public void setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY
        clientId = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).CLIENT_ID
        url = "${baseUrl}/api/v2/clients/${clientId}/patients/api${IDENTIFY}"
    }

    @Test
    public void addPatient() {

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${IDENTIFY}");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${IDENTIFY}@xplusz.com")
                    .queryString("phoneNumber", "12015466789")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            assertEquals(201, resp.getStatus());
        }
    }

    @Test
    public void addPatientWithWrongPhone() {

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${IDENTIFY}");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${IDENTIFY}@xplusz.com")
                    .queryString("phoneNumber", "2326265163")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(104, result.error.errorId)
        }
    }

    @Test
    public void addPatientWithInvalidEmail() {

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${IDENTIFY}");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${IDENTIFY}@xplusz")
                    .queryString("phoneNumber", "12015466789")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(105, result.error.errorId)
        }
    }


}
