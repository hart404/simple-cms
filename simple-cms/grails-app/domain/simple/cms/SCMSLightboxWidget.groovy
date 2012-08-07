package simple.cms

class SCMSLightboxWidget extends SCMSWidget {
	
    static constraints = {
    }
	
	SCMSWidget getWidgetInstance() {
		new SCMSPhotoWidget("widgetId": widgetId)
	}

}
