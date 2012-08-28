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
		menuItems(cascade: "all, delete-orphan")
	}
	
	def render(level) {
		StringBuffer result = new StringBuffer()
		if (menuIsAllowed()) {
			result << "<li><a href=\"${fullLink()}\"><span>$title</span></a>\n"
			if (!menuItems.isEmpty()) {
				renderMenuItems(result, level)
			}
			result << "</li>\n"
		}
		result.toString()
	}
	
	def renderMenuItems(StringBuffer buffer, level) {
		if (level == 1) {
			buffer << "<div style='position: relative;'>\n"
		}
		buffer << "<ul class='sub_menu'>\n"
		menuItems.each { menuItem ->
			buffer << menuItem.render(level + 1)
		}
		buffer << "</ul>\n"
		if (level == 1) {			
			buffer << "</div>\n"
		}
	}

	def canHaveItemsAdded() {
		true
	}

}
