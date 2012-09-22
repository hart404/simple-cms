package simple.cms

class WidgetService {

    def updateHTMLWidget(id, htmlText) {
		def htmlWidget = SCMSHTMLWidget.get(id)
		htmlWidget.htmlText = htmlText
		htmlWidget.save(failOnError: true)
    }
	
	def updatePhotoWidgetPhoto(id, photoId) {
		def photoWidget = SCMSPhotoWidget.get(id)
		def photo = SCMSPhoto.get(photoId)
		photoWidget.photo = photo
		photoWidget.save(failOnError: true)
	}
	
	def updatePhotoWidgetCaption(id, caption) {
		def photoWidget = SCMSPhotoWidget.get(id)
		photoWidget.caption = caption
		photoWidget.save(failOnError: true)
	}
}
