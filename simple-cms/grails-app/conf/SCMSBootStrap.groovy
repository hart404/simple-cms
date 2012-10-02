import simple.cms.SCMSGalleryWidgetCreator
import simple.cms.SCMSHTMLWidgetCreator
import simple.cms.SCMSLightboxWidgetCreator
import simple.cms.SCMSMenuBar
import simple.cms.SCMSPageTemplate
import simple.cms.SCMSPhoto
import simple.cms.SCMSPhotoWidgetCreator

class SCMSBootStrap {
	
	def photoService
	
	static final String STANDARD_DESCRIPTION = """
Standard internal page. Has one text area that floats to the left and two photos with captions
that float to the right.
"""
	static final String LIGHTBOX_DESCRIPTION = """
Same as standard but with an additional lightbox widget.
"""
	
	static final String GALLERY_DESCRIPTION = """
Intended for the 'People' page. Contains an HTML widget, 2 photo widgets and 2 gallery widgets.
"""

	static final String NOSIDEBAR_DESCRIPTION = """
Has no sidebar navigation and contains only one HTML widget.
"""

	def init = { servletContext ->
		createDefaultPhoto()
		createPageTemplates()
	}

	def destroy = {
	}
	
	def createPageTemplates() {
		createStandard()
		createLightbox()
		createGallery()
		createNoSidebar()
	}
	
	def createStandard() {
		def standardTemplate = SCMSPageTemplate.findByName("Standard")
		if (standardTemplate == null) {
			standardTemplate = new SCMSPageTemplate(
				name: "Standard",
				description: STANDARD_DESCRIPTION,
				associatedGSP: "standard.gsp"
			)
			standardTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
			standardTemplate.save(failOnError: true)
		} else {
			if (standardTemplate.widgetCreators.size() == 0) {
				standardTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
				standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
				standardTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
				standardTemplate.save(failOnError: true)
			}
		}	
	}
	
	def createNoSidebar() {
		def noSidebarTemplate = SCMSPageTemplate.findByName("No Sidebar Navigation")
		if (noSidebarTemplate == null) {
			noSidebarTemplate = new SCMSPageTemplate(
				name: "No Sidebar Navigation",
				description: NOSIDEBAR_DESCRIPTION,
				associatedGSP: "nosidebar.gsp"
			)
			noSidebarTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			noSidebarTemplate.save(failOnError: true)
		}
	}
	
	def createDefaultPhoto() {
		def defaultPhotos = SCMSPhoto.findAllBySource("http://new.mcdowellsonoran.org")
		println "There are ${defaultPhotos.size()} Gateway Buildings..."
		def defaultPhoto = SCMSPhoto.findByDescription("Gateway Building")
		println "Default photo: ${defaultPhoto}"
		if (defaultPhoto == null) {
			def serverURL = "http://mcdowellsonoran.org"
			def imagePath = "images/default"
			def gatewayBuilding = new SCMSPhoto(description: "Gateway Building", source: serverURL, path: imagePath, originalFileName: "Gateway Building.jpg", fileName: "Gateway Building.jpg", width: 5616, height: 3744, keywords: ["Gateway View default photo"], allKeywords: "default", artist: "Phil", copyright: "None")
			gatewayBuilding.save(failOnError: true, flush: true)
			SCMSPhoto.DEFAULT_PHOTO_ID = gatewayBuilding.id
		} else {
			SCMSPhoto.DEFAULT_PHOTO_ID = defaultPhoto.id
		}
	}
	
	def createLightbox() {
		def lightboxTemplate = SCMSPageTemplate.findByName("Lightbox")
		if (lightboxTemplate == null) {
			lightboxTemplate = new SCMSPageTemplate(
				name: "Lightbox",
				description: LIGHTBOX_DESCRIPTION,
				associatedGSP: "lightbox.gsp"
			)
			lightboxTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			lightboxTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			lightboxTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
			lightboxTemplate.addToWidgetCreators(new SCMSLightboxWidgetCreator(widgetId: "lightbox1"))
			lightboxTemplate.save(failOnError: true)
		}
	}
	
	def createGallery() {
		def galleryTemplate = SCMSPageTemplate.findByName("Gallery")
		if (galleryTemplate == null) {
			galleryTemplate = new SCMSPageTemplate(
				name: "Gallery",
				description: GALLERY_DESCRIPTION,
				associatedGSP: "gallery.gsp"
			)
			galleryTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			galleryTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			galleryTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
			galleryTemplate.addToWidgetCreators(new SCMSGalleryWidgetCreator(widgetId: "gallery1"))
			galleryTemplate.addToWidgetCreators(new SCMSGalleryWidgetCreator(widgetId: "gallery2"))
			galleryTemplate.save(failOnError: true)
		}
	}
		
}