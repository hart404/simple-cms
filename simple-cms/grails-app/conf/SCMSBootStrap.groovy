import simple.cms.SCMSHTMLWidgetCreator
import simple.cms.SCMSPageTemplate
import simple.cms.SCMSPhoto
import simple.cms.SCMSPhotoWidgetCreator

class SCMSBootStrap {
	
	static final String STANDARD_DESCRIPTION = """
Standard internal page. Has one text area that floats to the left and two photos with captions
that float to the right.
"""
	
	def init = { servletContext ->
		createDefaultPhoto()
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
	
	def createDefaultPhoto() {
		def serverURL = "http://newmscwebsite.cloudfoundry.com"
		def adSpacePath = "images/default"
		def gatewayBuilding = new SCMSPhoto(description: "Gateway Bulding", source: serverURL, path: adSpacePath, originalFileName: "Gateway Building.jpg", fileName: "Gateway Building.jpg", width: 5616, height: 3744, keywords: ["Gateway View default photo"], allKeywords: "default", artist: "Phil", copyright: "None")
		gatewayBuilding.save(failOnError: true, flush: true)
		SCMSPhoto.DEFAULT_PHOTO_ID = gatewayBuilding.id
	}

}
