package criptografia

class UrlMappings {

    static mappings = {
        group ("/criptografia") {
            get "/all"(controller: 'transfer', action: 'list')
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
