package com.ratchethealth.admin

class BackupController extends BaseController {

    def backupService

    def index() {
        def backupLinks = getBackup()
        render(view: '/backup/backup', model: [links: backupLinks])
    }

    def generateBackup() {
        String token = request.session.token
        def resp = backupService.generateBackup(token)
        if (resp == true) {
            def backupLinks = getBackup()
            render(view: '/backup/backup', model: [links: backupLinks])
        }
    }

    def getBackup() {
        String token = request.session.token
        def backupMax = RatchetConstants.DEFAULT_BACKUP_SIZE
        def backup = backupService.getDBBackups(token, backupMax)
        def backupLinks = backup?.links
        return backupLinks
    }
}
