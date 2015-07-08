package com.ratchethealth.admin

import grails.converters.JSON

class AnnouncementService extends RatchetAdminService {
	def grailsApplication

	def getAnnouncements(String token, offset, max) {
		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.announcements
		log.info("Call backend service to get Announcements with offset and max, token: ${token}.")

		withGet(token, announcementsUrl) { req ->
			def resp = req
					.queryString("offset", offset)
					.queryString("max", max)
					.asString()

			def result = JSON.parse(resp.body)

			if (resp.status == 200) {
				def map = [:]
				map.put("recordsTotal", result.totalCount)
				map.put("recordsFiltered", result.totalCount)
				map.put("data", result.items)
				log.info("Get Announcements success, token: ${token}")

				return [resp, map]
			}

			[resp, null]
		}
	}

	def addAnnouncement(String token, Announcement announcement) {
		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.announcements
		log.info("Call backend service to add Announcement with status, content and colorHex, token: ${token}.")

		withPost(token, announcementsUrl) { req ->
			def resp = req
					.field("status", announcement.status)
					.field("content", announcement.content)
					.field("colorHex", announcement.colorHex)
					.asString()

			def result = JSON.parse(resp.body)

			if (resp.status == 201) {
				log.info("Create Announcements success, token: ${token}")

				announcement.id = result.id

				return [resp, announcement]
			}

			[resp, null]
		}
	}

	def editAnnouncement(String token, Announcement announcement) {
		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.oneAnnouncement
		def url = String.format(announcementsUrl, announcement.id)
		log.info("Call backend service to edit Announcement with status, content and colorHex, token: ${token}.")

		withPost(token, url) { req ->
			def resp = req
					.field("status", announcement.status)
					.field("content", announcement.content)
					.field("colorHex", announcement.colorHex)
					.asString()

			if (resp.status == 200) {
				log.info("Update Announcements success, token: ${token}")

				return [resp, announcement]
			}

			[resp, null]
		}
	}

	def deleteAnnouncement(String token, Announcement announcement) {
		String announcementsUrl = grailsApplication.config.ratchetv2.server.url.oneAnnouncement
		def url = String.format(announcementsUrl, announcement.id)
		log.info("Call backend service to delete Announcement, token: ${token}.")

		withDelete(token, url) { req ->
			def resp = req.asString()

			if (resp.status == 204) {
				log.info("Delete Announcement success, token: ${token}")
				return [resp, true]
			}

			[resp, null]
		}
	}
}
