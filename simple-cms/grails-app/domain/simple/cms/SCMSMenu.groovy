package simple.cms

class SCMSMenu extends SCMSMenuItem {
	
	List<SCMSMenuItem> menuItems = []
	static hasMany = [
		menuItems: SCMSMenuItem
	]
	
	static constraints = {
		menuItems(nullable: true)
	}
	
	static mapping = {
		menuItems(cascade: "all, delete-orphan", lazy: false, cache: true)
		cache: true
	}
	
	def render(level, lastItem) {
		StringBuffer result = new StringBuffer()
		if (menuIsAllowed()) {
			result << "<li><a href=\"${fullLink()}\"><span>$title</span></a>\n"
			if (!menuItems.isEmpty()) {
				renderMenuItems(result, level, lastItem)
			}
			result << "</li>\n"
		}
		result.toString()
	}
	
	def renderMenuItems(StringBuffer buffer, level, lastItem) {
		if (level == 1) {
			buffer << "<div style='position: relative; '>\n"
		}
		if (level == 2 && lastItem) {
			buffer << "<ul class='sub_menu, lastItem'>\n"
		} else {
			buffer << "<ul class='sub_menu, notLastItem'>\n"
		}
		menuItems.each { menuItem ->
			buffer << menuItem.render(level + 1, lastItem)
		}
		buffer << "</ul>\n"
		if (level == 1) {			
			buffer << "</div>\n"
		}
	}

	def canHaveItemsAdded() {
		true
	}
	
	def convert() {
		if (link != DEFAULT_LINK) {
			def menuLink = SCMSMenuLink.findByLink(canonicalLink())
			if (menuLink == null) {
				menuLink = new SCMSMenuLink(menu: this, link: canonicalLink())
				menuLink.save(failOnError: true)
			}
		}
		menuItems.each { SCMSMenuItem menuItem ->
			if (menuItem.canHaveItemsAdded()) {
				menuItem.convert()
			} else {
				if (menuItem.link != DEFAULT_LINK) {
					def menuItemLink = SCMSMenuLink.findByLink(menuItem.canonicalLink())
					if (menuItemLink == null) {
						menuItemLink = new SCMSMenuLink(menu: this, link: menuItem.canonicalLink())
						menuItemLink.save(failOnError: true)
					}
				}
			}
		}
	}
	
}
