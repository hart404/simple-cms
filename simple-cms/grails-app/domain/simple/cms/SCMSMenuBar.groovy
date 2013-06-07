package simple.cms

class SCMSMenuBar {
	
	String widgetId

	List<SCMSMenu> menus = []	
	static hasMany = [
		menus: SCMSMenu
	]
	
	static constraints = {
		widgetId(nullable: false, unique: true)
		menus(nullable: false)
	}
	
	static mapping = {
		menus(lazy: false, cache: true)
		cache: true
	}
	
	def render() {
		StringBuffer result = new StringBuffer()
		result << "<ul class='dropdown'>\n"
		def visibleMenus = menus.findAll { menu -> menu.menuIsAllowed()}
		visibleMenus.eachWithIndex { menu, index ->
			if (index != visibleMenus.size() - 1) {
				result << menu.render(1, false)
			} else {
				result << menu.render(1, true)
			}			
		}
		result << "</ul>\n"
		return result.toString()
	}
	
	def migrateMenus() {
		menus*.convert()
	}
	
}
