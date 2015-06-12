package com.ratchethealth.admin

import com.mashape.unirest.http.Unirest
import com.ratchethealth.admin.exceptions.ServerException
import grails.converters.JSON

import javax.servlet.http.HttpServletRequest

class TreatmentService {
    //dependency injection for grailsApplication
    def grailsApplication

    /**
     * Get treatment list
     *
     * @param clientId # client id
     * @param offset
     * @param max
     * @return treatmentList   # treatment List
     */
    def getTreatments(HttpServletRequest request, int clientId, offset, max)
            throws ServerException {
        String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments
        log.info("Call backend service to get treatments with offset and max, token: ${request.session.token}.")

        def url = String.format(treatmentsUrl, clientId)

        def resp = Unirest.get(url)
                .header("X-Auth-Token", request.session.token)
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
            log.info("Get treatments success, token: ${request.session.token}")

            return map
        } else {
            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Create new treatment
     *
     * @param treatment # new Treatment instance
     * @return treatment   # created treatment
     */
    def createTreatment(HttpServletRequest request, Treatment treatment)
            throws ServerException {
        String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

        def url = String.format(treatmentsUrl, treatment.clientId)
        log.info("Call backend service to creat treatment with title, tmpTitle, description and surgeryTimeRequired, token: ${request.session.token}.")

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("title", treatment.title)
                .field("tmpTitle", treatment.tmpTitle)
                .field("description", treatment.description)
                .field("surgeryTimeRequired", treatment.surgeryTimeRequired)
                .asString()

        if (resp.status == 201) {
            log.info("Create treatment success, token: ${request.session.token}")
            def result = JSON.parse(resp.body)

            treatment.id = result.id
            return treatment
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Get one treatment by id
     *
     * @param clientId
     * @param treatmentId
     * @return client
     */
    def getTreatment(HttpServletRequest request, int clientId, int treatmentId)
            throws ServerException {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

        def url = String.format(oneTreatmentUrl, clientId, treatmentId)
        log.info("Call backend service to get treatment, token: ${request.session.token}.")

        def resp = Unirest.get(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 200) {
            log.info("Get treatment success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Update treatment
     *
     * @param treatment # updated Treatment instance
     * @return isSuccess
     */
    def updateTreatment(HttpServletRequest request, Treatment treatment)
            throws ServerException {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

        def url = String.format(oneTreatmentUrl, treatment.clientId, treatment.id)
        log.info("Call backend service to update treatment with title, tmpTitle, description, surgeryTimeRequired, token: ${request.session.token}.")

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("title", treatment.title)
                .field("tmpTitle", treatment.tmpTitle)
                .field("description", treatment.description)
                .field("surgeryTimeRequired", treatment.surgeryTimeRequired)
                .asString()

        if (resp.status == 200) {
            log.info("Update treatment success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Close treatment
     *
     * @param clientId # client id
     * @param treatmentId # treatment id
     * @return isSuccess
     */
    def closeTreatment(HttpServletRequest request, int clientId, int treatmentId)
            throws ServerException {
        String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment
        log.info("Call backend service to close treatment, token: ${request.session.token}.")

        def url = String.format(oneTreatmentUrl, clientId, treatmentId)

        def resp = Unirest.delete(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 204) {
            log.info("Close treatment success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Get tool list
     *
     * @param treatmentId # treatment id
     * @param offset # the tool index where start
     * @param max # page size
     * @return tool list
     */
    def getTools(HttpServletRequest request, int treatmentId, int offset, int max)
            throws ServerException {
        String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

        String url = String.format(toolsUrl, treatmentId)
        log.info("Call backend service to get tools with offset and max, token: ${request.session.token}.")

        def resp = Unirest.get(url)
                .header("X-Auth-Token", request.session.token)
                .queryString("offset", offset)
                .queryString("max", max)
                .asString()

        if (resp.status == 200) {
            log.info("Get tools success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Get tool list in a treatment
     *
     * @param treatmentId # treatment id
     * @param offset # the tool index where start
     * @param max # page size
     * @return tool list
     */
    def getToolsInTreatment(HttpServletRequest request, int treatmentId)
            throws ServerException {
        String allToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfTreatment

        String url = String.format(allToolsUrl, treatmentId)
        log.info("Call backend service to get tools in treatment, token: ${request.session.token}.")

        def resp = Unirest.get(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 200) {
            log.info("Get tools in treatment success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Get all predefined tools
     *
     * @return tool list
     */
    def getPredefinedTools(HttpServletRequest request) throws ServerException {
        String allPredefinedToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfPredefined

        def resp = Unirest.get(allPredefinedToolsUrl)
                .header("X-Auth-Token", request.session.token)
                .asString()

        log.info("Call backend service to get predefined tools, token: ${request.session.token}.")

        if (resp.status == 200) {
            log.info("Get predefined tools success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Add new tool to a treatment
     *
     * @param tool # new Tool instance
     * @return tool   # new Tool instance
     */
    def addTool(HttpServletRequest request, Tool tool) throws ServerException {
        String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

        def url = String.format(toolsUrl, tool.treatmentId)
        log.info("Call backend service to add tool with id, title, description, requireCompletion, defaultDueTime, reminder, detailedDescription and type, token: ${request.session.token}.")

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
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
            log.info("Add tool success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * update tool of a treatment
     *
     * @param tool # Tool instance
     * @return tool object
     */
    def updateTool(HttpServletRequest request, Tool tool) throws ServerException {
        String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool

        def url = String.format(oneToolUrl, tool.treatmentId, tool.id)
        log.info("Call backend service to update tool with id, title, description, requireCompletion, defaultDueTime, reminder, detailedDescription and type, token: ${request.session.token}.")


        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("title", tool.title)
                .field("description", tool.description)
                .field("requireCompletion", tool.requireCompletion)
                .field("defaultDueTime", tool.defaultDueTime)
                .field("reminder", tool.reminder)
                .field("detailedDescription", tool.detailedDescription)
                .field("type", tool.type)
                .asString()

        if (resp.status == 200) {
            log.info("Update tool success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Delete tool
     *
     * @param treatmentId # treatment id
     * @param toolId # tool id
     * @return isSuccess
     */
    def deleteTool(HttpServletRequest request, int treatmentId, int toolId)
            throws ServerException {
        String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool
        log.info("Call backend service to delete tool, token: ${request.session.token}.")

        def url = String.format(oneToolUrl, treatmentId, toolId)

        def resp = Unirest.delete(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 204) {
            log.info("Delete tool success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Get task list
     *
     * * @param treatmentId # treatment id
     * @param offset # the tool index where start
     * @param max # page size
     * @return task list
     */
    def getTasks(HttpServletRequest request, int treatmentId, int offset, int max)
            throws ServerException {
        String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks
        log.info("Call backend service to get tasks with offset and max, token: ${request.session.token}.")

        String url = String.format(tasksUrl, treatmentId)

        def resp = Unirest.get(url)
                .header("X-Auth-Token", request.session.token)
                .queryString("offset", offset)
                .queryString("max", max)
                .asString()

        if (resp.status == 200) {
            log.info("Get tasks success, token: ${request.session.token}")
            return JSON.parse(resp.body)
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Add new task to a treatment
     *
     * @param task # new Task instance
     * @return task   # new Task instance
     */
    def addTask(HttpServletRequest request, Task task)
            throws ServerException {
        String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks
        log.info("Call backend service to add task with toolId, sendTimeOffset and immediate, token: ${request.session.token}.")

        def url = String.format(tasksUrl, task.treatmentId)

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("toolId", task.toolId)
                .field("sendTimeOffset", task.sendTimeOffset)
                .field("immediate", task.immediate)
                .asString()

        if (resp.status == 201) {
            log.info("Add task success, token: ${request.session.token}")
            def result = JSON.parse(resp.body)

            return result
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * update task of a treatment
     *
     * @param task # Tool instance
     * @return task   # returned task object
     */
    def updateTask(HttpServletRequest request, Task task) throws ServerException {
        String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask

        def url = String.format(oneTaskUrl, task.treatmentId, task.id)
        log.info("Call backend service to update task with toolId, sendTimeOffset and immediate, token: ${request.session.token}.")

        def resp = Unirest.post(url)
                .header("X-Auth-Token", request.session.token)
                .field("toolId", task.toolId)
                .field("sendTimeOffset", task.sendTimeOffset)
                .field("immediate", task.immediate)
                .asString()

        if (resp.status == 200) {
            log.info("Update task success, token: ${request.session.token}")
            def result = JSON.parse(resp.body)

            return result
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }

    /**
     * Delete task
     *
     * @param treatmentId # treatment id
     * @param taskId # task id
     * @return isSuccess
     */
    def deleteTask(HttpServletRequest request, int treatmentId, int taskId)
            throws ServerException {
        String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask
        log.info("Call backend service to delete task, token: ${request.session.token}.")

        def url = String.format(oneTaskUrl, treatmentId, taskId)

        def resp = Unirest.delete(url)
                .header("X-Auth-Token", request.session.token)
                .asString()

        if (resp.status == 204) {
            log.info("Delete task success, token: ${request.session.token}")
            return true
        } else {
            def result = JSON.parse(resp.body)

            String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
            throw new ServerException(resp.status, errorMessage)
        }
    }
}
