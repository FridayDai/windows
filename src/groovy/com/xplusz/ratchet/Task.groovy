package com.xplusz.ratchet

class Task {
	String id
	String title
	String description
	String type
	String sendTime
	String notify
	String lastUpdated

	def Task(id, title, description, type, sendTime, notify, lastUpdated) {
		this.id = id
		this.title = title
		this.description = description
		this.type = type
		this.sendTime = sendTime
		this.notify = notify
		this.lastUpdated = lastUpdated
	}
}
