package simple.cms

class SCMSMenuWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSMapWidget("widgetId": widgetId)
	}

}
