class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        // Root
        "/"(controller: "home", action: "index")

        // Account
        "/login"(controller: "authentication", action: "login")
        "/logout"(controller: "authentication", action: 'logout')

        // Client
        "/clients"(controller: "clients") {
            action = [GET: "index", POST: "addClient"]
        }
        "/clients/$id/$clientName?"(controller: "clients") {
            action = [GET: "clientDetail", POST: "editClient"]
        }
        "/clients/$clientId/agents"(controller: "clients") {
            action = [POST: "addAgent"]
        }
        "/clients/$clientId/agents/$agentId"(controller: "clients") {
            action = [POST: "editAgent", DELETE: "deleteAgent"]
        }

        // Treatment
        "/clients/$clientId/treatments"(controller: "treatments") {
            action = [POST: "addTreatment"]
        }

        "/clients/$clientId/treatments/$treatmentId/$treatmentName?"(controller: "treatments") {
            action = [GET: "treatmentDetail", POST: "editTreatment", DELETE: "closeTreatment"]
        }

        "/clients/$clientId/treatments/$treatmentId/tools"(controller: "treatments") {
            action = [GET: "getTools"]
        }

        "/clients/$clientId/treatments/$treatmentId/tasks"(controller: "treatments") {
            action = [GET: "getTasks"]
        }

        // Account
        "/accounts"(controller: "accounts", action: "index")

        // Error
        "500"(view: '/error/error')
    }
}
