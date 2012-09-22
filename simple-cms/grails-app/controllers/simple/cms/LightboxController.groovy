package simple.cms

import groovy.json.JsonSlurper

import org.springframework.dao.DataIntegrityViolationException

class LightboxController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", deletePhotoWidget: "POST", updateLightboxPhotoIds: "POST"]
	
	def lightboxService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [SCMSLightboxWidgetInstanceList: SCMSLightboxWidget.list(params), SCMSLightboxWidgetInstanceTotal: SCMSLightboxWidget.count()]
    }

    def create() {
        [SCMSLightboxWidgetInstance: new SCMSLightboxWidget(params)]
    }

    def save() {
        def SCMSLightboxWidgetInstance = new SCMSLightboxWidget(params)
        if (!SCMSLightboxWidgetInstance.save(flush: true)) {
            render(view: "create", model: [SCMSLightboxWidgetInstance: SCMSLightboxWidgetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), SCMSLightboxWidgetInstance.id])
        redirect(action: "show", id: SCMSLightboxWidgetInstance.id)
    }

    def show(Long id) {
        def SCMSLightboxWidgetInstance = SCMSLightboxWidget.get(id)
        if (!SCMSLightboxWidgetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "list")
            return
        }

        [SCMSLightboxWidgetInstance: SCMSLightboxWidgetInstance]
    }

    def edit(Long id) {
        def SCMSLightboxWidgetInstance = SCMSLightboxWidget.get(id)
        if (!SCMSLightboxWidgetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "list")
            return
        }

        [SCMSLightboxWidgetInstance: SCMSLightboxWidgetInstance]
    }

    def update(Long id, Long version) {
        def SCMSLightboxWidgetInstance = SCMSLightboxWidget.get(id)
        if (!SCMSLightboxWidgetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (SCMSLightboxWidgetInstance.version > version) {
                SCMSLightboxWidgetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget')] as Object[],
                          "Another user has updated this SCMSLightboxWidget while you were editing")
                render(view: "edit", model: [SCMSLightboxWidgetInstance: SCMSLightboxWidgetInstance])
                return
            }
        }

        SCMSLightboxWidgetInstance.properties = params

        if (!SCMSLightboxWidgetInstance.save(flush: true)) {
            render(view: "edit", model: [SCMSLightboxWidgetInstance: SCMSLightboxWidgetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), SCMSLightboxWidgetInstance.id])
        redirect(action: "show", id: SCMSLightboxWidgetInstance.id)
    }

    def delete(Long id) {
        def SCMSLightboxWidgetInstance = SCMSLightboxWidget.get(id)
        if (!SCMSLightboxWidgetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "list")
            return
        }

        try {
            SCMSLightboxWidgetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SCMSLightboxWidget.label', default: 'SCMSLightboxWidget'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def deletePhotoWidget() {
		def lightbox = SCMSLightboxWidget.get(params.id)
		def photoWidget = SCMSPhotoWidget.get(params.photoWidgetId)
		lightboxService.deleteLightboxPhotoWidget(lightbox, photoWidget)
		render(template: "/layouts/updated")
	}
	
	def lightboxPhotoWidgets() {
		def lightbox = SCMSLightboxWidget.get(params.id)
		def max = params.max as Integer
		def offset = params.offset as Integer
		def last = Math.min(offset + max, lightbox.photoWidgets.size())
		def photoWidgets = []
		if (!lightbox.photoWidgets.isEmpty()) {
			photoWidgets = lightbox.photoWidgets[offset..last - 1]
		}
		render(template: "/lightbox/lightboxList", model: [lightbox: lightbox, photoWidgets: photoWidgets, total: lightbox.photoWidgets.size(), offset: offset, max: max], plugin: "simple-cms")
	}
	
	def updateLightboxPhotoIds() {
		def lightbox = SCMSLightboxWidget.get(params.id)
		def photoIds = params.list('photoIds[]')
		photoIds.each { photoId ->
			def photo = SCMSPhoto.get(photoId)
			def photoWidget = new SCMSPhotoWidget(widgetId: photoId.toString(), photo: photo, caption: "Default")
			lightbox.addToPhotoWidgets(photoWidget)
		}
		lightbox.save(failOnError: true, flush: true)
		render(template: "/layouts/updated")
	}
	
	def updateLinkPhoto() {
		def lightbox = SCMSLightboxWidget.get(params.id)
		def photo = SCMSPhoto.get(params.photoId)
		lightbox.linkPhoto = photo
		lightbox.save(failOnError: true, flush: true)
		render(template: "/layouts/updated")
	}
}
