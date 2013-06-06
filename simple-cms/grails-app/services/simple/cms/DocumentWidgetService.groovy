package simple.cms

class DocumentWidgetService {
	
	def deleteDocumentWidgetDocument(documentWidget, document) {
		documentWidget.removeFromDocuments(document)
		documentWidget.save(failOnError: true)
	}
	
	SCMSDocumentWidget updateDocumentWidgetIds(documentWidgetId, documentIds) {
		def documentWidget = SCMSDocumentWidget.get(documentWidgetId)
		documentIds.each { documentId ->
			def document = SCMSDocument.get(documentId)
			documentWidget.addToDocuments(document)
		}
		documentWidget.save(failOnError: true)
		println "Documents size: ${documentWidget.documents.size()}"
		documentWidget
	}
	
}
