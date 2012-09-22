package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class MenuController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]
	
	def menuService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [menuInstanceList: SCMSMenu.list(params), menuInstanceTotal: SCMSMenu.count()]
    }

    def create() {
        [menuInstance: new SCMSMenu(params)]
    }

    def save() {
        def menuInstance = new SCMSMenu(params)
        if (!menuInstance.save(flush: true)) {
            render(view: "create", model: [menuInstance: menuInstance])
            return
        }
		createMenuLink(menu: menuInstance, link: menuInstance.canonicalLink())

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), menuInstance.id])
        redirect(action: "show", id: menuInstance.id)
    }

    def show(Long id) {
        def menuInstance = SCMSMenu.get(id)
        if (!menuInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "list")
            return
        }

        [menuInstance: menuInstance]
    }

    def edit(Long id) {
        def menuInstance = SCMSMenu.get(id)
        if (!menuInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "list")
            return
        }

        [menuInstance: menuInstance]
    }

    def update(Long id, Long version) {
        def menuInstance = SCMSMenu.get(id)
        if (!menuInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (menuInstance.version > version) {
                menuInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSMenu.label', default: 'SCMSMenu')] as Object[],
                          "Another user has updated this SCMSMenu while you were editing")
                render(view: "edit", model: [menuInstance: menuInstance])
                return
            }
        }

        menuInstance.properties = params

        if (!menuInstance.save(flush: true)) {
            render(view: "edit", model: [menuInstance: menuInstance])
            return
        }
		createMenuLink(menu: menuInstance, link: menuInstance.canonicalLink())
		
        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), menuInstance.id])
        redirect(action: "show", id: menuInstance.id)
    }

    def delete(Long id) {
        def menuInstance = SCMSMenu.get(id)
        if (!menuInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "list")
            return
        }

        try {
            menuInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSMenu.label', default: 'SCMSMenu'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def updateMenu() {
		def menu = SCMSMenu.get(params.id)
		menu.title = params.title
		menu.link = params.link
		// If roles is not passed back as a parameter, clear the roles
		def roles = params.list('roles[]')
		if (!roles) {
			menu.roles.clear()
			menu.roles = []
		} else {
			menu.roles = roles
		}
		menu.save(failOnError: true)
		createMenuLink(menu, menu.canonicalLink())
		render(template: '/layouts/updated')
	}
	
	def createMenuLink(menu, link) {
		def menuLink = SCMSMenuLink.findByLink(link)
		if (menuLink == null) {
			menuLink = new SCMSMenuLink(menu: menu, link: link)
			menuLink.save(failOnError: true)
		}
	}
	
	def addSubMenu() {
		def menu = SCMSMenu.get(params.id)
		def menuNew = new SCMSMenu()
		menuNew.title = params.title
		menuNew.link = params.link
		def roles = params.list('roles[]')
		if (roles) {
			menuNew.roles = roles
		}
		menuNew.save(failOnError: true, flush: true)
		menu.addToMenuItems(menuNew)
		menu.save(failOnError: true, flush: true)
		createMenuLink(menuNew, menuNew.canonicalLink())
		render(template: '/layouts/updated')
	}

	def addMenuItem() {
		def menu = SCMSMenu.get(params.id)
		def menuItemNew = new SCMSMenuItem()
		menuItemNew.title = params.title
		menuItemNew.link = params.link
		def roles = params.list('roles[]')
		if (roles) {
			menuItemNew.roles = roles
		}
		menu.addToMenuItems(menuItemNew)
		menu.save(failOnError: true)
		createMenuLink(menu, menuItemNew.canonicalLink())
		render(template: '/layouts/updated')
	}
	
	def removeMenu() {
		menuService.removeMenu(params)
		render(template: '/layouts/updated')
	}

	def removeMenuItem() {
		menuService.removeMenuItem(params)
		render(template: '/layouts/updated')
	}

}
