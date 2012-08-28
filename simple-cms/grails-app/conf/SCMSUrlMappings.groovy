class SCMSUrlMappings {

	static mappings = {		
		"/content/pages/**"(controller: "content", action: "handleContent")
		"500"(view:'/error')
	}
}
