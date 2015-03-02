package com.xplusz.ratchet

import com.mashape.unirest.http.Unirest
import com.xplusz.ratchet.exceptions.ServerException
import grails.converters.JSON

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
	def getTreatments(int clientId, int offset, int max) throws ServerException {
		String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

		def url = String.format(treatmentsUrl, clientId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Create new treatment
	 *
	 * @param treatment # new Treatment instance
	 * @return treatment   # created treatment
	 */
	def createTreatment(Treatment treatment) throws ServerException {
		String treatmentsUrl = grailsApplication.config.ratchetv2.server.url.treatments

		def url = String.format(treatmentsUrl, treatment.clientId)

		def resp = Unirest.post(url)
				.field("title", treatment.title)
				.field("tmpTitle", treatment.tmpTitle)
				.field("description", treatment.description)
				.field("surgeryTimeRequired", treatment.surgeryTimeRequired)
				.asString()

		if (resp.status == 201) {
			def result = JSON.parse(resp.body)

			treatment.id = result.id
			return treatment
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Get one treatment by id
	 *
	 * @param clientId
	 * @param treatmentId
	 * @return client
	 */
	def getTreatment(int clientId, int treatmentId) throws ServerException {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, clientId, treatmentId)

		def resp = Unirest.get(url).asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Update treatment
	 *
	 * @param treatment # updated Treatment instance
	 * @return isSuccess
	 */
	def updateTreatment(Treatment treatment) throws ServerException {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, treatment.clientId, treatment.id)

		def resp = Unirest.post(url)
				.field("title", treatment.title)
				.field("tmpTitle", treatment.tmpTitle)
				.field("description", treatment.description)
				.field("surgeryTimeRequired", treatment.surgeryTimeRequired)
				.asString()

		if (resp.status == 200) {
			return true
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}

		return false
	}

	/**
	 * Close treatment
	 *
	 * @param clientId # client id
	 * @param treatmentId # treatment id
	 * @return isSuccess
	 */
	def closeTreatment(int clientId, int treatmentId) throws ServerException {
		String oneTreatmentUrl = grailsApplication.config.ratchetv2.server.url.oneTreatment

		def url = String.format(oneTreatmentUrl, clientId, treatmentId)

		def resp = Unirest.delete(url).asString()

		if (resp.status == 204) {
			return true
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
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
	def getTools(int treatmentId, int offset, int max) throws ServerException {
		String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

		String url = String.format(toolsUrl, treatmentId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
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
	def getToolsInTreatment(int treatmentId) throws ServerException {
		String allToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfTreatment

		String url = String.format(allToolsUrl, treatmentId)

		def resp = Unirest.get(url).asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Get all predefined tools
	 *
	 * @return tool list
	 */
	def getPredefinedTools() throws ServerException {
		String allPredefinedToolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.allToolsOfPredefined

		def resp = Unirest.get(allPredefinedToolsUrl).asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Add new tool to a treatment
	 *
	 * @param tool # new Tool instance
	 * @return tool   # new Tool instance
	 */
	def addTool(Tool tool) throws ServerException {
		String toolsUrl = grailsApplication.config.ratchetv2.server.url.treatment.tools

		def url = String.format(toolsUrl, tool.treatmentId)

		def resp = Unirest.post(url)
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
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * update tool of a treatment
	 *
	 * @param tool # Tool instance
	 * @return tool object
	 */
	def updateTool(Tool tool) throws ServerException {
		String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool

		def url = String.format(oneToolUrl, tool.treatmentId, tool.id)

		def resp = Unirest.post(url)
				.field("title", tool.title)
				.field("description", tool.description)
				.field("requireCompletion", tool.requireCompletion)
				.field("defaultDueTime", tool.defaultDueTime)
				.field("reminder", tool.reminder)
				.field("detailedDescription", tool.detailedDescription)
				.field("type", tool.type)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Delete tool
	 *
	 * @param treatmentId # treatment id
	 * @param toolId # tool id
	 * @return isSuccess
	 */
	def deleteTool(int treatmentId, int toolId) throws ServerException {
		String oneToolUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTool

		def url = String.format(oneToolUrl, treatmentId, toolId)

		def resp = Unirest.delete(url).asString()

		if (resp.status == 204) {
			return true
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
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
	def getTasks(int treatmentId, int offset, int max) throws ServerException {
		String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks

		String url = String.format(tasksUrl, treatmentId)

		def resp = Unirest.get(url)
				.queryString("offset", offset)
				.queryString("max", max)
				.asString()

		if (resp.status == 200) {
			return JSON.parse(resp.body)
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Add new task to a treatment
	 *
	 * @param task # new Task instance
	 * @return task   # new Task instance
	 */
	def addTask(Task task) throws ServerException {
		String tasksUrl = grailsApplication.config.ratchetv2.server.url.treatment.tasks

		def url = String.format(tasksUrl, task.treatmentId)

		def resp = Unirest.post(url)
				.field("toolId", task.toolId)
				.field("sendTimeOffset", task.sendTimeOffset)
				.field("immediate", task.immediate)
				.asString()

		if (resp.status == 201) {
			def result = JSON.parse(resp.body)

			return result
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * update task of a treatment
	 *
	 * @param task # Tool instance
	 * @return task   # returned task object
	 */
	def updateTask(Task task) throws ServerException {
		String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask

		def url = String.format(oneTaskUrl, task.treatmentId, task.id)

		def resp = Unirest.post(url)
				.field("toolId", task.toolId)
				.field("sendTimeOffset", task.sendTimeOffset)
				.field("immediate", task.immediate)
				.asString()

		if (resp.status == 200) {
			def result = JSON.parse(resp.body)

			return result
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}

	/**
	 * Delete task
	 *
	 * @param treatmentId # treatment id
	 * @param taskId # task id
	 * @return isSuccess
	 */
	def deleteTask(int treatmentId, int taskId) throws ServerException{
		String oneTaskUrl = grailsApplication.config.ratchetv2.server.url.treatment.oneTask

		def url = String.format(oneTaskUrl, treatmentId, taskId)

		def resp = Unirest.delete(url).asString()

		if (resp.status == 204) {
			return true
		} else {
			def result = JSON.parse(resp.body)

			String errorMessage = result?.errors?.message ?: result?.error?.errorMessage
			throw new ServerException(errorMessage)
		}
	}
}
