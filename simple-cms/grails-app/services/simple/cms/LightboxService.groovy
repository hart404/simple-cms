package simple.cms

class LightboxService {

	def deleteLightboxPhotoWidget(lightbox, photoWidget) {
		lightbox.removeFromPhotoWidgets(photoWidget)
		lightbox.save(failOnError: true, flush: true)
	}
	
}
