import com.mashape.unirest.http.Unirest

class BootStrap {

    def grailsApplication
    def init = { servletContext ->
        Unirest.setDefaultHeader("X-App-Type", "admin")
        Unirest.setDefaultHeader("X-Anonymous-Token", grailsApplication.config.ratchet.api.anonymous.token)
    }
    def destroy = {
    }
}
