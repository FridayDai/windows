package specs.api.patient

import org.junit.Test
import specs.api.RatchetAPITest

import static org.junit.Assert.assertEquals


class UpdatePatientFunctionalTest extends RatchetAPITest {
    def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}"
    @Test
    public void updatePatient() {



        def (token, dateString) = getToken('PUT',"/api/v2/clients/${clientId}/patients/api${TimeMills}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat1${TimeMills}@xplusz.com")
                    .queryString("phoneNumber", "2313231312")
                    .queryString("firstName", "colin")
                    .queryString("lastName", "chen")
                    .asString()

            assertEquals(200, resp.getStatus());
        }
    }

    @Test
    public void updatePatientWithWrongPhone() {

        def (token, dateString) = getToken('PUT',"/api/v2/clients/${clientId}/patients/api${TimeMills}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat1${TimeMills}@xplusz.com")
                    .queryString("phoneNumber", "2326265163")
                    .queryString("firstName", "colin")
                    .queryString("lastName", "chen")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(104, result.error.errorId)
        }
    }

    @Test
    public void updatePatientWithInvalidEmail() {

        def (token, dateString) = getToken('PUT',"/api/v2/clients/${clientId}/patients/api${TimeMills}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("email", "thomas.cai+pat1${TimeMills}@xplusza")
                    .queryString("phoneNumber", "2313231312")
                    .queryString("firstName", "colin")
                    .queryString("lastName", "chen")
                    .asString()

            def result = slurper.parseText(resp.body)
            assertEquals(400, resp.getStatus());
            assertEquals(105, result.error.errorId)
        }
    }
}
