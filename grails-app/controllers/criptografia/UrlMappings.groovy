package criptografia

class UrlMappings {

    static mappings = {

        get "/all" (controller: 'transfer', action: 'list')
        post "/create" (controller: 'transfer', action: 'create')


        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
