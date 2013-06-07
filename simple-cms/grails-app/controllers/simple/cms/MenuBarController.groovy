package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class MenuBarController {
	
	def menuService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [menuBarInstanceList: SCMSMenuBar.list(params), menuBarInstanceTotal: SCMSMenuBar.count()]
    }

    def create() {
        [menuBarInstance: new SCMSMenuBar(params)]
    }

    def save() {
        def menuBarInstance = new SCMSMenuBar(params)
        if (!menuBarInstance.save(flush: true)) {
            render(view: "create", model: [menuBarInstance: menuBarInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), menuBarInstance.id])
        redirect(action: "show", id: menuBarInstance.id)
    }

    def show(Long id) {
        def menuBarInstance = SCMSMenuBar.get(id)
        if (!menuBarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "list")
            return
        }

        [menuBarInstance: menuBarInstance]
    }

    def edit(Long id) {
        def menuBarInstance = SCMSMenuBar.get(id)
        if (!menuBarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "list")
            return
        }

        [menuBarInstance: menuBarInstance]
    }

    def update(Long id, Long version) {
        def menuBarInstance = SCMSMenuBar.get(id)
        if (!menuBarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (menuBarInstance.version > version) {
                menuBarInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar')] as Object[],
                          "Another user has updated this SCMSMenuBar while you were editing")
                render(view: "edit", model: [menuBarInstance: menuBarInstance])
                return
            }
        }

        menuBarInstance.properties = params

        if (!menuBarInstance.save(flush: true)) {
            render(view: "edit", model: [menuBarInstance: menuBarInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), menuBarInstance.id])
        redirect(action: "show", id: menuBarInstance.id)
    }

    def delete(Long id) {
        def menuBarInstance = SCMSMenuBar.get(id)
        if (!menuBarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "list")
            return
        }

        try {
            menuBarInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSMenuBar.label', default: 'SCMSMenuBar'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def addMenu() {
		def menuBar = SCMSMenuBar.get(params.id)
		def menu = new SCMSMenu()
		menu.title = params.title
		menu.link = params.link
		def roles = params.list('roles[]')
		if (roles) {
			menu.roles = roles
		}
		menu.save(failOnError: true, flush: true)
		menuBar.addToMenus(menu)
		menuBar.save(failOnError: true, flush: true)
		def menuLink = new SCMSMenuLink(menu: menu, link: menu.canonicalLink())
		menuLink.save(failOnError: true, flush: true)
		render(template: '/layouts/updated')
	}
	
	def removeMenu() {
		def menuBarId = params.menuBarId
		def menuId = params.menuId
		println "menuBarId: ${menuBarId}, menuId: ${menuId}"
		menuService.removeMenu(menuBarId, menuId)
		redirect(action: "edit", id: menuBarId)
	}

}
