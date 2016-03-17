package pages.admin

import geb.Page
import model.OutcomeTaskModel
import model.OutcomeToolModel
import modules.admin.DefinedToolModalModule
import modules.admin.TaskModalModule
import modules.admin.TasksTableModule
import modules.admin.ToolsTableModule

class TreatmentPage extends Page {
	static at = { $("#treatment-info-panel").size() == 1 }

	static content = {
		addToolButton { $("#tool-pool-list-panel .tool-bar button") }
		addDefinedToolButton { $("#add-defined-tool-btn") }
		addDefinedToolModal { $("#add-defined-tool-modal").module(DefinedToolModalModule) }

		toolTable { $("#tool-pool-table").module(ToolsTableModule) }

		addTaskButton { $("#add-item-btn") }
		addTaskModal { $("#add-item-modal").module(TaskModalModule) }

		taskTable { $("#task-table").module(TasksTableModule) }

		profileTab { $("#menu a", text: contains("PROFILE")) }

        logoIcon { $(".logo") }
	}

	def addOutcomeTool(OutcomeToolModel tool) {
		addToolButton.click()

		waitFor(3, 1) { addDefinedToolButton.displayed }

		addDefinedToolButton.click()

		waitFor(3, 1) { addDefinedToolModal.displayed }

		addDefinedToolModal.tool = tool.name
		addDefinedToolModal.defaultDueTimeDay = tool.defaultDueTimeDay
		addDefinedToolModal.defaultExpireTimeDay = tool.defaultExpireTimeDay
		addDefinedToolModal.reminder << tool.reminder

		addDefinedToolModal.createButton.click()

		waitFor(30, 1) { !addDefinedToolModal.displayed }

		waitFor(10, 1) {
			def firstRow = toolTable.toolItems[0]

			firstRow.toolTitle == tool.name
			firstRow.description == tool.description
			firstRow.type == tool.type
		}
	}

	def addImmediateTask(OutcomeTaskModel task) {
		js.exec('window.scrollBy(0, 1000)') //将页面窗口向下滚动1000像素

		addTaskButton.click()

		waitFor(3, 1) { addTaskModal.displayed }

		addTaskModal.tool = task.tool.name

        task.sendTimeDirection = "Immediate"
		addTaskModal.sendTimeDirection = task.sendTimeDirection

		addTaskModal.createButton.click()

		waitFor(30, 1) { !addTaskModal.displayed }

		waitFor(10) {
			def firstRow = taskTable.taskItems[0]
			firstRow.toolTitle == task.tool.name
			firstRow.description == task.tool.description
			firstRow.toolType == task.tool.type
			firstRow.sendTime == task.sendTimeDirection
			firstRow.dueTime == task.tool.dueTime
		}
	}

	def clickLogoIcon() {
		logoIcon.click()
	}

	def gotoProfilePage() {
		profileTab.click()
	}
}
