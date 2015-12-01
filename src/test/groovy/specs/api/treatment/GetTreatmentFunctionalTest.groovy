package specs.api.treatment

import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals


class GetTreatmentFunctionalTest extends RatchetAPITest {
    @Test
    public void getTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(200, resp.status);
        }
    }
}

