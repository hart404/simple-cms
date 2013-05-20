package simple.cms

class SCMSDocumentWidgetCreator extends SCMSWidgetCreator {
	static constraints = {
	}
	
	SCMSWidget getWidgetInstance() {
		new SCMSDocumentWidget("widgetId": widgetId)
	}

}
