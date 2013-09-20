package simple.cms

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class ContentController {
	
	LinkGenerator grailsLinkGenerator

    def handleContent() { 
		def contextPath = grailsLinkGenerator.contextPath
		def baseURI = request.forwardURI
		baseURI = baseURI[contextPath.size()..-1]
		def pageURI = baseURI[('content/pages/').size()..-1]
		pageURI = pageURI.stripMargin("/")		
		def page = SCMSPage.findByLink(pageURI)
		if (!baseURI.startsWith("/")) {
			baseURI = "/" + baseURI
		}
		if (page == null) {
			redirect(uri: '/error', params: [page: baseURI])
			log.error("Someone tried to access a missing page: ${baseURI}")
		} else {
			def template = page.template
			def widgets = [:]
			page.widgets.each { widget ->
				widgets[widget.widgetId] = widget
			}
			def menuLink = SCMSMenuLink.findByLink(baseURI)
			if (menuLink == null) {
				log.error("Disaster! No menuLink found for: ${baseURI}")
			} else {
				widgets.menu = menuLink.menu
			}
			widgets.page = page
			SCMSStaticPage.currentPage = page
			widgets.link = baseURI.stripMargin("/")
			render(view: template.associatedGSP, model: widgets)
		}
	}
	
	def handlePage() {
		redirect(uri: "/content/pages/${params.link}")
	}
	
}
