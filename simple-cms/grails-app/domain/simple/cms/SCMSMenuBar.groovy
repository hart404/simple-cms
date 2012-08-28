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
		menus(cascade: "all, delete-orphan")
	}
	
	def render() {
		StringBuffer result = new StringBuffer()
		result << "<ul class='dropdown'>\n"
		menus.each { menu ->
			result << menu.render(1)
		}
		result << "</ul>\n"
		return result.toString()
	}

}
