package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest

/**
 * Base Controller.
 */
class BaseController {

	/**
	 *  Verify Permissions. Quick check to see if the current user is logged in. If not, it will redirect to login.
	 *
	 * @return
	 */
	protected auth() {
		if (!session.token) {
			def back

			if (request.isXhr()) {
				render status: 403
			} else {
				back =  request.forwardURI

				redirect(uri: "/login", params:["back": back])
			}

			return false
		} else {
			Unirest.setDefaultHeader("X-Auth-Token", session.token)
			return true
		}
	}
}
