package simple.cms

class SCMSLightboxWidgetCreator extends SCMSWidgetCreator {

    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSLightboxWidget("widgetId": widgetId)
	}

}
