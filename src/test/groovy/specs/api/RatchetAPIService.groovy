package specs.api

import com.mashape.unirest.http.exceptions.UnirestException
import com.mashape.unirest.request.GetRequest
import com.mashape.unirest.request.HttpRequestWithBody
import com.mashape.unirest.http.HttpMethod
import groovy.json.JsonSlurper

class RatchetAPIService {

    def messageSource
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
}
