import simple.cms.SCMSHTMLWidgetCreator
import simple.cms.SCMSPageTemplate
import simple.cms.SCMSPhotoWidgetCreator

class SCMSBootStrap {
	
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
		def standardTemplate = new SCMSPageTemplate(
			name: "Standard",
			description: STANDARD_DESCRIPTION,
			associatedGSP: "standard.gsp"
		)
		standardTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
		standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
		standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
		standardTemplate.save(failOnError: true)
	}


}
