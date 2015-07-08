package com.ratchethealth.admin

import grails.converters.JSON

class TreatmentService extends RatchetAdminService {
    def grailsApplication

    def getTreatments(String token, int clientId, offset, max) {
        String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments
        log.info("Call backend service to get treatments with offset and max, token: ${token}.")

        def url = String.format(treatmentsUrl, clientId)

        withGet(token, url) { req ->
            def resp = req
                    .queryString("offset", offset)
                    .queryString("max", max)
                    .queryString("isClientPortal", true)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 200) {
                def map = [:]
                map.put("recordsTotal", result.totalCount)
                map.put("recordsFiltered", result.totalCount)
                map.put("data", result.items)
                log.info("Get treatments success, token: ${token}")

                return [resp, map]
            }

            [resp, null]
        }
    }

    def createTreatment(String token, Treatment treatment) {
        String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

        def url = String.format(treatmentsUrl, treatment.clientId)
        log.info("Call backend service to creat treatment with title, tmpTitle, " +
                "description and surgeryTimeRequired, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("title", treatment.title)
                    .field("tmpTitle", treatment.tmpTitle)
                    .field("description", treatment.description)
                    .field("surgeryTimeRequired", treatment.surgeryTimeRequired)
                    .asString()

            def result = JSON.parse(resp.body)

            if (resp.status == 201) {
                log.info("Create treatment success, token: ${token}")

                treatment.id = result.id
                return [resp, treatment]
            }

            [resp, null]
        }
    }

    def getTreatment(String token, int clientId, int treatmentId) {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

        def url = String.format(oneTreatmentUrl, clientId, treatmentId)
        log.info("Call backend service to get treatment, token: ${token}.")

        withGet(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 200) {
                log.info("Get treatment success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def updateTreatment(String token, Treatment treatment) {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

        def url = String.format(oneTreatmentUrl, treatment.clientId, treatment.id)
        log.info("Call backend service to update treatment with title, tmpTitle, description, " +
                "surgeryTimeRequired, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("title", treatment.title)
                    .field("tmpTitle", treatment.tmpTitle)
                    .field("description", treatment.description)
                    .field("surgeryTimeRequired", treatment.surgeryTimeRequired)
                    .asString()

            if (resp.status == 200) {
                log.info("Update treatment success, token: ${token}")

                return [resp, true]
            }

            [resp, null]
        }
    }

    def closeTreatment(String token, int clientId, int treatmentId) {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment
        log.info("Call backend service to close treatment, token: ${token}.")

        def url = String.format(oneTreatmentUrl, clientId, treatmentId)

        withDelete(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 204) {
                log.info("Close treatment success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def getTools(String token, int treatmentId, int offset, int max) {
        String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

        String url = String.format(toolsUrl, treatmentId)
        log.info("Call backend service to get tools with offset and max, token: ${token}.")

        withGet(token, url) { req ->
            def resp = req
                    .queryString("offset", offset)
                    .queryString("max", max)
                    .asString()

            if (resp.status == 200) {
                log.info("Get tools success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def getToolsInTreatment(String token, int treatmentId) {
        String allToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfTreatment
        String url = String.format(allToolsUrl, treatmentId)
        log.info("Call backend service to get tools in treatment, token: ${token}.")

        withGet(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 200) {
                log.info("Get tools in treatment success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def getPredefinedTools(String token) {
        String allPredefinedToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfPredefined
        log.info("Call backend service to get predefined tools, token: ${token}.")

        withGet(token, allPredefinedToolsUrl) { req ->
            def resp = req.asString()

            if (resp.status == 200) {
                log.info("Get predefined tools success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def addTool(String token, Tool tool) {
        String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools
        def url = String.format(toolsUrl, tool.treatmentId)
        log.info("Call backend service to add tool with id, title, description, requireCompletion, " +
                "defaultDueTime, reminder, detailedDescription and type, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("id", tool.id)
                    .field("title", tool.title)
                    .field("description", tool.description)
                    .field("requireCompletion", tool.requireCompletion)
                    .field("defaultDueTime", tool.defaultDueTime)
                    .field("reminder", tool.reminder)
                    .field("detailedDescription", tool.detailedDescription)
                    .field("type", tool.type)
                    .asString()

            if (resp.status == 201) {
                log.info("Add tool success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def updateTool(String token, Tool tool) {
        String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool

        def url = String.format(oneToolUrl, tool.treatmentId, tool.id)
        log.info("Call backend service to update tool with id, title, description, requireCompletion, " +
                "defaultDueTime, reminder, detailedDescription and type, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("title", tool.title)
                    .field("description", tool.description)
                    .field("requireCompletion", tool.requireCompletion)
                    .field("defaultDueTime", tool.defaultDueTime)
                    .field("reminder", tool.reminder)
                    .field("detailedDescription", tool.detailedDescription)
                    .field("type", tool.type)
                    .asString()

            if (resp.status == 200) {
                log.info("Update tool success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def deleteTool(String token, int treatmentId, int toolId) {
        String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool
        log.info("Call backend service to delete tool, token: ${token}.")

        def url = String.format(oneToolUrl, treatmentId, toolId)

        withDelete(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 204) {
                log.info("Delete tool success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }

    def getTasks(String token, int treatmentId, int offset, int max) {
        String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks
        log.info("Call backend service to get tasks with offset and max, token: ${token}.")

        String url = String.format(tasksUrl, treatmentId)

        withGet(token, url) { req ->
            def resp = req
                    .queryString("offset", offset)
                    .queryString("max", max)
                    .asString()

            if (resp.status == 200) {
                log.info("Get tasks success, token: ${token}")
                return [resp, JSON.parse(resp.body)]
            }

            [resp, null]
        }
    }

    def addTask(String token, Task task) {
        String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks
        log.info("Call backend service to add task with toolId, " +
                "sendTimeOffset and immediate, token: ${token}.")

        def url = String.format(tasksUrl, task.treatmentId)

        withPost(token, url) { req ->
            def resp = req
                    .field("toolId", task.toolId)
                    .field("sendTimeOffset", task.sendTimeOffset)
                    .field("immediate", task.immediate)
                    .asString()

            if (resp.status == 201) {
                log.info("Add task success, token: ${token}")
                def result = JSON.parse(resp.body)

                return [resp, result]
            }

            [resp, null]
        }
    }

    def updateTask(String token, Task task) {
        String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask

        def url = String.format(oneTaskUrl, task.treatmentId, task.id)
        log.info("Call backend service to update task with toolId, " +
                "sendTimeOffset and immediate, token: ${token}.")

        withPost(token, url) { req ->
            def resp = req
                    .field("toolId", task.toolId)
                    .field("sendTimeOffset", task.sendTimeOffset)
                    .field("immediate", task.immediate)
                    .asString()

            if (resp.status == 200) {
                log.info("Update task success, token: ${token}")
                def result = JSON.parse(resp.body)

                return [resp, result]
            }

            [resp, null]
        }
    }

    def deleteTask(String token, int treatmentId, int taskId) {
        String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask
        log.info("Call backend service to delete task, token: ${token}.")

        def url = String.format(oneTaskUrl, treatmentId, taskId)

        withDelete(token, url) { req ->
            def resp = req.asString()

            if (resp.status == 204) {
                log.info("Delete task success, token: ${token}")
                return [resp, true]
            }

            [resp, null]
        }
    }
}
