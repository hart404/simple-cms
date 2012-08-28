package simple.cms

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.web.mapping.LinkGenerator


class SCMSMenuItem {
	
	LinkGenerator grailsLinkGenerator
	
	static List<String> possibleRoles() {
		["ROLE_ADMIN", "ROLE_GUEST","ROLE_USER","ROLE_STEWARD", "ROLE_LEADER", "ROLE_BOARD", "ROLE_STAFF", "ROLE_WEB"]
	}
	
	String title
	String link = '#'			// Default link
	
	static transients = ['grailsLinkGenerator']
	
	List<String> roles = []		// Can't make a direct reference to SecRole - TODO
	static hasMany = [
		roles: String
	]
	
	static constraints = {
		title(nullable: false)
		link(nullable: false)
		roles(size: 0..20)
	} 
	
	boolean menuIsAllowed() {
		if (roles.isEmpty()) return true
		SpringSecurityUtils.ifAnyGranted(roleList())
	}
	
	def render(level) {
		if (menuIsAllowed()) {
			return "<li><a href=\"${fullLink()}\"><span>$title</span></a></li>\n"
		} else {
			return ""
		}		
	}
	
	def roleList() {
		roles.join(",")
	}
	
	def fullLink() {
		if (link.startsWith('/')) {
			return grailsLinkGenerator.contextPath + link
		} else {
			return grailsLinkGenerator.contextPath + '/' + link
		}
	}
	
	def canHaveItemsAdded() {
		false
	}
	
}
