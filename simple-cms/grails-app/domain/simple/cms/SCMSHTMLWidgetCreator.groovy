package simple.cms

class SCMSHTMLWidgetCreator extends SCMSWidgetCreator {
	
    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSHTMLWidget("widgetId": widgetId)
	}
}
