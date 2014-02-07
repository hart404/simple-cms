import simple.cms.SCMSDocumentWidgetCreator
import simple.cms.SCMSGalleryWidgetCreator
import simple.cms.SCMSHTMLWidgetCreator
import simple.cms.SCMSLightboxWidgetCreator
import simple.cms.SCMSPageTemplate
import simple.cms.SCMSPhoto
import simple.cms.SCMSPhotoWidgetCreator

class SCMSBootStrap {
	
	def photoService
	
	static final String STANDARD_DESCRIPTION = """
Standard internal page. Has one text area that floats to the left and two photos with captions
that float to the right.
"""
	static final String STANDARD_1_PHOTO_DESCRIPTION = """
Standard internal page, one photo. Has one text area that floats to the left and one photo with caption
that floats to the right.
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

	static final String PLAIN_DESCRIPTION = """
Contains only one HTML widget.
"""

	static final String DOCUMENT_DESCRIPTION = """
Contains only one HTML widget and a Document widget.
"""

	static final String DOCUMENT_PHOTO_DESCRIPTION = """
Contains only one HTML widget, a Document widget and two Photo widgets.
"""

	static final String DOCUMENT_2_PHOTO_DESCRIPTION = """
Contains only one HTML widget, two Document widgets and two Photo widgets.
"""
	def init = { servletContext ->
		createDefaultPhoto()
		createPageTemplates()
	}

	def destroy = {
	}
	
	def createPageTemplates() {
		createStandard()
		createStandardOnePhoto()
		createLightbox()
		createGallery()
		createNoSidebar()
		createPlain()
		createDocument()
		createDocumentPhoto()
		createDocument2Photo()
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
	
	def createStandardOnePhoto() {
		def standardTemplateOnePhoto = SCMSPageTemplate.findByName("Standard One Photo")
		if (standardTemplateOnePhoto == null) {
			standardTemplateOnePhoto = new SCMSPageTemplate(
				name: "Standard One Photo",
				description: STANDARD_1_PHOTO_DESCRIPTION,
				associatedGSP: "standard1Photo.gsp"
			)
			standardTemplateOnePhoto.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			standardTemplateOnePhoto.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			standardTemplateOnePhoto.save(failOnError: true)
		} else {
			if (standardTemplateOnePhoto.widgetCreators.size() == 0) {
				standardTemplateOnePhoto.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
				standardTemplateOnePhoto.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
				standardTemplateOnePhoto.save(failOnError: true)
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
		def defaultPhotos = SCMSPhoto.findAllBySource("http://mcdowellsonoran.org")
		def defaultPhoto = SCMSPhoto.findByDescription("Gateway Building")
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
	
	def createPlain() {
		def plainTemplate = SCMSPageTemplate.findByName("Plain")
		if (plainTemplate == null) {
			plainTemplate = new SCMSPageTemplate(
				name: "Plain",
				description: PLAIN_DESCRIPTION,
				associatedGSP: "plain.gsp"
			)
			plainTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			plainTemplate.save(failOnError: true)
		}
	}
		
	def createDocument() {
		def documentTemplate = SCMSPageTemplate.findByName("Document")
		if (documentTemplate == null) {
			documentTemplate = new SCMSPageTemplate(
				name: "Document",
				description: DOCUMENT_DESCRIPTION,
				associatedGSP: "document.gsp"
			)
			documentTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			documentTemplate.addToWidgetCreators(new SCMSDocumentWidgetCreator(widgetId: "document1"))
			documentTemplate.save(failOnError: true)
		}
	}
	
	def createDocumentPhoto() {
		def documentPhotoTemplate = SCMSPageTemplate.findByName("Document Photo")
		if (documentPhotoTemplate == null) {
			documentPhotoTemplate = new SCMSPageTemplate(
				name: "Document Photo",
				description: DOCUMENT_PHOTO_DESCRIPTION,
				associatedGSP: "documentPhoto.gsp"
			)
			documentPhotoTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSDocumentWidgetCreator(widgetId: "document1"))
			documentPhotoTemplate.save(failOnError: true)
		}
	}
	
	def createDocument2Photo() {
		def documentPhotoTemplate = SCMSPageTemplate.findByName("Document 2 Photo")
		if (documentPhotoTemplate == null) {
			documentPhotoTemplate = new SCMSPageTemplate(
				name: "Document 2 Photo",
				description: DOCUMENT_2_PHOTO_DESCRIPTION,
				associatedGSP: "document2Photo.gsp"
			)
			documentPhotoTemplate.addToWidgetCreators(new SCMSHTMLWidgetCreator(widgetId: "html1"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo1"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSPhotoWidgetCreator(widgetId: "photo2"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSDocumentWidgetCreator(widgetId: "document1"))
			documentPhotoTemplate.addToWidgetCreators(new SCMSDocumentWidgetCreator(widgetId: "document2"))
			documentPhotoTemplate.save(failOnError: true)
		}
	}
	
}
