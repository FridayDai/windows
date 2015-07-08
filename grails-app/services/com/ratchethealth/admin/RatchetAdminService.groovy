package com.ratchethealth.admin

import com.mashape.unirest.http.HttpMethod
import com.mashape.unirest.http.exceptions.UnirestException
import com.mashape.unirest.request.GetRequest
import com.mashape.unirest.request.HttpRequestWithBody
import com.ratchethealth.admin.exceptions.ApiAccessException
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

class RatchetAdminService {
	def withGet(String url, Closure reqHandler) {
		GetRequest get = new GetRequest(HttpMethod.GET, url)

		withReq(get, null, reqHandler)
	}

	def withGet(String token, String url, Closure reqHandler) {
		GetRequest get = new GetRequest(HttpMethod.GET, url)

		withReq(get, token, reqHandler)
	}

	def withPost(String url, Closure reqHandler) {
		HttpRequestWithBody post = new HttpRequestWithBody(HttpMethod.POST, url)

		withReq(post, null, reqHandler)
	}

	def withPost(String token, String url, Closure reqHandler) {
		HttpRequestWithBody post = new HttpRequestWithBody(HttpMethod.POST, url)

		withReq(post, token, reqHandler)
	}

	def withDelete(String token, String url, Closure reqHandler) {
		HttpRequestWithBody delete = new HttpRequestWithBody(HttpMethod.DELETE, url)

		withReq(delete, token, reqHandler)
	}

	def withReq(req, String token, Closure reqHandler)
			throws ServerException, ApiAccessException
	{
		try {
			def reqObj

			if (token)
				reqObj = req.header("X-Auth-Token", token)
			else
				reqObj = req

			def (resp, result) = reqHandler.call(reqObj)

			if (result) {
				return result
			} else if (resp.status == 500 || resp.status == 503) {
				String errorMessage = JSON.parse(resp.body)?.errors?.message
				throw new ApiAccessException(errorMessage)
			} else {
				String errorMessage = JSON.parse(resp.body)?.error?.errorMessage
				throw new ServerException(errorMessage)
			}
		} catch (UnirestException e) {
			throw new ApiAccessException(e.message, e)
		}
	}
}
