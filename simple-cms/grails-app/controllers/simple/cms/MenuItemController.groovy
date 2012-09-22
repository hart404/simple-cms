package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class MenuItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [menuItemInstanceList: SCMSMenuItem.list(params), menuItemInstanceTotal: SCMSMenuItem.count()]
    }

    def create() {
        [menuItemInstance: new SCMSMenuItem(params)]
    }

    def save() {
        def menuItemInstance = new SCMSMenuItem(params)
        if (!menuItemInstance.save(flush: true)) {
            render(view: "create", model: [menuItemInstance: menuItemInstance])
            return
        }
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), menuItemInstance.id])
        redirect(action: "show", id: menuItemInstance.id)
    }

    def show(Long id) {
        def menuItemInstance = SCMSMenuItem.get(id)
        if (!menuItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "list")
            return
        }

        [menuItemInstance: menuItemInstance]
    }

    def edit(Long id) {
        def menuItemInstance = SCMSMenuItem.get(id)
        if (!menuItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "list")
            return
        }

        [menuItemInstance: menuItemInstance]
    }

    def update(Long id, Long version) {
        def menuItemInstance = SCMSMenuItem.get(id)
        if (!menuItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (menuItemInstance.version > version) {
                menuItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem')] as Object[],
                          "Another user has updated this SCMSMenuItem while you were editing")
                render(view: "edit", model: [menuItemInstance: menuItemInstance])
                return
            }
        }

        menuItemInstance.properties = params

        if (!menuItemInstance.save(flush: true)) {
            render(view: "edit", model: [menuItemInstance: menuItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), menuItemInstance.id])
        redirect(action: "show", id: menuItemInstance.id)
    }

    def delete(Long id) {
        def menuItemInstance = SCMSMenuItem.get(id)
        if (!menuItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "list")
            return
        }

        try {
            menuItemInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSMenuItem.label', default: 'SCMSMenuItem'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def updateMenuItem() {
		def menuItem = SCMSMenuItem.get(params.id)
		menuItem.title = params.title
		menuItem.link = params.link
		// If roles is not passed back as a parameter, clear the roles
		def roles = params.list('roles[]')
		if (!roles) {
			menuItem.roles.clear()
			menuItem.roles = []
		} else {
			menuItem.roles = roles
		}
		menuItem.save(failOnError: true)
		def menu = SCMSMenu.get(params.menuId)
		assert menu != null
		createMenuLink(menu, menuItem.canonicalLink())
		render(template: '/layouts/updated')
	}
	
	def createMenuLink(menu, link) {
		def menuLink = SCMSMenuLink.findByLink(link)
		if (menuLink == null) {
			menuLink = new SCMSMenuLink(menu: menu, link: link)
			menuLink.save(failOnError: true, flush: true)
		} else {
			menuLink.menu = menu
			menuLink.save(failOnError: true, flush: true)
		}
	} 
	
}
