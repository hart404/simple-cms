package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class PageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pageInstanceList: SCMSPage.list(params), pageInstanceTotal: SCMSPage.count()]
    }

    def create() {
		def page = new SCMSPage(params)
        [pageInstance: page]
    }

    def save() {
        def pageInstance = new SCMSPage(params)
		pageInstance.generateWidgets()
		pageInstance.link = params.link
        if (!pageInstance.save(flush: true)) {
            render(view: "create", model: [pageInstance: pageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), pageInstance.id])
        redirect(action: "show", id: pageInstance.id)
    }

    def show(Long id) {
        def pageInstance = SCMSPage.get(id)
        if (!pageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "list")
            return
        }

        [pageInstance: pageInstance]
    }

    def edit(Long id) {
        def pageInstance = SCMSPage.get(id)
        if (!pageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "list")
            return
        }

        [pageInstance: pageInstance]
    }

    def update(Long id, Long version) {
        def pageInstance = SCMSPage.get(id)
        if (!pageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pageInstance.version > version) {
                pageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSPage.label', default: 'SCMSPage')] as Object[],
                          "Another user has updated this SCMSPage while you were editing")
                render(view: "edit", model: [pageInstance: pageInstance])
                return
            }
        }

        pageInstance.properties = params
		// Ensures that any necessary widgets are created if the template is changed
		pageInstance.generateWidgets()

        if (!pageInstance.save(flush: true)) {
            render(view: "edit", model: [pageInstance: pageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), pageInstance.id])
        redirect(action: "show", id: pageInstance.id)
    }

    def delete(Long id) {
        def pageInstance = SCMSPage.get(id)
        if (!pageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "list")
            return
        }
		def menuLink = SCMSMenuLink.findByLink(pageInstance.link)
		if (menuLink != null) {
			menuLink.delete(flush:true)
		}
        try {
            pageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSPage.label', default: 'SCMSPage'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def rebuildPage(Long id) {
		def pageInstance = SCMSPage.get(id)
		def template = pageInstance.template
		if (pageInstance.widgets.size() != template.widgetCreators.size()) {
			log.error("Page with link: ${pageInstance.link} has ${pageInstance.widgets.size()} widgets and should have ${template.widgetCreators.size()}")
			pageInstance.widgets.clear()
			pageInstance.generateWidgets()
			render(template: '/layouts/rebuilt')
		} else {
			render(template: '/layouts/unnecessary') 
		}
	}
}
