class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "login", action: "login")

//        "/"(view:"/pages/login")
//        "500"(view:'/pages/error')
	}
}
