package specs.api
import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.text.SimpleDateFormat
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.JVM)
public class ApiTest extends RatchetAPIService {

    public static long TimeMills = 0;
    def clientId
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateNowStr = sdf.format(d);

    @Before
    public void setup() {
         clientId = "54051155"
    }

    def getToken(String methodString, String uri) {
        def url = 'http://api.develop.ratchethealth.com/api/v2/debug/auth'

        withPost(url) { req ->
            def resp = req
                    .queryString('requestMethod', methodString)
                    .queryString('requestURL', uri)
                    .queryString('clientId', clientId)
                    .asString()

            if (resp.status == 200) {
                def slurper = new JsonSlurper()
                def result = slurper.parseText(resp.body)

                return ["${result.token}:${result.nonce}:${result.digetst}", result.date]
            }
            else {
                handleError(resp)
            }
        }
    }

    def getTreatmentId() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            if(resp.status == 200){
                def slurper = new JsonSlurper()
                def result = slurper.parseText(resp.body)
                def arr = []
                for(int i=0; i < result.items.size; i++){
                    arr.push(result.items[i].treatmentId)
                }
                return arr
            }
            else {
                handleError(resp)
            }
        }
    }

    @Test
    public void addPatient() {
        def TIME = System.currentTimeMillis();
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
    public void updatePatient() {

        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}"

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
    public void getPatient() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${TimeMills}");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(200, resp.status);
        }
    }

    @Test
    public void addTreatment() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments"

        def (token, dateString) = getToken('POST', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withPost(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("treatmentTemplateId", "54132843")
                    .queryString("surgeryDate", dateNowStr)
                    .asString()
            assertEquals(201, resp.status)

        }
    }

    @Test
    public void getTreatments() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            assertEquals(200, resp.status)
        }
    }

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

    @Test
    public void updateTreatment() {
        def treatmentId = getTreatmentId()[0]
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}"

        def (token, dateString) = getToken('PUT', "/api/v2/clients/${clientId}/patients/api${TimeMills}/treatments/${treatmentId}");

        withPut(token, dateString, url) { req ->
            def resp = req
                    .queryString("groupId", "54051199")
                    .queryString("providerNpi", "2154154615")
                    .queryString("surgeryDate", dateNowStr)
                    .asString()

            assertEquals(200, resp.status);
        }
    }

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