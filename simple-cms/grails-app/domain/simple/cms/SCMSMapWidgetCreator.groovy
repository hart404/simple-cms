package simple.cms

class SCMSMapWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSMapWidget("widgetId": widgetId)
	}

}
