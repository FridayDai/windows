package specs.api
import com.mashape.unirest.http.exceptions.UnirestException
import com.mashape.unirest.request.GetRequest
import com.mashape.unirest.request.HttpRequestWithBody
import com.mashape.unirest.http.HttpMethod
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

class RatchetAPITest {
    def slurper = new JsonSlurper()
    def messageSource
    static APP_VAR_PATH = "src/test/resources/var.json"
    static APP_CLIENT_PATH = "src/test/resources/info.json"
    def CLIENT_ID = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).CLIENTID
    def TimeMills = new JsonSlurper().parseText(new File(APP_VAR_PATH).text).IDENTIFY

    def withGet(String url, Closure reqHandler) {
        GetRequest get = new GetRequest(HttpMethod.GET, url)

        withReq(get, null, null, reqHandler)
    }

    def withGet(String token, String stringDate, String url, Closure reqHandler) {
            GetRequest get = new GetRequest(HttpMethod.GET, url)

            withReq(get, token, stringDate, reqHandler)
    }

    def withPost(String url, Closure reqHandler) {
        HttpRequestWithBody post = new HttpRequestWithBody(HttpMethod.POST, url)

        withReq(post, null, null, reqHandler)
    }

    def withPost(String token, String stringDate, String url, Closure reqHandler) {
        HttpRequestWithBody post = new HttpRequestWithBody(HttpMethod.POST, url)

        withReq(post, token, stringDate, reqHandler)
    }

    def withDelete(String token, String stringDate, String url, Closure reqHandler) {
        HttpRequestWithBody delete = new HttpRequestWithBody(HttpMethod.DELETE, url)

        withReq(delete, token, stringDate, reqHandler)
    }

    def withPut(String token, String stringDate, String url, Closure reqHandler) {
        HttpRequestWithBody put = new HttpRequestWithBody(HttpMethod.PUT, url)

        withReq(put, token, stringDate, reqHandler)
    }

    def handleError(resp) {
        if (!resp || !resp.status?.toString()?.isNumber()) {
            throw new ApiAccessException(messageSource.getMessage("api.errors.not.access", null, Locale.ENGLISH))
        }

        def body
        try {
            def slurper = new JsonSlurper()
            body = slurper.parseText(resp.body)
        } catch (e) {
            body = [:]
        }

        if (resp.status >= 500) {
            String errorMessage = body?.errors?.message
            throw new ApiAccessException(errorMessage ?: resp.body)
        } else if (resp.status >= 400 && resp.status < 500) {
            String errorMessage = body?.error?.errorMessage
            throw new ServerException(errorMessage ?: resp.body)
        }
    }

    def withReq(req, String token, String stringDate, Closure reqHandler)
            throws ApiAccessException {
        try {
            def reqObj

            if (token){

                reqObj = req.header("Authentication", "hmac ${token}")
                        .header("Date", stringDate)
                        .header("Content-Type", "application/json")
            }
            else
                reqObj = req

            reqHandler.call(reqObj)

        } catch (UnirestException e) {
            throw new ApiAccessException(e.message, e)
        }
    }

    def getToken(String methodString, String uri) {
        def url = 'http://api.develop.ratchethealth.com/api/v2/debug/auth'

        withPost(url) { req ->
            def resp = req
                    .queryString('requestMethod', methodString)
                    .queryString('requestURL', uri)
                    .queryString('clientId', CLIENT_ID)
                    .asString()

            if (resp.status == 200) {
                def result = slurper.parseText(resp.body)

                return ["${result.token}:${result.nonce}:${result.digetst}", result.date]
            }
            else {
                handleError(resp)
            }
        }
    }

    def getTreatmentId() {
        def url = "http://api.develop.ratchethealth.com/api/v2/clients/${CLIENT_ID}/patients/api${TimeMills}/treatments"

        def (token, dateString) = getToken('GET', "/api/v2/clients/${CLIENT_ID}/patients/api${TimeMills}/treatments");

        withGet(token, dateString, url) { req ->
            def resp = req.asString()

            if(resp.status == 200){
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

    static def getDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }
}
