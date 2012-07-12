class SCMSUrlMappings {

	static mappings = {		
		"/content/**"(controller: "content", action: "handleContent")
		"500"(view:'/error')
	}
}
