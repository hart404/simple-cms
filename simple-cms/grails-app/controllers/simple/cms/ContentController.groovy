package simple.cms

import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class ContentController {
	
	LinkGenerator grailsLinkGenerator

    def handleContent() { 
		def contextPath = grailsLinkGenerator.contextPath
		def baseURI = request.forwardURI
		println "Base URI ${baseURI}"
		baseURI = baseURI[contextPath.size()..-1]
		def pageURI = baseURI[('content/pages/').size()..-1]
		pageURI = pageURI.stripMargin("/")		
		println "Page URI ${pageURI}"
		def page = SCMSPage.findByLink(pageURI)
		if (page == null) {
			redirect(uri: '/error.gsp')
		} else {
			def template = page.template
			def widgets = [:]
			page.widgets.each { widget ->
				widgets[widget.widgetId] = widget
			}
			render(view: template.associatedGSP, model: widgets)
		}
	}
	
}
