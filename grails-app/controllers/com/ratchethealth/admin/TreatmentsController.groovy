package com.ratchethealth.admin

import grails.converters.JSON

class TreatmentsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def treatmentService

    def getTreatments() {
        String token = request.session.token
        def offset = params?.start
        def max = params?.length
        int clientId = params.clientId.toInteger()

        def resp = treatmentService.getTreatments(token, clientId, offset, max)

        render resp as JSON
    }

    def addTreatment(Treatment treatment) {
        String token = request.session.token
        treatment.clientId = params.clientId.toInteger()

        treatment = treatmentService.createTreatment(token, treatment)

        if (treatment.id) {

            treatment.lastUpdated = System.currentTimeMillis()

            render treatment as JSON
        }
    }

    def treatmentDetail() {
        String token = request.session.token
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def treatment = treatmentService.getTreatment(token, clientId, treatmentId)
        def tools = treatmentService.getToolsInTreatment(token, treatmentId)
        def predefinedTools = treatmentService.getPredefinedTools(token)

        render view: '/treatment/treatmentDetail', model: [clientId       : clientId,
                                                           treatment      : treatment,
                                                           tools          : tools,
                                                           predefinedTools: predefinedTools]
    }

    def editTreatment(Treatment treatment) {
        String token = request.session.token
        treatment.clientId = params.clientId.toInteger()
        treatment.id = params.treatmentId.toInteger()

        def success = treatmentService.updateTreatment(token, treatment)

        if (success) {
            render status: 200
        }
    }

    def closeTreatment() {
        String token = request.session.token
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def success = treatmentService.closeTreatment(token, clientId, treatmentId)

        if (success) {
            render status: 204
        }
    }

    def getTools() {
        String token = request.session.token
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def toolList = treatmentService.getTools(token, treatmentId, page, pageSize)

        if (toolList) {
            render toolList as JSON
        }
    }

    def getTasks() {
        String token = request.session.token
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pageSize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def taskList = treatmentService.getTasks(token, treatmentId, page, pageSize)

        if (taskList) {
            render taskList as JSON
        }
    }

    def addTool(Tool tool) {
        String token = request.session.token
        tool.treatmentId = params.treatmentId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.addTool(token, tool)

        if (result) {
            render result as JSON
        }
    }

    def editTool(Tool tool) {
        String token = request.session.token
        tool.treatmentId = params.treatmentId.toInteger()
        tool.id = params.toolId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.updateTool(token, tool)

        if (result) {
            render result as JSON
        }
    }

    def deleteTool() {
        String token = request.session.token
        int treatmentId = params.treatmentId.toInteger()
        int toolId = params.toolId.toInteger()

        def success = treatmentService.deleteTool(token, treatmentId, toolId)

        if (success) {
            render status: 204
        }
    }

    def addTask(Task task) {
        String token = request.session.token
        task.treatmentId = params.treatmentId.toInteger()

        task.sendTimeOffset = task.sendTimeDirection *
                (task.sendTimeWeeks * 7 * 24 * 60 * 60
                        + task.sendTimeDays * 24 * 60 * 60
                        + task.sendTimeHours * 60 * 60
                        + task.sendTimeMinutes * 60) * 1000

        task.immediate = task.sendTimeDirection == 0

        def result = treatmentService.addTask(token, task)

        if (result) {
            render result as JSON
        }
    }

    def editTask(Task task) {
        String token = request.session.token
        task.treatmentId = params.treatmentId.toInteger()
        task.id = params.taskId.toInteger()

        task.sendTimeOffset = task.sendTimeDirection *
                (task.sendTimeWeeks * 7 * 24 * 60 * 60
                        + task.sendTimeDays * 24 * 60 * 60
                        + task.sendTimeHours * 60 * 60
                        + task.sendTimeMinutes * 60) * 1000

        task.immediate = task.sendTimeDirection == 0

        def result = treatmentService.updateTask(token, task)

        if (result) {
            render result as JSON
        }
    }

    def deleteTask() {
        String token = request.session.token
        int treatmentId = params.treatmentId.toInteger()
        int taskId = params.taskId.toInteger()

        def success = treatmentService.deleteTask(token, treatmentId, taskId)

        if (success) {
            render status: 204
        }
    }
}
