package com.ratchethealth.admin

import grails.converters.JSON

class AnnouncementsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

	def announcementService

    def index() {
        String token = request.session.token
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        def announceList = announcementService.getAnnouncements(token, page, pageSize)

    	render view: '/announcement/announcements', model: [annouceList:announceList, pagesize: pageSize]
    }

    def getAnnouncements() {
        String token = request.session.token
        def offset = params?.start
        def max = params?.length

        def resp = announcementService.getAnnouncements(token, offset, max)

        render resp as JSON
    }

    def addAnnouncement(Announcement announcement) {
        String token = request.session.token

        def result = announcementService.addAnnouncement(token, announcement)

        if (result) {
            render result as JSON
        }
    }

    def editAnnouncement(Announcement announcement) {
        String token = request.session.token

        def result = announcementService.editAnnouncement(token, announcement)

        if (result) {
            render result as JSON
        }
    }

    def closeAnnouncement(Announcement announcement) {
        String token = request.session.token

        def result = announcementService.editAnnouncement(token, announcement)

        if (result) {
            render result as JSON
        }
    }

    def deleteAnnouncement() {
        String token = request.session.token
        Announcement announcement = new Announcement()
        announcement.id = params.announcementId as int

       def result = announcementService.deleteAnnouncement(token, announcement)

        if (result) {
            render status: 204
        }
    }
}
