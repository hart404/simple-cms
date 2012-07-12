import simple.cms.SCMSHTMLWidgetCreator
import simple.cms.SCMSPageTemplate
import simple.cms.SCMSPhotoWidgetCreator

class Bootstrap {
	
	static final String STANDARD_DESCRIPTION = """
Standard internal page. Has one text area that floats to the left and two photos with captions
that float to the right.
"""
	
	def init = { servletContext ->
		createPageTemplates()
	}

	def destroy = {
	}
	
	def createPageTemplates() {
		createStandard()
	}
	
	def createStandard() {
		def standard = new SCMSPageTemplate(
			name: "Standard",
			description: STANDARD_DESCRIPTION,
			associatedGSP: "standard.gsp"
		)
		standard.addToWidgetCreators(new SCMSHTMLWidgetCreator())
		standard.addToWidgetCreators(new SCMSPhotoWidgetCreator())
		standard.addToWidgetCreators(new SCMSPhotoWidgetCreator())
		standard.save(failOnError: true)
	}


}
