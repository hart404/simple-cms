package simple.cms

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.web.mapping.LinkGenerator


class SCMSMenuItem {
	
	LinkGenerator grailsLinkGenerator
	
	static String DEFAULT_LINK = "#"
	
	static List<String> possibleRoles() {
		["ROLE_ADMIN", "ROLE_GUEST","ROLE_USER","ROLE_STEWARD", "ROLE_LEADER", "ROLE_BOARD", "ROLE_STAFF", "ROLE_WEB"]
	}
	
	static transients = ['grailsLinkGenerator']
	
	String title
	String link = DEFAULT_LINK			// Default link
	
	List<String> roles = []		// Can't make a direct reference to SecRole - TODO
	static hasMany = [
		roles: String
	]
	
	static constraints = {
		title(nullable: false)
		link(nullable: false)
		roles(size: 0..20)
	} 
	
	static mapping = {
		roles(lazy: false, cache: true)
		cache: true
	}
	
	boolean menuIsAllowed() {
		if (roles.isEmpty()) return true
		SpringSecurityUtils.ifAnyGranted(roleList())
	}
	
	def render(level, lastItem) {
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
		grailsLinkGenerator.contextPath + canonicalLink()
	}
	
	def canHaveItemsAdded() {
		false
	}
	
	def canonicalLink() {
		if (link.startsWith("/")) {
			return link
		}
		return "/" + link
	}
	
	def convert() {
		// Do nothing for menu items
	}

}
