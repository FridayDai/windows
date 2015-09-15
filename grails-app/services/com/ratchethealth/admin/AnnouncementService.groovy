package com.ratchethealth.admin

import grails.converters.JSON

class AnnouncementService extends RatchetAPIService {
	def grailsApplication

	def getAnnouncements(String token, offset, max) {
		log.info("Call backend service to get Announcements with offset and max, token: ${token}.")

		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.announcements

		withGet(token, announcementsUrl) { req ->
			def resp = req
					.queryString("offset", offset)
					.queryString("max", max)
					.asString()

			if (resp.status == 200) {
                def result = JSON.parse(resp.body)
				log.info("Get Announcements success, token: ${token}")

				[
					"recordsTotal":	result.totalCount,
					"recordsFiltered": result.totalCount,
					"data": result.items
				]
			} else {
				handleError(resp)
			}
		}
	}

	def addAnnouncement(String token, Announcement announcement) {
		log.info("Call backend service to add Announcement with status, content and colorHex, token: ${token}.")

		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.announcements

		withPost(token, announcementsUrl) { req ->
			def resp = req
					.field("status", announcement.status)
					.field("content", announcement.content)
					.field("colorHex", announcement.colorHex)
					.asString()

			if (resp.status == 201) {
                def result = JSON.parse(resp.body)
				log.info("Create Announcements success, token: ${token}")

				announcement.id = result.id

				announcement
			} else {
				handleError(resp)
			}
		}
	}

	def editAnnouncement(String token, Announcement announcement) {
		log.info("Call backend service to edit Announcement with status, content and colorHex, token: ${token}.")

		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.oneAnnouncement
		def url = String.format(announcementsUrl, announcement.id)

		withPost(token, url) { req ->
			def resp = req
					.field("status", announcement.status)
					.field("content", announcement.content)
					.field("colorHex", announcement.colorHex)
					.asString()

			if (resp.status == 200) {
				log.info("Update Announcements success, token: ${token}")

				announcement
			} else {
				handleError(resp)
			}
		}
	}

	def deleteAnnouncement(String token, Announcement announcement) {
		log.info("Call backend service to delete Announcement, token: ${token}.")

		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.oneAnnouncement
		def url = String.format(announcementsUrl, announcement.id)

		withDelete(token, url) { req ->
			def resp = req.asString()

			if (resp.status == 204) {
				log.info("Delete Announcement success, token: ${token}")

				true
			} else {
				handleError(resp)
			}
		}
	}
}
