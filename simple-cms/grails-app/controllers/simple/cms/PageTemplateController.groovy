package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class PageTemplateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pageTemplateInstanceList: SCMSPageTemplate.list(params), pageTemplateInstanceTotal: SCMSPageTemplate.count()]
    }

    def create() {
        [pageTemplateInstance: new SCMSPageTemplate(params)]
    }

    def save() {
        def pageTemplateInstance = new SCMSPageTemplate(params)
        if (!pageTemplateInstance.save(flush: true)) {
            render(view: "create", model: [pageTemplateInstance: pageTemplateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), pageTemplateInstance.id])
        redirect(action: "show", id: pageTemplateInstance.id)
    }

    def show(Long id) {
        def pageTemplateInstance = SCMSPageTemplate.get(id)
        if (!pageTemplateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "list")
            return
        }

        [pageTemplateInstance: pageTemplateInstance]
    }

    def edit(Long id) {
        def pageTemplateInstance = SCMSPageTemplate.get(id)
        if (!pageTemplateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "list")
            return
        }

        [pageTemplateInstance: pageTemplateInstance]
    }

    def update(Long id, Long version) {
        def pageTemplateInstance = SCMSPageTemplate.get(id)
        if (!pageTemplateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pageTemplateInstance.version > version) {
                pageTemplateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate')] as Object[],
                          "Another user has updated this SCMSPageTemplate while you were editing")
                render(view: "edit", model: [pageTemplateInstance: pageTemplateInstance])
                return
            }
        }

        pageTemplateInstance.properties = params

        if (!pageTemplateInstance.save(flush: true)) {
            render(view: "edit", model: [pageTemplateInstance: pageTemplateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), pageTemplateInstance.id])
        redirect(action: "show", id: pageTemplateInstance.id)
    }

    def delete(Long id) {
        def pageTemplateInstance = SCMSPageTemplate.get(id)
        if (!pageTemplateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "list")
            return
        }

        try {
            pageTemplateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSPageTemplate.label', default: 'SCMSPageTemplate'), id])
            redirect(action: "show", id: id)
        }
    }
}
