package simple.cms

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class SimpleCMSTagLib {
	
	def springSecurityService
	
	static namespace = "scms"
	
	def htmlWidget = { attributes, body ->
		def widget = SCMSHTMLWidget.get(attributes.id)
		out << "<div class='${widget.cssClass}' id='${attributes.htmlId}'>" << "\n"
		out << widget.htmlText << "\n" 
		out << "</div>" << "\n"
		if (SpringSecurityUtils.ifAllGranted("ROLE_WEB")) {
			out << "<div class='scmsButtons'>" << "\n"
			out << "<button type='button' onclick='createEditor(\"" << attributes.htmlId << "\");'>Edit</button>" << "\n"
			out << "<button type='button' onclick='saveEditor(" << attributes.id << ");'>Save</button>" << "\n"
			out << "<button type='button' onclick='cancelEditor();'>Cancel</button>" << "\n"
			out << "</div>" << "\n"
		}
	}

}
