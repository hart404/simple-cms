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
			out << "<button class=\"scmsButton\" type=\"button\" onclick=\"createEditor('" << widget.widgetId << "', " << widget.id << ", 'html');\">Edit</button>" << "\n"
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
			out << "<button class=\"scmsButton\" type=\"button\" onClick='editPhoto(${widget.id});' style='clear: both;'>Edit Photo</button>" << "\n"
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
				out << "<div><button class=\"scmsButton\" type=\"button\" onclick=\"createEditor('" << widget.widgetId << "Caption', " << widget.id << ", 'caption');\">Edit Caption</button>" << "</div>\n"
				out << g.render(template: "/widget/updateHtmlWidgetDialog", plugin: "simple-cms") << "\n"
			}			
		}
	}
}
