package simple.cms

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class SimpleCMSTagLib {

	def springSecurityService

	static namespace = "scms"
	
	def htmlWidget = { attributes, body ->
		def widget = attributes.widget
		if (widget.cssClass) {
			out << "<div class='${widget.cssClass}' id='${widget.widgetId}'>" << "\n"
		} else {
			out << "<div id='${widget.widgetId}'>" << "\n"
		}
		out << widget.htmlText << "\n"
		out << "</div>" << "\n"
		if (SpringSecurityUtils.ifAnyGranted("ROLE_WEB,ROLE_ADMIN")) {
			out << "<div class='scmsButtons'>" << "\n"
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"createEditorForHTMLWidget('" << widget.widgetId << "', " << widget.id << ", 'html');\">Edit</button>" << "\n"
			out << "</div>" << "\n"
		}
	}

	def menuBarWidget = { attributes, body ->
		def menuBar = SCMSMenuBar.findByWidgetId(attributes.widgetId)
		out << menuBar.render()
	}

	def photoWidget = { attributes, body ->
		def widget = attributes.widget
		def photo = widget.photo
		if (photo == null) {
			// Should not happen but if it does, create a default photo and assign it
			def serverURL = "http://mcdowellsonoran.org"
			def imagePath = "images/default"
			def gatewayBuilding = new SCMSPhoto(description: "Gateway Bulding", source: serverURL, path: imagePath, originalFileName: "Gateway Building.jpg", fileName: "Gateway Building.jpg", width: 5616, height: 3744, keywords: ["Gateway View default photo"], allKeywords: "default", artist: "Phil", copyright: "None")
			gatewayBuilding.save(failOnError: true, flush: true)
			SCMSPhoto.DEFAULT_PHOTO_ID = gatewayBuilding.id
			widget.photo = gatewayBuilding
			photo = gatewayBuilding
		}
		def cssAttributes = new StringBuffer()
		cssAttributes << "id='${widget.widgetId}Photo' "
		if (attributes.photoCssClass) {
			cssAttributes << 'class="' << attributes.photoCssClass << '" '
		}
		// Both width and height specified
		def styleAttributes = new StringBuffer()
		if (attributes.width && attributes.height) {
			def width = attributes.width as Integer
			def height = attributes.height as Integer
			styleAttributes << "width: ${width}px; height: ${height}px; "
		}
		// Only width specified
		if (attributes.width && !attributes.height) {
			def width = attributes.width as Integer
			styleAttributes << "width: ${width}px; height: ${photo.height * (width / photo.width)}px; "
		}		
		// Only height specified
		if (!attributes.width && attributes.height) {
			def height = attributes.height as Integer
			styleAttributes << "width: ${photo.width * (height / photo.height)}px; height: ${height}px; "
		}
		def backgroundImage = "${photo.fullPath()}"
		cssAttributes << "style='background-image: url(\"${backgroundImage}\"); background-size: cover; ${styleAttributes.toString()}'"
		out << "<div ${cssAttributes.toString()}>\n"
		if (SpringSecurityUtils.ifAnyGranted("ROLE_WEB,ROLE_ADMIN")) {
			out << "<button class=\"scmsButton\" type=\"button\" onClick='editPhotoWidgetPhoto(${widget.id});' style='clear: both;'>Edit Photo</button>" << "\n"
		}
		out << "</div>\n" 
		if (widget.caption == null && attributes.caption != null) {
			widget.caption = "<p>" + attributes.caption + "</p>"
		}
		if (widget.caption) {
			if (attributes.captionCssClass) {
				out << "<div class='${attributes.captionCssClass}' id='${widget.widgetId}Caption'>${widget.caption}"
			} else {
				out << "<div id='${widget.widgetId}Caption'>${widget.caption}"
			}
			out << "</div>\n"
			if (SpringSecurityUtils.ifAnyGranted("ROLE_WEB,ROLE_ADMIN")) {
				out << "<div><button class=\"scmsButton\" type=\"button\" onclick=\"createEditorForPhotoCaption('" << widget.widgetId << "Caption', " << widget.id << ", 'caption');\">Edit Caption</button>" << "</div>\n"
			}			
		}
	}
	
	def lightboxWidget = { attributes, body ->
		def widget = attributes.widget
		if (widget.cssClass) {
			out << "<div class='${widget.cssClass}' id='${widget.widgetId}'>" << "\n"
		} else {
			out << "<div id='${widget.widgetId}'>" << "\n"
		}
		showLightbox(widget, out)
		out << "</div>" << "\n"
		if (SpringSecurityUtils.ifAnyGranted("ROLE_WEB,ROLE_ADMIN")) {
			out << "<div class='scmsButtons'>" << "\n"
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"openLightboxUpdate(" << widget.id << ");\">Edit</button>" << "\n"
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"editLinkPhoto(" << widget.id << ");\">Edit Link Photo</button>" << "\n"
			out << "</div>" << "\n"
		}
	}
	
	def showLightbox(lightbox, stream) {
		stream << render(template: "/lightbox/lightboxContent", model: [lightbox: lightbox], plugin: "simple-cms")
	}
	
	def galleryWidget = { attributes, body ->
		def widget = attributes.widget
		if (widget.cssClass) {
			out << "<div class='${widget.cssClass}' id='${widget.widgetId}'>" << "\n"
		} else {
			out << "<div id='${widget.widgetId}'>" << "\n"
		}
		showGallery(widget, out)
		out << "</div>" << "\n"
		if (SpringSecurityUtils.ifAnyGranted("ROLE_WEB,ROLE_ADMIN")) {
			out << "<div class='scmsButtons'>" << "\n"
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"openGalleryUpdate(" << widget.id << ");\">Edit Gallery</button>" << "\n"
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"editGalleryTitle(" << widget.id << ", \'title${widget.widgetId}\');\">Edit Gallery Title</button>" << "\n"
			out << "</div>" << "\n"
		}
	}
	
	def showGallery(gallery, stream) {
		stream << render(template: "/gallery/galleryContent", model: [gallery: gallery], plugin: "simple-cms")
	}

}
