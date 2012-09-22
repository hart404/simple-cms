package simple.cms

import org.springframework.dao.DataIntegrityViolationException

class GalleryController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST", deletePhotoWidget: "POST", updateLightboxPhotoIds: "POST"]
	
	def galleryService

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
		def gallery = SCMSGalleryWidget.get(params.id)
		def photoWidget = SCMSPhotoWidget.get(params.photoWidgetId)
		galleryService.deleteGalleryPhotoWidget(gallery, photoWidget)
		render(template: "/layouts/updated")
	}
	
	def galleryPhotoWidgets() {
		def gallery = SCMSGalleryWidget.get(params.id)
		def max = params.max as Integer
		def offset = params.offset as Integer
		def last = Math.min(offset + max, gallery.photoWidgets.size())
		def photoWidgets = []
		if (!gallery.photoWidgets.isEmpty()) {
			photoWidgets = gallery.photoWidgets[offset..last - 1]
		}
		render(template: "/gallery/galleryList", model: [gallery: gallery, photoWidgets: photoWidgets, total: gallery.photoWidgets.size(), offset: offset, max: max], plugin: "simple-cms")
	}
	
	def updateGalleryPhotoIds() {
		def gallery = SCMSGalleryWidget.get(params.id)
		def photoIds = params.list('photoIds[]')
		photoIds.each { photoId ->
			def photo = SCMSPhoto.get(photoId)
			def photoWidget = new SCMSPhotoWidget(widgetId: photoId.toString(), photo: photo, caption: "Default")
			gallery.addToPhotoWidgets(photoWidget)
		}
		gallery.save(failOnError: true, flush: true)
		render(template: "/layouts/updated")
	}
	
	def updateGalleryTitle() {
		def gallery = SCMSGalleryWidget.get(params.id)
		gallery.title = params.title
		gallery.save(failOnError: true)
		render(template: "/layouts/updated")
	}
	
}
