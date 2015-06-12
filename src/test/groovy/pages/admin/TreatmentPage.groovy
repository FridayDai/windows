package pages.admin

import geb.Page
import modules.admin.DefinedToolModelModule
import modules.admin.TaskModelModule

class TreatmentPage extends Page {
	static at = { $("#treatment-info-panel").size() == 1 }

	static content = {
		addToolButton { $("#tool-pool-list-panel .tool-bar button") }
		addDefinedToolButton { $("#add-defined-tool-btn") }
		addDefinedToolModel { $("#add-defined-tool-modal") }
		addDefinedToolModelModule { module DefinedToolModelModule, $("#add-defined-tool-modal") }

		toolTable { $("#tool-pool-table") }

		addTaskButton { $("#add-item-btn") }
		addTaskModel { $("#add-item-modal") }
		addTaskModleModule { module TaskModelModule, $("#add-item-modal") }

		taskTable { $("#task-table") }

		profileTab { $("#menu a", text: contains("PROFILE")) }
	}
}
