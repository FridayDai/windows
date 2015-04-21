import com.mashape.unirest.http.Unirest

class BootStrap {

    def init = { servletContext ->
        Unirest.setDefaultHeader("X-App-Type", "admin")
    }
    def destroy = {
    }
}
