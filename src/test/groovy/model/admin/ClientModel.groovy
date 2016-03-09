package model.admin

class ClientModel {
    def PRIMARY_COLOR_HEX = "#9EFF9E"
    def LOGO_RELATIVE_PATH = "src/test/resources/ps.png"
    def FAVICON_RELATIVE_PATH = "src/test/resources/ps-favicon.png"

    long id
    String clientName
    String subDomain
    String patientPortalName
    String primaryColor = PRIMARY_COLOR_HEX
    String logoPath = LOGO_RELATIVE_PATH
    String faviconPath = FAVICON_RELATIVE_PATH
}
