package specs.admin

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import groovy.json.JsonSlurper
import org.json.JSONException
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*;


public class UnirestTest extends RatchetAPIService {

    def TIME;


    @Before
    public void setUp() {

        TIME = System.currentTimeMillis();

    }


    def getToken() {
        def url = 'http://api.develop.ratchethealth.com/api/v2/debug/auth'

        withPost(url) { req ->
            def resp = req
                    .header("accept", "application/json")
                    .queryString('requestMethod', 'GET')
                    .queryString('requestURL', '/api/v2/clients/54051155/patients/api5')
                    .queryString('clientId', '54051155')
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

    @Test
    public void getPatients() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/54051155/patients/api5"

        def (token, dateString) = getToken();

        withGet(token, dateString, url) { req ->
            def resp = req
                    .asString()

            assertEquals(200, resp.status);


        }
    }



/*   @Test
    public void addPatients() {

//        String addPatientsUrl = "http://api.develop.ratchethealth.com/api/v1/clients/%s/patients/assign/record"
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/54051155/patients/api11"
//        def url = String.format(addPatientsUrl, clientId)
        def token = getToken();

        withPost(token, url) { req ->
            def resp = req
                    .field("email", 'thomas.cai+pat003@xplusz.com')
                    .field("phoneNumber", "12015466789")
                    .field("firstName", "thomas")
                    .field("lastName", "cai")
                    .asString()

            assertEquals(201, resp.getStatus());
        }
    }*/


}