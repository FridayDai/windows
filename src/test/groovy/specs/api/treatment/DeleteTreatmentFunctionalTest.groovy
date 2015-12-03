package specs.api.treatment
import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import specs.api.RatchetAPITest
import spock.lang.Shared

import static org.junit.Assert.assertEquals

class DeleteTreatmentFunctionalTest extends RatchetAPITest {

    @Shared IDENTIFY

    @Before
    public void setupSpec() {
        IDENTIFY = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

    }
    @Test
    public void deleteTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}"

        def (token, dateString) = getToken('DELETE', "/api/v2/clients/${clientId}/patients/api${IDENTIFY}/treatments/${treatmentId}");

        withDelete(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(204, resp.status);
        }
    }


}
