package simple.cms

class DocumentWidgetService {
	
	def deleteDocumentWidgetDocument(documentWidget, document) {
		documentWidget.removeFromDocuments(document)
		documentWidget.save(failOnError: true)
	}
	
}
