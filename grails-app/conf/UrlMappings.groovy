class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: "index")

        "/login"(controller: "authentication", action: "login")
        "/logout"(controller: "authentication",action: 'logout')


        "500"(view:'/pages/error')
	}
}
