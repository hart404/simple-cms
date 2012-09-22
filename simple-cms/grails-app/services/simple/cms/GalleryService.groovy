package simple.cms

class GalleryService {
	
	def deleteGalleryPhotoWidget(gallery, photoWidget) {
		gallery.removeFromPhotoWidgets(photoWidget)
		gallery.save(failOnError: true, flush: true)
	}
	
}
