package com.xplusz.ratchet

class Tool {
	String id
	String title
	String description
	String type
	String lastUpdated

	def Tool(id, title, description, type, lastUpdated) {
		this.id = id
		this.title = title
		this.description = description
		this.type = type
		this.lastUpdated = lastUpdated
	}
}
