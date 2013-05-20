package simple.cms

class WidgetController {
	
	def widgetService
	
	def updateHtmlWidget() {
		widgetService.updateHTMLWidget(params.id, params.htmlText)
		render(contentType: "text/json") {
			["result": "success"]
		}
	}
	
	def updatePhotoWidgetPhoto() {
		widgetService.updatePhotoWidgetPhoto(params.id, params.photoId)
		render(contentType: "text/json") {
			["result": "success"]
		}
	}

	def updatePhotoWidgetCaption() {
		widgetService.updatePhotoWidgetCaption(params.id, params.caption)
		render(contentType: "text/json") {
			["result": "success"]
		}
	}
	
}
