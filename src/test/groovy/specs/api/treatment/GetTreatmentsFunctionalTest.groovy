package specs.api.treatment

import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared

import static org.junit.Assert.assertEquals

class GetTreatmentsFunctionalTest extends RatchetAPITest {

    @Shared IDENTIFY

    @Before
    public void  setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

    }
    @Test
    public void getTreatments() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(200, resp.status)
        }
    }
}
