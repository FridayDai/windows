package specs.api.treatment

import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals

class DeleteTreatmentFunctionalTest extends RatchetAPITest {

    @Test
    public void deleteTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}"

        def (token, dateString) = getToken('DELETE', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}");

        withDelete(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(204, resp.status);
        }
    }


}
