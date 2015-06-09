package com.ratchethealth.admin

import grails.converters.JSON

class AnnouncementsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

	def announcementService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        def announceList = announcementService.getAnnouncements(request, page, pagesize)

    	render view: '/announcement/announcements', model: [annouceList:announceList, pagesize: pagesize]
    }

    def getAnnouncements() {
        def offset = params?.start
        def max = params?.length
        def resp = announcementService.getAnnouncements(request, offset, max)
        render resp as JSON
    }

    def addAnnouncement(Announcement announcement) {
        def result = announcementService.addAnnouncement(request, announcement)

        if (result) {
            render result as JSON
        }
    }

    def editAnnouncement(Announcement announcement) {
        def result = announcementService.editAnnouncement(request, announcement)

        if (result) {
            render result as JSON
        }
    }

    def closeAnnouncement(Announcement announcement) {
        def result = announcementService.editAnnouncement(request, announcement)

        if (result) {
            render result as JSON
        }
    }

    def deleteAnnouncement() {
        Announcement announcement = new Announcement()
        announcement.id = params.announcementId.toInteger()

       def result = announcementService.deleteAnnouncement(request, announcement)

        if (result) {
            render status: 204
        }
    }
}
