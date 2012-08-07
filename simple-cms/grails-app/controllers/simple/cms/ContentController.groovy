package simple.cms

class ContentController {

    def handleContent() { 
		def baseURI = request.forwardURI
		def pageURI = extractURI(baseURI)
		def page = SCMSPage.findByURI(pageURI)
		if (page == null) {
			redirect(uri: '/error')
		}
		def template = page.template
		def widgets = [:]
		page.widgets.each { widget ->
			widgets.widgetId = widget
		}
		render(template.associatedGSP, model: widgets)
	}
	
	String extractURI(baseURI) {
		def parts = baseURI.split('/')

		def newURIList = parts[-(parts.size() - 3)..-1]
		def newURI = ""
		newURIList.each { part ->
			newURI += '/' + part
		}
		newURI
	}
}
