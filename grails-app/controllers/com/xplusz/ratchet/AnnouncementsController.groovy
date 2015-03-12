package com.xplusz.ratchet

import grails.converters.JSON

class AnnouncementsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

	def announcementService

    def index() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE
        def isAjax = params.ajax ?: false

        def annouceList = announcementService.getAnnouncements(request, response, page, pagesize)

    	render view: '/announcement/announcements', model: [annouceList:annouceList, pagesize: pagesize]
    }

    def getAnnouncements() {
        def offset = params?.start
        def max = params?.length
        def resp = announcementService.getAnnouncements(request, response, offset, max)
        render resp as JSON
    }
}
