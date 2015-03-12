package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import com.xplusz.ratchet.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AnnouncementService {
    // dependency injection for grailsApplication
    def grailsApplication

    /**
     * Get client list
     *
     * @param offset # page index from 0
     * @param max # page size
     * @return client list
     */
    def getAnnouncements(HttpServletRequest request, HttpServletResponse response, offset, max)
            throws ServerException {
        String announcementsUrl = grailsApplication.config.ratchetv2.server.url.announcements

        //TO-DO: create service to handle announcements on the server

        /*def resp = Unirest.get(announcementsUrl)
                .queryString("offset", offset)
                .queryString("max", max)
                .asString()

        def result = JSON.parse(resp.body)

        if (resp.status == 200) {
            def map = [:]
            map.put("recordsTotal", result.totalCount)
            map.put("recordsFiltered", result.totalCount)
            map.put("data", result.items)
            log.info("Get Announcements success, token: ${request.session.token}")
            return map
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(errorMessage)
        }*/

        def map = [:]

        def result =[:]
        result.id = 1
        result.annoucement = "hello world"
        result.status = "active"
        result.background = "#fff"
        result.timeCreated = "12:00"

        map.put("recordsTotal", 1)
        map.put("recordsFiltered", 1)
        map.put("data", [result])

        return map
	}
}
