package specs.api.patient

import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals


class GetPatientFunctionalTest extends RatchetAPITest {
    @Test
    public void getPatient() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${TimeMills}");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(200, resp.status);
        }
    }
}
