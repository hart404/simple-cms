package simple.cms

class MenuService {
	
	def removeMenu(params) {
		def menu = SCMSMenu.get(params.id)
		def subMenu = SCMSMenu.get(params.menuId)
		menu.removeFromMenuItems(subMenu)
		menu.save(failOnError: true, flush: true)
	}
	
	def removeMenuItem(params) {
		def menu = SCMSMenu.get(params.id)
		def menuItem = SCMSMenuItem.get(params.menuId)
		menu.removeFromMenuItems(menuItem)
		menu.save(failOnError: true, flush: true)
	}
	
	def removeMenu(menuBarId, menuId) {
		def menuBar = SCMSMenuBar.get(menuBarId)
		def menu = SCMSMenu.get(menuId)
		menuBar.removeFromMenus(menu)
		menuBar.save(failOnError: true, flush: true)
	}

}
