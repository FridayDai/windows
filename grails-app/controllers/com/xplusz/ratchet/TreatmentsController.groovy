package com.xplusz.ratchet

import grails.converters.JSON

class TreatmentsController extends BaseController {

    def beforeInterceptor = [action: this.&auth]

    def treatmentService

    def addTreatment(Treatment treatment) {
        treatment.clientId = params.clientId.toInteger()

        treatment = treatmentService.createTreatment(request, response, treatment)

        if (treatment.id) {

            treatment.lastUpdated = System.currentTimeMillis()

            render treatment as JSON
        }
    }

    def treatmentDetail() {
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def treatment = treatmentService.getTreatment(request, response, clientId, treatmentId)
        def tools = treatmentService.getToolsInTreatment(request, response, treatmentId)
        def predefinedTools = treatmentService.getPredefinedTools(request, response)

        render view: '/treatment/treatmentDetail', model: [clientId       : clientId,
                                                           treatment      : treatment,
                                                           tools          : tools,
                                                           predefinedTools: predefinedTools]
    }

    def editTreatment(Treatment treatment) {
        treatment.clientId = params.clientId.toInteger()
        treatment.id = params.treatmentId.toInteger()

        def success = treatmentService.updateTreatment(request, response, treatment)

        if (success) {
            render status: 200
        }
    }

    def closeTreatment() {
        int clientId = params.clientId.toInteger()
        int treatmentId = params.treatmentId.toInteger()

        def success = treatmentService.closeTreatment(request, response, clientId, treatmentId)

        if (success) {
            render status: 204
        }
    }

    def getTools() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def toolList = treatmentService.getTools(request, response, treatmentId, page, pagesize)

        if (toolList) {
            render toolList as JSON
        }
    }

    def getTasks() {
        def page = params.page ?: RatchetConstants.DEFAULT_PAGE_OFFSET
        def pagesize = params.pagesize ?: RatchetConstants.DEFAULT_PAGE_SIZE

        int treatmentId = params.treatmentId.toInteger()

        def taskList = treatmentService.getTasks(request, response, treatmentId, page, pagesize)

        if (taskList) {
            render taskList as JSON
        }
    }

    def addTool(Tool tool) {
        tool.treatmentId = params.treatmentId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.addTool(request, response, tool)

        if (result) {
            render result as JSON
        }
    }

    def editTool(Tool tool) {
        tool.treatmentId = params.treatmentId.toInteger()
        tool.id = params.toolId.toInteger()
        tool.defaultDueTime = (tool.defaultDueTimeHour + tool.defaultDueTimeDay * 24) * 3600000

        def result = treatmentService.updateTool(request, response, tool)

        if (result) {
            render result as JSON
        }
    }

    def deleteTool() {
        int treatmentId = params.treatmentId.toInteger()
        int toolId = params.toolId.toInteger()

        def success = treatmentService.deleteTool(request, response, treatmentId, toolId)

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

        def result = treatmentService.addTask(request, response, task)

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

        def result = treatmentService.updateTask(request, response, task)

        if (result) {
            render result as JSON
        }
    }

    def deleteTask() {
        int treatmentId = params.treatmentId.toInteger()
        int taskId = params.taskId.toInteger()

        def success = treatmentService.deleteTask(request, response, treatmentId, taskId)

        if (success) {
            render status: 204
        }
    }
}
