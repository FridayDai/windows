package com.ratchethealth.admin

class BackupController extends BaseController{

    def backupService

    def index() {
        String token = request.session.token
        def backupMax = RatchetConstants.DEFAULT_BACKUP_SIZE
        def backup = backupService.getDBBackups(token, backupMax)
        def backupLinks = backup?.links
        render(view: '/backup/backup', model: [links: backupLinks])
    }
}
