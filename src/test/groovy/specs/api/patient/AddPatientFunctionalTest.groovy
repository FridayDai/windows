package specs.api.patient
import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals


class AddPatientFunctionalTest extends RatchetAPITest {
    @Test
    public void addPatient() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TIME}"

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${TIME}");

        TimeMills = TIME;

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${TIME}@xplusz.com")
                    .queryString("phoneNumber", "12015466789")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            assertEquals(201, resp.getStatus());
        }
    }

    @Test
    public void addPatientWithWrongPhone() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TIME}"

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${TIME}");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${TIME}@xplusz.com")
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

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TIME}"

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api${TIME}");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${TIME}@xplusz")
                    .queryString("phoneNumber", "12015466789")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(105, result.error.errorId)
        }
    }

    @Test
    public void addPatientWithEqualPatientId() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api1448870076979"

        def (token, dateString) = getToken('POST',"/api/v2/clients/${clientId}/patients/api1448870076979");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat${TIME}@xplusz.com")
                    .queryString("phoneNumber", "12015466789")
                    .queryString("firstName", "thomas")
                    .queryString("lastName", "cai")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(107, result.error.errorId)
        }
    }

}
