package com.ratchethealth.admin

import grails.converters.JSON

class TreatmentsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def treatmentService

    def getTreatments() {
        def offset = params?.start
        def max = params?.length
        int clientId = params.clientId.toInteger()

        def resp = treatmentService.getTreatments(request, clientId, offset, max)

        render resp as JSON
    }

    def addTreatment(Treatment treatment) {
        treatment.clientId = params.clientId.toInteger()

        treatment = treatmentService.createTreatment(request, treatment)

        if (treatment.id) {

            treatment.lastUpdated = System.currentTimeMillis()

            render treatment as JSON
        }
    }

    def treatmentDetail() {
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def treatment = treatmentService.getTreatment(request, clientId, treatmentId)
        def tools = treatmentService.getToolsInTreatment(request, treatmentId)
        def predefinedTools = treatmentService.getPredefinedTools(request)

        render view: '/treatment/treatmentDetail', model: [clientId       : clientId,
                                                           treatment      : treatment,
                                                           tools          : tools,
                                                           predefinedTools: predefinedTools]
    }

    def editTreatment(Treatment treatment) {
        treatment.clientId = params.clientId.toInteger()
        treatment.id = params.treatmentId.toInteger()

        def success = treatmentService.updateTreatment(request, treatment)

        if (success) {
            render status: 200
        }
    }

    def closeTreatment() {
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def success = treatmentService.closeTreatment(request, clientId, treatmentId)

        if (success) {
            render status: 204
        }
    }

    def getTools() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def toolList = treatmentService.getTools(request, treatmentId, page, pagesize)

        if (toolList) {
            render toolList as JSON
        }
    }

    def getTasks() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def taskList = treatmentService.getTasks(request, treatmentId, page, pagesize)

        if (taskList) {
            render taskList as JSON
        }
    }

    def addTool(Tool tool) {
        tool.treatmentId = params.treatmentId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.addTool(request, tool)

        if (result) {
            render result as JSON
        }
    }

    def editTool(Tool tool) {
        tool.treatmentId = params.treatmentId.toInteger()
        tool.id = params.toolId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.updateTool(request, tool)

        if (result) {
            render result as JSON
        }
    }

    def deleteTool() {
        int treatmentId = params.treatmentId.toInteger()
        int toolId = params.toolId.toInteger()

        def success = treatmentService.deleteTool(request, treatmentId, toolId)

        if (success) {
            render status: 204
        }
    }

    def addTask(Task task) {
        task.treatmentId = params.treatmentId.toInteger()

        task.sendTimeOffset = task.sendTimeDirection *
                (task.sendTimeWeeks * 7 * 24 * 60 * 60
                        + task.sendTimeDays * 24 * 60 * 60
                        + task.sendTimeHours * 60 * 60
                        + task.sendTimeMinutes * 60) * 1000

        task.immediate = task.sendTimeDirection == 0

        def result = treatmentService.addTask(request, task)

        if (result) {
            render result as JSON
        }
    }

    def editTask(Task task) {
        task.treatmentId = params.treatmentId.toInteger()
        task.id = params.taskId.toInteger()

        task.sendTimeOffset = task.sendTimeDirection *
                (task.sendTimeWeeks * 7 * 24 * 60 * 60
                        + task.sendTimeDays * 24 * 60 * 60
                        + task.sendTimeHours * 60 * 60
                        + task.sendTimeMinutes * 60) * 1000

        task.immediate = task.sendTimeDirection == 0

        def result = treatmentService.updateTask(request, task)

        if (result) {
            render result as JSON
        }
    }

    def deleteTask() {
        int treatmentId = params.treatmentId.toInteger()
        int taskId = params.taskId.toInteger()

        def success = treatmentService.deleteTask(request, treatmentId, taskId)

        if (success) {
            render status: 204
        }
    }
}
